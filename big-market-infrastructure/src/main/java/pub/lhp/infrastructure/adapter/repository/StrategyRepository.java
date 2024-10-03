package pub.lhp.infrastructure.adapter.repository;

import org.springframework.stereotype.Repository;
import pub.lhp.domain.strategy.model.entity.StrategyAwardEntity;
import pub.lhp.domain.strategy.model.entity.StrategyEntity;
import pub.lhp.domain.strategy.model.entity.StrategyRuleEntity;
import pub.lhp.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import pub.lhp.domain.strategy.repository.IStrategyRepository;
import pub.lhp.infrastructure.dao.IStrategyAwardDao;
import pub.lhp.infrastructure.dao.IStrategyDao;
import pub.lhp.infrastructure.dao.IStrategyRuleDao;
import pub.lhp.infrastructure.dao.po.Strategy;
import pub.lhp.infrastructure.dao.po.StrategyAward;
import pub.lhp.infrastructure.dao.po.StrategyRule;
import pub.lhp.infrastructure.redis.IRedisService;
import pub.lhp.types.common.Constants;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 策略仓储实现
 * @since 2024/10/1 17:05
 */
@Repository
public class StrategyRepository implements IStrategyRepository {
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyAwardDao strategyAwardDao;
    @Resource
    private IStrategyRuleDao strategyRuleDao;
    @Resource
    private IRedisService redisService;

    /**
     * 查询 策略奖品列表
     *
     * @param strategyId 策略ID
     * @return 策略奖品列表
     */
    @Override
    public List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_KEY + strategyId; // big_market_strategy_award_key_100001
        // 1.查询缓存
        List<StrategyAwardEntity> strategyAwardEntities = redisService.getValue(cacheKey);
        if (null != strategyAwardEntities && !strategyAwardEntities.isEmpty()) return strategyAwardEntities;
        // 2.查询数据库
        List<StrategyAward> strategyAwards = strategyAwardDao.queryStrategyAwardListByStrategyId(strategyId);
        strategyAwardEntities = new ArrayList<>(strategyAwards.size());
        for (StrategyAward strategyAward : strategyAwards) {
            StrategyAwardEntity strategyAwardEntity = StrategyAwardEntity.builder()
                    .strategyId(strategyAward.getStrategyId())
                    .awardId(strategyAward.getAwardId())
                    .awardTitle(strategyAward.getAwardTitle())
                    .awardSubtitle(strategyAward.getAwardSubtitle())
                    .awardCount(strategyAward.getAwardCount())
                    .awardCountSurplus(strategyAward.getAwardCountSurplus())
                    .awardRate(strategyAward.getAwardRate())
                    .build();
            strategyAwardEntities.add(strategyAwardEntity);
        }
        // 3.写入缓存
        redisService.setValue(cacheKey, strategyAwardEntities);
        return strategyAwardEntities;
    }

    /**
     * 存储 策略奖品概率查找表
     * @param key 策略ID(_权重规则)
     * @param rateRange 概率区间
     * @param shuffleStrategyAwardRateSearchTable 策略奖品概率查找表
     */
    @Override
    public void storeStrategyAwardRateSearchTable(String key, BigDecimal rateRange, HashMap<Integer, Integer> shuffleStrategyAwardRateSearchTable) {
        // 1.存储抽奖策略范围值，如10000，用于生成10000以内的随机数
        redisService.setValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + key, rateRange.intValue());
        // 2.存储概率查找表
        Map<Integer, Integer> cacheRateTable = redisService.getMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + key);
        cacheRateTable.putAll(shuffleStrategyAwardRateSearchTable);
    }

//    @Override
//    public int getRateRange(Long strategyId) {
//        return getRateRange(String.valueOf(strategyId));
//    }

    /**
     * 获取 概率区间
     * @param key 策略ID(_权重规则)
     * @return 概率区间
     */
    @Override
    public int getRateRange(String key) {
        return redisService.getValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + key);
    }

    /**
     * 获取 随机奖品ID
     * @param key 策略ID(_权重规则)
     * @param randomNum 随机数
     * @return 随机奖品ID
     */
    @Override
    public Integer getStrategyAwardAssemble(String key, int randomNum) {
        return redisService.getFromMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + key, randomNum);
    }

    /**
     * 查询 策略
     * @param strategyId 策略ID
     * @return 策略
     */
    @Override
    public StrategyEntity queryStrategyEntityByStrategyId(Long strategyId) {
        // 优先从缓存获取
        String cacheKey = Constants.RedisKey.STRATEGY_KEY + strategyId;
        StrategyEntity strategyEntity = redisService.getValue(cacheKey);
        if (null != strategyEntity) return strategyEntity;
        // 从数据库获取
        Strategy strategy = strategyDao.queryStrategyByStrategyId(strategyId);
        strategyEntity = StrategyEntity.builder()
                .strategyId(strategy.getStrategyId())
                .strategyDesc(strategy.getStrategyDesc())
                .ruleModels(strategy.getRuleModels())
                .build();
        // 写入缓存
        redisService.setValue(cacheKey, strategyEntity);
        return strategyEntity;
    }

    /**
     * 查询 策略规则
     * @param strategyId 策略ID
     * @param ruleModel 规则模型
     * @return 策略规则
     */
    @Override
    public StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel) {
        StrategyRule strategyRuleReq = new StrategyRule();
        strategyRuleReq.setStrategyId(strategyId);
        strategyRuleReq.setRuleModel(ruleModel);
        StrategyRule strategyRule = strategyRuleDao.queryStrategyRule(strategyRuleReq);
        return StrategyRuleEntity.builder()
                .strategyId(strategyRule.getStrategyId())
                .awardId(strategyRule.getAwardId())
                .ruleType(strategyRule.getRuleType())
                .ruleModel(strategyRule.getRuleModel())
                .ruleValue(strategyRule.getRuleValue())
                .ruleDesc(strategyRule.getRuleDesc())
                .build();
        // TODO: 这里也可以写入缓存，但是为了后续开发方便，暂时不写入缓存
    }

    /**
     * 查询 策略规则值
     * @param strategyId 策略ID
     * @param awardId 奖品ID
     * @param ruleModel 规则模型
     * @return 策略规则值
     */
    @Override
    public String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel) {
        StrategyRule strategyRule = new StrategyRule();
        strategyRule.setStrategyId(strategyId);
        strategyRule.setAwardId(awardId);
        strategyRule.setRuleModel(ruleModel);
        return strategyRuleDao.queryStrategyRuleValue(strategyRule);
    }

    @Override
    public StrategyAwardRuleModelVO queryStrategyAwardRuleModelVO(Long strategyId, Integer awardId) {
        StrategyAward strategyAward = new StrategyAward();
        strategyAward.setStrategyId(strategyId);
        strategyAward.setAwardId(awardId);
        String ruleModels = strategyAwardDao.queryStrategyAwardRuleModels(strategyAward);
        return StrategyAwardRuleModelVO.builder().ruleModels(ruleModels).build();
    }
}
