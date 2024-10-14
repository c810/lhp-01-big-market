package pub.lhp.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import org.apache.ibatis.annotations.Mapper;
import pub.lhp.infrastructure.persistent.po.RaffleActivityAccount;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖活动账户表
 * @since 2024/10/12 21:41
 */
@Mapper
public interface IRaffleActivityAccountDao {

    void insert(RaffleActivityAccount raffleActivityAccount);

    int updateAccountQuota(RaffleActivityAccount raffleActivityAccount);

    @DBRouter
    RaffleActivityAccount queryActivityAccountByUserId(RaffleActivityAccount raffleActivityAccountReq);

    int updateActivityAccountSubtractionQuota(RaffleActivityAccount raffleActivityAccount);

    int updateActivityAccountMonthSubtractionQuota(RaffleActivityAccount raffleActivityAccount);

    int updateActivityAccountDaySubtractionQuota(RaffleActivityAccount raffleActivityAccount);

    void updateActivityAccountMonthSurplusImageQuota(RaffleActivityAccount raffleActivityAccount);

    void updateActivityAccountDaySurplusImageQuota(RaffleActivityAccount raffleActivityAccount);

}
