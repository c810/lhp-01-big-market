package pub.lhp.domain.strategy.repository;

import org.springframework.stereotype.Repository;
import pub.lhp.domain.strategy.model.entity.StrategyAwardEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 策略服务仓储接口
 * @since 2024/10/1 16:55
 */
public interface IStrategyRepository {
    /**
     * 查询策略奖品列表
     * 查询策略配置
     * @param strategyId 策略ID
     * @return 策略奖品列表
     */
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    /**
     * 存储策略奖品概率查找表
     * @param strategyId 策略ID
     * @param rateRange 概率值区间
     * @param shuffleStrategyAwardRateSearchTable 概率值查找表
     */
    void storeStrategyAwardRateSearchTable(Long strategyId, BigDecimal rateRange, HashMap<Integer, Integer> shuffleStrategyAwardRateSearchTable);

    /**
     * 获取概率值区间
     * @param strategyId 策略ID
     * @return 概率值区间
     */
    int getRateRange(Long strategyId);

    Integer getStrategyAwardAssemble(Long strategyId, int rateKey);
}
