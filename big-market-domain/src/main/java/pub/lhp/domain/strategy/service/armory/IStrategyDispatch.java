package pub.lhp.domain.strategy.service.armory;

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
}
