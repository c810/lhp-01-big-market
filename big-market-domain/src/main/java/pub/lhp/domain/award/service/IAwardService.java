package pub.lhp.domain.award.service;

import pub.lhp.domain.award.model.entity.UserAwardRecordEntity;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 奖品服务接口
 * @since 2024/10/14 11:09
 */
public interface IAwardService {

    void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity);

}
