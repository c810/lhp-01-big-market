package pub.lhp.domain.strategy.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import pub.lhp.domain.strategy.model.entity.RaffleAwardEntity;
import pub.lhp.domain.strategy.model.entity.RaffleFactorEntity;
import pub.lhp.domain.strategy.model.entity.RuleActionEntity;
import pub.lhp.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import pub.lhp.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import pub.lhp.domain.strategy.repository.IStrategyRepository;
import pub.lhp.domain.strategy.service.armory.IStrategyDispatch;
import pub.lhp.domain.strategy.service.rule.chain.ILogicChain;
import pub.lhp.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import pub.lhp.types.enums.ResponseCode;
import pub.lhp.types.exception.AppException;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖策略抽象类，定义抽奖的标准流程
 * @since 2024/10/2 21:47
 */
@Slf4j
public abstract class AbstractRaffleStrategy implements IRaffleStrategy {

    // 策略仓储服务 -> domain层像一个大厨，仓储层提供米面粮油
    protected IStrategyRepository repository;
    // 策略调度服务 -> 只负责抽奖处理，通过新增接口的方式，隔离职责，不需要使用方关心或者调用抽奖的初始化
    protected IStrategyDispatch strategyDispatch;

    private DefaultChainFactory defaultChainFactory;

    public AbstractRaffleStrategy(IStrategyRepository repository, IStrategyDispatch strategyDispatch, DefaultChainFactory defaultChainFactory) {
        this.repository = repository;
        this.strategyDispatch = strategyDispatch;
        this.defaultChainFactory = defaultChainFactory;
    }

    /**
     * 执行抽奖
     *
     * @param raffleFactorEntity 抽奖因子
     * @return 抽奖奖品
     */
    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity) {
        // 1. 参数校验
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if (null == strategyId || StringUtils.isBlank(userId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        // 2. 获取抽奖责任链 - 前置规则的责任链处理
        ILogicChain logicChain = defaultChainFactory.openLogicChain(strategyId);

        // 3. 通过责任链获得，奖品ID
        Integer awardId = logicChain.logic(userId, strategyId);

        // 4. 查询奖品规则「抽奖中（拿到奖品ID时，过滤规则）、抽奖后（扣减完奖品库存后过滤，抽奖中拦截和无库存则走兜底）」
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = repository.queryStrategyAwardRuleModelVO(strategyId, awardId);

        // 5. 抽奖中 - 规则过滤
        RuleActionEntity<RuleActionEntity.RaffleCenterEntity> ruleActionCenterEntity = this.doCheckRaffleCenterLogic(RaffleFactorEntity.builder()
                .userId(userId)
                .strategyId(strategyId)
                .awardId(awardId)
                .build(), strategyAwardRuleModelVO.raffleCenterRuleModelList());

        if (RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(ruleActionCenterEntity.getCode())){
            log.info("【临时日志】中奖中规则拦截，通过抽奖后规则 rule_luck_award 走兜底奖励。");
            return RaffleAwardEntity.builder()
                    .awardDesc("中奖中规则拦截，通过抽奖后规则 rule_luck_award 走兜底奖励。")
                    .build();
        }

        return RaffleAwardEntity.builder()
                .awardId(awardId)
                .build();
    }

    /**
     * 抽奖前动作 - 规则过滤
     *
     * @param raffleFactorEntity 抽奖因子
     * @param logics 规则模型
     * @return 规则过滤结果（执行后规则动作实体）
     */
    protected abstract RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> doCheckRaffleBeforeLogic(RaffleFactorEntity raffleFactorEntity, String... logics);

    protected abstract RuleActionEntity<RuleActionEntity.RaffleCenterEntity> doCheckRaffleCenterLogic(RaffleFactorEntity raffleFactorEntity, String... logics);
}
