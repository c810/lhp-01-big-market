package pub.lhp.domain.strategy.service.rule.chain;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 责任链装配
 * @since 2024/10/3 22:11
 */
public interface ILogicChainArmory {

    ILogicChain next();

    ILogicChain appendNext(ILogicChain next);

}
