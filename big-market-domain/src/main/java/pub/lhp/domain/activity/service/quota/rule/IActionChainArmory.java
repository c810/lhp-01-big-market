package pub.lhp.domain.activity.service.quota.rule;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 下单规则责任链装配
 * @since 2024/10/13 21:04
 */
public interface IActionChainArmory {

    IActionChain next();

    IActionChain appendNext(IActionChain next);

}
