package pub.lhp.domain.strategy.service.armory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pub.lhp.domain.strategy.model.entity.StrategyAwardEntity;
import pub.lhp.domain.strategy.model.entity.StrategyEntity;
import pub.lhp.domain.strategy.model.entity.StrategyRuleEntity;
import pub.lhp.domain.strategy.repository.IStrategyRepository;
import pub.lhp.types.common.Constants;
import pub.lhp.types.enums.ResponseCode;
import pub.lhp.types.exception.AppException;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 策略装配库（兵工厂），负责初始化策略计算
 * @since 2024/10/1 16:50
 */
@Slf4j
@Service
public class StrategyArmoryDispatch implements IStrategyArmory, IStrategyDispatch {

    @Resource
    private IStrategyRepository repository;

    private final SecureRandom secureRandom = new SecureRandom();

    /**
     * 装配 策略奖品概率查找表
     * 触发的时机可以为活动审核通过后进行调用
     *
     * @param strategyId 策略ID
     * @return 装配结果
     */
    @Override
    public boolean assembleLotteryStrategy(Long strategyId) {
        // 1.装配 策略奖品概率查找表（没有权重规则的、该策略下所有奖品都能抽）
        // 查询 策略ID 对应的 策略奖品列表
        List<StrategyAwardEntity> strategyAwardEntities = repository.queryStrategyAwardList(strategyId);

        // 2 缓存奖品库存【用于decr扣减库存使用】
        for (StrategyAwardEntity strategyAward : strategyAwardEntities) {
            Integer awardId = strategyAward.getAwardId();
            Integer awardCount = strategyAward.getAwardCount();
            cacheStrategyAwardCount(strategyId, awardId, awardCount);
        }

        // 3.1 默认装配配置【全量抽奖概率】
        // 装配 策略奖品概率查找表（没有权重规则的、该策略下所有奖品都能抽）
        assembleLotteryStrategy(String.valueOf(strategyId), strategyAwardEntities);

        // 3.2 权重策略配置
        // 装配 策略奖品概率查找表（有权重规则的、抽够一定次数后可以去掉比较差的奖品）
        // 查询 策略
        StrategyEntity strategyEntity = repository.queryStrategyEntityByStrategyId(strategyId);
        // 获取 策略 是否有 权重规则
        String ruleWeight = strategyEntity.getRuleWeight();
        if (null == ruleWeight) return true;
        // 查询 策略规则 中的 权重规则
        StrategyRuleEntity strategyRuleEntity = repository.queryStrategyRule(strategyId, ruleWeight);
        if (null == strategyRuleEntity) // 业务异常，策略使用 权重规则，但策略规则中没有 权重规则的 配置
            throw new AppException(ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getCode(), ResponseCode.STRATEGY_RULE_WEIGHT_IS_NULL.getInfo());

        // 获取 权重规则 中的 权重值，数据案例；4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109
        Map<String, List<Integer>> ruleWeightValueMap = strategyRuleEntity.getRuleWeightValues();
        Set<String> keys = ruleWeightValueMap.keySet(); // 4000、5000、6000
        for (String key : keys) {
            List<Integer> ruleWeightValues = ruleWeightValueMap.get(key); // 比如key为4000时，102,103,104,105
            // 克隆一份 策略奖品列表，然后根据权重值进行过滤
            ArrayList<StrategyAwardEntity> strategyAwardEntitiesClone = new ArrayList<>(strategyAwardEntities);
            strategyAwardEntitiesClone.removeIf(entity -> !ruleWeightValues.contains(entity.getAwardId()));
            // 装配 策略奖品概率查找表（有权重规则的、抽够一定次数后可以去掉比较差的奖品）
            // 100001_4000:102,103,104,105、 100001_5000:102,103,104,105,106,107、 100001_6000:102,103,104,105,106,107,108,109
            assembleLotteryStrategy(String.valueOf(strategyId).concat("_").concat(key), strategyAwardEntitiesClone);
        }
        return true;
    }

