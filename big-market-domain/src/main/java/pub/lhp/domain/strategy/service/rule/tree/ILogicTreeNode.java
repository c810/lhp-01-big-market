package pub.lhp.domain.strategy.service.rule.tree;

import pub.lhp.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 规则树接口
 * @since 2024/10/4 15:39
 */
public interface ILogicTreeNode {

    DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue);

}
