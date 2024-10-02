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
     * 装配抽奖策略配置「触发的时机可以为活动审核通过后进行调用」
     * 只需要告知策略ID，即可完成策略的装配
     *
     * @param strategyId 策略ID
     * @return 装配结果
     */
    void assembleLotteryStrategy(Long strategyId);

    /**
     * 获取随机奖品ID
     * @param strategyId 策略ID
     * @return 随机奖品ID
     */
    Integer getRandomAwardId(Long strategyId);

}
