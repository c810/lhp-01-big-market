package pub.lhp.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import pub.lhp.infrastructure.dao.po.RaffleActivityCount;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖活动次数配置表Dao
 * @since 2024/10/12 21:42
 */
@Mapper
public interface IRaffleActivityCountDao {

    RaffleActivityCount queryRaffleActivityCountByActivityCountId(Long activityCountId);

}
