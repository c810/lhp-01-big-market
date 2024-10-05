package pub.lhp.domain.strategy.repository;

import org.springframework.stereotype.Repository;
import pub.lhp.domain.strategy.model.entity.StrategyAwardEntity;
import pub.lhp.domain.strategy.model.entity.StrategyEntity;
import pub.lhp.domain.strategy.model.entity.StrategyRuleEntity;
import pub.lhp.domain.strategy.model.valobj.RuleTreeVO;
import pub.lhp.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import pub.lhp.domain.strategy.model.valobj.StrategyAwardStockKeyVO;

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

    String queryStrategyRuleValue(Long strategyId, String ruleModel);

    /**
     * 查询 策略规则值
     * @param strategyId 策略ID
     * @param awardId 奖品ID
     * @param ruleModel 规则模型
     * @return 策略规则值
     */
    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

    StrategyAwardRuleModelVO queryStrategyAwardRuleModelVO(Long strategyId, Integer awardId);

    /**
     * 根据规则树ID，查询树结构信息
     *
     * @param treeId 规则树ID
     * @return 树结构信息
     */
    RuleTreeVO queryRuleTreeVOByTreeId(String treeId);

    /**
     * 缓存奖品库存
     *
     * @param cacheKey   key
     * @param awardCount 库存值
     */
    void cacheStrategyAwardCount(String cacheKey, Integer awardCount);

    /**
     * 缓存key，decr 方式扣减库存
     *
     * @param cacheKey 缓存Key
     * @return 扣减结果
     */
    Boolean subtractionAwardStock(String cacheKey);

    /**
     * 写入奖品库存消费队列
     *
     * @param strategyAwardStockKeyVO 对象值对象
     */
    void awardStockConsumeSendQueue(StrategyAwardStockKeyVO strategyAwardStockKeyVO);

    /**
     * 获取奖品库存消费队列
     */
    StrategyAwardStockKeyVO takeQueueValue() throws InterruptedException;

    /**
     * 更新奖品库存消耗
     *
     * @param strategyId 策略ID
     * @param awardId 奖品ID
     */
    void updateStrategyAwardStock(Long strategyId, Integer awardId);

    /**
     * 根据策略ID+奖品ID的唯一值组合，查询奖品信息
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     * @return 奖品信息
     */
    StrategyAwardEntity queryStrategyAwardEntity(Long strategyId, Integer awardId);
}
