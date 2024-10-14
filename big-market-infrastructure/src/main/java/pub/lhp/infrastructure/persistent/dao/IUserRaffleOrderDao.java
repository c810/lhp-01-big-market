package pub.lhp.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;
import pub.lhp.infrastructure.persistent.po.UserRaffleOrder;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 用户抽奖订单表
 * @since 2024/10/14 10:10
 */
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserRaffleOrderDao {

    void insert(UserRaffleOrder userRaffleOrder);

    @DBRouter
    UserRaffleOrder queryNoUsedRaffleOrder(UserRaffleOrder userRaffleOrderReq);

    int updateUserRaffleOrderStateUsed(UserRaffleOrder userRaffleOrderReq);

}
