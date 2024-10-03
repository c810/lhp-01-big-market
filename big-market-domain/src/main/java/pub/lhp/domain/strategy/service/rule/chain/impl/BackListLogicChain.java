package pub.lhp.domain.strategy.service.rule.chain.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pub.lhp.domain.strategy.repository.IStrategyRepository;
import pub.lhp.domain.strategy.service.rule.chain.AbstractLogicChain;
import pub.lhp.types.common.Constants;

import javax.annotation.Resource;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 黑名单责任链
 * @since 2024/10/3 22:15
 */
@Slf4j
@Component("rule_blacklist")
public class BackListLogicChain extends AbstractLogicChain {

    @Resource
    private IStrategyRepository repository;

    @Override
    public Integer logic(String userId, Long strategyId) {
        log.info("抽奖责任链-黑名单开始 userId: {} strategyId: {} ruleModel: {}", userId, strategyId, ruleModel());

        // 查询规则值配置
        String ruleValue = repository.queryStrategyRuleValue(strategyId, ruleModel());
        String[] splitRuleValue = ruleValue.split(Constants.COLON);
        Integer awardId = Integer.parseInt(splitRuleValue[0]);

        // 黑名单抽奖判断
        String[] userBlackIds = splitRuleValue[1].split(Constants.SPLIT);
        for (String userBlackId : userBlackIds) {
            if (userId.equals(userBlackId)) {
                log.info("抽奖责任链-黑名单接管 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, ruleModel(), awardId);
                return awardId;
            }
        }

        // 过滤其他责任链
        log.info("抽奖责任链-黑名单放行 userId: {} strategyId: {} ruleModel: {}", userId, strategyId, ruleModel());
        return next().logic(userId, strategyId);
    }

    @Override
    protected String ruleModel() {
        return "rule_blacklist";
    }
}
