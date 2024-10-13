package pub.lhp.infrastructure.persistent.dao;

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

}
