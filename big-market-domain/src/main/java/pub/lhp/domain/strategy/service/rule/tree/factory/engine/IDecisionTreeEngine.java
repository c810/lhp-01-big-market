package pub.lhp.domain.strategy.service.rule.tree.factory.engine;

import pub.lhp.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 规则树组合接口
 * @since 2024/10/4 15:46
 */
public interface IDecisionTreeEngine {

    DefaultTreeFactory.StrategyAwardVO process(String userId, Long strategyId, Integer awardId);

}
