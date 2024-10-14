package pub.lhp.domain.activity.service.quota.rule;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 下单规则责任链抽象类
 * @since 2024/10/13 21:04
 */
public abstract class AbstractActionChain implements IActionChain {

    private IActionChain next;

    @Override
    public IActionChain next() {
        return next;
    }

    @Override
    public IActionChain appendNext(IActionChain next) {
        this.next = next;
        return next;
    }

}
