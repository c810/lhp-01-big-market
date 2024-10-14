package pub.lhp.domain.strategy.service.armory;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 策略装配库（兵工厂），负责初始化策略计算
 * @since 2024/10/1 16:28
 */
public interface IStrategyArmory {

    /**
     * 装配 策略奖品概率查找表
     * 触发的时机可以为活动审核通过后进行调用
     *
     * @param strategyId 策略ID
     * @return 装配结果
     */
    boolean assembleLotteryStrategy(Long strategyId);

    /**
     * 装配抽奖策略配置「触发的时机可以为活动审核通过后进行调用」
     *
     * @param activityId 活动ID
     * @return 装配结果
     */
    boolean assembleLotteryStrategyByActivityId(Long activityId);

}
