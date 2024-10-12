package pub.lhp.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import pub.lhp.infrastructure.dao.po.RaffleActivity;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖活动表Dao
 * @since 2024/10/12 21:43
 */
@Mapper
public interface IRaffleActivityDao {

    RaffleActivity queryRaffleActivityByActivityId(Long activityId);

}
