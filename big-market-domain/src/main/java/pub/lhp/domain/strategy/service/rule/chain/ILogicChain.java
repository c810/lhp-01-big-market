package pub.lhp.domain.strategy.service.rule.chain;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 责任链接口
 * @since 2024/10/3 22:11
 */
public interface ILogicChain extends ILogicChainArmory {

    /**
     * 责任链接口
     *
     * @param userId     用户ID
     * @param strategyId 策略ID
     * @return 奖品ID
     */
    Integer logic(String userId, Long strategyId);

}
