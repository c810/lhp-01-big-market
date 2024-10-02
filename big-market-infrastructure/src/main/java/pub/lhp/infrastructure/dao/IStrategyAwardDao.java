package pub.lhp.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import pub.lhp.domain.strategy.service.armory.StrategyArmory;
import pub.lhp.infrastructure.dao.po.StrategyAward;

import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖策略奖品明细配置 - 概率、规则 DAO
 * @since 2024/9/28 23:34
 */
@Mapper
public interface IStrategyAwardDao {
    List<StrategyAward> queryStrategyAwardList();

    List<StrategyAward> queryStrategyAwardListByStrategyId(Long strategyId);

}
