package pub.lhp.domain.strategy.service;

import pub.lhp.domain.strategy.model.entity.RaffleAwardEntity;
import pub.lhp.domain.strategy.model.entity.RaffleFactorEntity;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖策略接口
 * @since 2024/10/2 21:39
 */
public interface IRaffleStrategy {

    RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity);
}
