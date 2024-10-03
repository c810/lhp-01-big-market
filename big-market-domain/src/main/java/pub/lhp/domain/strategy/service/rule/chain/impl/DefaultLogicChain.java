package pub.lhp.domain.strategy.service.rule.chain.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pub.lhp.domain.strategy.service.armory.IStrategyDispatch;
import pub.lhp.domain.strategy.service.rule.chain.AbstractLogicChain;

import javax.annotation.Resource;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 默认的责任链「作为最后一个链」
 * @since 2024/10/3 22:16
 */
@Slf4j
@Component("default")
public class DefaultLogicChain extends AbstractLogicChain {

    @Resource
    protected IStrategyDispatch strategyDispatch;

    @Override
    public Integer logic(String userId, Long strategyId) {
        Integer awardId = strategyDispatch.getRandomAwardId(strategyId);
        log.info("抽奖责任链-默认处理 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, ruleModel(), awardId);
        return awardId;
    }

    @Override
    protected String ruleModel() {
        return "default";
    }

}
