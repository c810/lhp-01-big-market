package pub.lhp.domain.strategy.service.armory;

import java.util.Date;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 策略抽奖调度
 * @since 2024/10/2 9:45
 */
public interface IStrategyDispatch {
    /**
     * 获取 随机奖品ID
     *
     * @param strategyId 策略ID
     * @return 随机奖品ID
     */
    Integer getRandomAwardId(Long strategyId);

    /**
     * 获取 随机奖品ID
     *
     * @param strategyId 策略ID
     * @param ruleWeightValue 权重规则
     * @return 随机奖品ID
     */
    Integer getRandomAwardId(Long strategyId, String ruleWeightValue);

    /**
     * 根据策略ID和奖品ID，扣减奖品缓存库存
     *
     * @param strategyId  策略ID
     * @param awardId     奖品ID
     * @param endDateTime 活动结束时间
     * @return 扣减结果
     */
    Boolean subtractionAwardStock(Long strategyId, Integer awardId, Date endDateTime);

}
