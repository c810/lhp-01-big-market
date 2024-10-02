package pub.lhp.domain.strategy.service.rule.tree.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pub.lhp.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import pub.lhp.domain.strategy.service.rule.tree.ILogicTreeNode;
import pub.lhp.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 次数锁节点
 * @since 2024/10/4 15:41
 */
@Slf4j
@Component("rule_lock")
public class RuleLockLogicTreeNode implements ILogicTreeNode {

    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId) {
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                .build();
    }

}
