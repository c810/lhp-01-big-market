package pub.lhp.domain.strategy.service.rule.tree.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import pub.lhp.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import pub.lhp.domain.strategy.model.valobj.RuleTreeVO;
import pub.lhp.domain.strategy.service.rule.tree.ILogicTreeNode;
import pub.lhp.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import pub.lhp.domain.strategy.service.rule.tree.factory.engine.impl.DecisionTreeEngine;

import java.util.Map;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 规则树工厂
 * @since 2024/10/4 15:44
 */
@Service
public class DefaultTreeFactory {

    private final Map<String, ILogicTreeNode> logicTreeNodeGroup;

    public DefaultTreeFactory(Map<String, ILogicTreeNode> logicTreeNodeGroup) {
        this.logicTreeNodeGroup = logicTreeNodeGroup;
    }

    public IDecisionTreeEngine openLogicTree(RuleTreeVO ruleTreeVO) {
        return new DecisionTreeEngine(logicTreeNodeGroup, ruleTreeVO);
    }

    /**
     * 决策树个动作实习
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TreeActionEntity {
        private RuleLogicCheckTypeVO ruleLogicCheckType;
        private StrategyAwardData strategyAwardData;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StrategyAwardData {
        /** 抽奖奖品ID - 内部流转使用 */
        private Integer awardId;
        /** 抽奖奖品规则 */
        private String awardRuleValue;
    }

}
