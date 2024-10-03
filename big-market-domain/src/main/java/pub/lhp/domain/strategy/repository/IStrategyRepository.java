package pub.lhp.domain.strategy.repository;

import org.springframework.stereotype.Repository;
import pub.lhp.domain.strategy.model.entity.StrategyAwardEntity;
import pub.lhp.domain.strategy.model.entity.StrategyEntity;
import pub.lhp.domain.strategy.model.entity.StrategyRuleEntity;
import pub.lhp.domain.strategy.model.valobj.StrategyAwardRuleModelVO;

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
     * 查询 策略奖品列表
     *
     * @param strategyId 策略ID
     * @return 策略奖品列表
     */
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    /**
     * 存储 策略奖品概率查找表
     * @param key 策略ID(_权重规则)
     * @param rateRange 概率区间
     * @param shuffleStrategyAwardRateSearchTable 策略奖品概率查找表
     */
    void storeStrategyAwardRateSearchTable(String key, BigDecimal rateRange, HashMap<Integer, Integer> shuffleStrategyAwardRateSearchTable);

    /**
     * 获取 概率区间
     * TODO: 可以去掉这个方法，因为已经有了 getRateRange(String key)
     *
     * @param strategyId 策略ID
     * @return 概率区间
     */
//    int getRateRange(Long strategyId);

    /**
     * 获取 概率区间
     * @param key 策略ID(_权重规则)
     * @return 概率区间
     */
    int getRateRange(String key);

    /**
     * 获取 随机奖品ID
     * @param key 策略ID(_权重规则)
     * @param randomNum 随机数
     * @return 随机奖品ID
     */
    Integer getStrategyAwardAssemble(String key, int randomNum);

    /**
     * 查询 策略
     * @param strategyId 策略ID
     * @return 策略
     */
    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    /**
     * 查询 策略规则
     * @param strategyId 策略ID
     * @param ruleModel 规则模型
     * @return 策略规则
     */
    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);

    /**
     * 查询 策略规则值
     * @param strategyId 策略ID
     * @param awardId 奖品ID
     * @param ruleModel 规则模型
     * @return 策略规则值
     */
    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

    StrategyAwardRuleModelVO queryStrategyAwardRuleModelVO(Long strategyId, Integer awardId);
}
