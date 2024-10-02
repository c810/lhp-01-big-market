package pub.lhp.domain.strategy.service.armory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pub.lhp.domain.strategy.model.entity.StrategyAwardEntity;
import pub.lhp.domain.strategy.repository.IStrategyRepository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 策略装配库（兵工厂），负责初始化策略计算
 * @since 2024/10/1 16:50
 */
@Slf4j
@Service
public class StrategyArmory implements IStrategyArmory {

    @Resource
    private IStrategyRepository repository;

    /**
     * 装配抽奖策略配置「触发的时机可以为活动审核通过后进行调用」
     * 只需要告知策略ID，即可完成策略的装配
     *
     * @param strategyId 策略ID
     * @return 装配结果
     */
    @Override
    public void assembleLotteryStrategy(Long strategyId) {
        // 1.查询策略配置
        List<StrategyAwardEntity> strategyAwardEntities = repository.queryStrategyAwardList(strategyId);

        // 2.获取最小概率值
        BigDecimal minAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        // 3.获取概率值的总和
        BigDecimal totalAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4.计算概率值的区间
        BigDecimal rateRange = totalAwardRate.divide(minAwardRate, 0, RoundingMode.CEILING);

        // 5.生成策略奖品概率查找表「这里指需要在list集合中，存放上对应的奖品占位即可，占位越多等于概率越高」
        ArrayList<Integer> strategyAwardRateSearchTable = new ArrayList<>(rateRange.intValue());
        for (StrategyAwardEntity strategyAwardEntity : strategyAwardEntities) {
            Integer awardId = strategyAwardEntity.getAwardId();
            BigDecimal awardRate = strategyAwardEntity.getAwardRate();
            // 计算出每个概率值需要存放到查找表的数量，循环填充
            for (int i = 0; i < rateRange.multiply(awardRate).setScale(0, RoundingMode.CEILING).intValue(); i++)
                strategyAwardRateSearchTable.add(awardId);
        }

        // 6.对存储的奖品进行乱序操作
        Collections.shuffle(strategyAwardRateSearchTable);

        // 7.生成出Map集合，key值，对应的就是后续的概率值。通过概率来获得对应的奖品ID
        HashMap<Integer, Integer> shuffleStrategyAwardRateSearchTable = new HashMap<>();
        for (int i = 0; i < strategyAwardRateSearchTable.size(); i++)
            shuffleStrategyAwardRateSearchTable.put(i, strategyAwardRateSearchTable.get(i));

        // 8.写入缓存
        repository.storeStrategyAwardRateSearchTable(strategyId, BigDecimal.valueOf(shuffleStrategyAwardRateSearchTable.size()), shuffleStrategyAwardRateSearchTable);
    }

    @Override
    public Integer getRandomAwardId(Long strategyId) {
        int rateRange = repository.getRateRange(strategyId);
        return repository.getStrategyAwardAssemble(strategyId, new SecureRandom().nextInt(rateRange));
    }
}
