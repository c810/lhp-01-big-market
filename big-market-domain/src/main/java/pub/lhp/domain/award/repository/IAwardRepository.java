package pub.lhp.domain.award.repository;

import pub.lhp.domain.award.model.aggregate.UserAwardRecordAggregate;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 奖品仓储服务
 * @since 2024/10/14 11:09
 */
public interface IAwardRepository {

    void saveUserAwardRecord(UserAwardRecordAggregate userAwardRecordAggregate);

}
