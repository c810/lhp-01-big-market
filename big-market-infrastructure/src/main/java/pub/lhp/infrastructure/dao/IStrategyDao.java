package pub.lhp.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import pub.lhp.infrastructure.dao.po.Strategy;

import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖策略 DAO
 * @since 2024/9/28 23:34
 */
@Mapper
public interface IStrategyDao {
    List<Strategy> queryStrategyList();

    Strategy queryStrategyByStrategyId(Long strategyId);
}
