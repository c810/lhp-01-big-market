package pub.lhp.domain.strategy.service.rule;

import pub.lhp.domain.strategy.model.entity.RuleActionEntity;
import pub.lhp.domain.strategy.model.entity.RuleMatterEntity;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖规则过滤接口
 * @since 2024/10/2 21:48
 */
public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {

    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);
}
