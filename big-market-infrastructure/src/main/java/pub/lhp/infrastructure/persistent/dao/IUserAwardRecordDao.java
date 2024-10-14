package pub.lhp.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;
import pub.lhp.infrastructure.persistent.po.UserAwardRecord;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 用户中奖记录表
 * @since 2024/10/14 10:10
 */
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserAwardRecordDao {

    void insert(UserAwardRecord userAwardRecord);

}
