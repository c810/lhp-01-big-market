package pub.lhp.domain.strategy.service.raffle;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pub.lhp.domain.strategy.model.entity.RaffleFactorEntity;
import pub.lhp.domain.strategy.model.entity.RuleActionEntity;
import pub.lhp.domain.strategy.model.entity.RuleMatterEntity;
import pub.lhp.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import pub.lhp.domain.strategy.model.valobj.RuleTreeVO;
import pub.lhp.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import pub.lhp.domain.strategy.repository.IStrategyRepository;
import pub.lhp.domain.strategy.service.AbstractRaffleStrategy;
import pub.lhp.domain.strategy.service.armory.IStrategyDispatch;
import pub.lhp.domain.strategy.service.rule.ILogicFilter;
import pub.lhp.domain.strategy.service.rule.chain.ILogicChain;
import pub.lhp.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import pub.lhp.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import pub.lhp.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import pub.lhp.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 默认的抽奖策略实现
 * @since 2024/10/2 23:26
 */
@Slf4j
@Service
public class DefaultRaffleStrategy extends AbstractRaffleStrategy {

    public DefaultRaffleStrategy(IStrategyRepository repository, IStrategyDispatch strategyDispatch, DefaultChainFactory defaultChainFactory, DefaultTreeFactory defaultTreeFactory) {
        super(repository, strategyDispatch, defaultChainFactory, defaultTreeFactory);
    }

    @Override
    public DefaultChainFactory.StrategyAwardVO raffleLogicChain(String userId, Long strategyId) {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(strategyId);
        return logicChain.logic(userId, strategyId);
    }

    @Override
    public DefaultTreeFactory.StrategyAwardVO raffleLogicTree(String userId, Long strategyId, Integer awardId) {
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = repository.queryStrategyAwardRuleModelVO(strategyId, awardId);
        if (null == strategyAwardRuleModelVO) {
            return DefaultTreeFactory.StrategyAwardVO.builder().awardId(awardId).build();
        }
        RuleTreeVO ruleTreeVO = repository.queryRuleTreeVOByTreeId(strategyAwardRuleModelVO.getRuleModels());
        if (null == ruleTreeVO) {
            throw new RuntimeException("存在抽奖策略配置的规则模型 Key，未在库表 rule_tree、rule_tree_node、rule_tree_line 配置对应的规则树信息 " + strategyAwardRuleModelVO.getRuleModels());
        }
        IDecisionTreeEngine treeEngine = defaultTreeFactory.openLogicTree(ruleTreeVO);
        return treeEngine.process(userId, strategyId, awardId);
    }

}