    /**
     * 装配 策略奖品概率查找表
     * 根据策略ID，以及策略奖品列表，进行装配。
     * 将策略奖品列表中的奖品，按照概率值进行装配，生成 策略奖品概率查找表
     *
     * @param key                   策略ID
     * @param strategyAwardEntities 策略奖品列表
     */
    public void assembleLotteryStrategy(String key, List<StrategyAwardEntity> strategyAwardEntities) {
        // 1.获取最小概率值
        BigDecimal minAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        // 2.获取概率值的总和
        BigDecimal totalAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3.计算概率值的区间
        BigDecimal rateRange = totalAwardRate.divide(minAwardRate, 0, RoundingMode.CEILING);

        // 4.生成 策略奖品概率查找表
        ArrayList<Integer> strategyAwardRateSearchTable = new ArrayList<>(rateRange.intValue());
        for (StrategyAwardEntity strategyAwardEntity : strategyAwardEntities) {
            Integer awardId = strategyAwardEntity.getAwardId();
            BigDecimal awardRate = strategyAwardEntity.getAwardRate();
            // 这里指需要在list集合中，存放上对应的奖品占位即可，占位越多等于概率越高
            // 计算出每个概率值需要存放到查找表的数量，循环填充
            for (int i = 0; i < rateRange.multiply(awardRate).setScale(0, RoundingMode.CEILING).intValue(); i++)
                strategyAwardRateSearchTable.add(awardId);
        }

        // 5.对存储的奖品进行乱序操作
        Collections.shuffle(strategyAwardRateSearchTable);

        // 6.生成出Map集合，key值，对应的就是后续的概率值。通过概率来获得对应的奖品ID
        HashMap<Integer, Integer> shuffleStrategyAwardRateSearchTable = new HashMap<>();
        for (int i = 0; i < strategyAwardRateSearchTable.size(); i++)
            shuffleStrategyAwardRateSearchTable.put(i, strategyAwardRateSearchTable.get(i));

        // 7.写入缓存
        repository.storeStrategyAwardRateSearchTable(key, BigDecimal.valueOf(shuffleStrategyAwardRateSearchTable.size()), shuffleStrategyAwardRateSearchTable);
    }

    /**
     * 缓存奖品库存到Redis
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     * @param awardCount 奖品库存
     */
    private void cacheStrategyAwardCount(Long strategyId, Integer awardId, Integer awardCount) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_COUNT_KEY + strategyId + Constants.UNDERLINE + awardId;
        repository.cacheStrategyAwardCount(cacheKey, awardCount);
    }

    /**
     * 获取 随机奖品ID
     *
     * @param strategyId 策略ID
     * @return 随机奖品ID
     */
    @Override
    public Integer getRandomAwardId(Long strategyId) {
        // 获取 概率区间
        // 分布式部署下，不一定是 当前应用 装配的 策略奖品概率查找表，也就是值不一定会保存到本应用，而是分布式应用，所以需要从Redis中获取
        int rateRange = repository.getRateRange(String.valueOf(strategyId));
        // 获取 随机奖品ID
        // 抽奖，通过生成的随机数，获取概率值奖品查找表的结果
        return repository.getStrategyAwardAssemble(String.valueOf(strategyId), new SecureRandom().nextInt(rateRange));
    }

    /**
     * 获取 随机奖品ID
     * @param strategyId 策略ID
     * @param ruleWeightValue 权重规则
     * @return 随机奖品ID
     */
    @Override
    public Integer getRandomAwardId(Long strategyId, String ruleWeightValue) {
        String key = String.valueOf(strategyId).concat("_").concat(ruleWeightValue);
        // 获取概率区间
        // 分布式部署下，不一定是 当前应用 装配的 策略奖品概率查找表，也就是值不一定会保存到本应用，而是分布式应用，所以需要从Redis中获取
        int rateRange = repository.getRateRange(key);
        // 获取 随机奖品ID
        // 抽奖，通过生成的随机数，获取概率值奖品查找表的结果
        return repository.getStrategyAwardAssemble(key, new SecureRandom().nextInt(rateRange));
    }

    @Override
    public Boolean subtractionAwardStock(Long strategyId, Integer awardId) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_COUNT_KEY + strategyId + Constants.UNDERLINE + awardId;
        return repository.subtractionAwardStock(cacheKey);
    }
}
