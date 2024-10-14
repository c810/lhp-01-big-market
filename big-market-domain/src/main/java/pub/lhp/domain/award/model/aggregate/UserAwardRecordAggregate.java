package pub.lhp.domain.award.model.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pub.lhp.domain.award.model.entity.TaskEntity;
import pub.lhp.domain.award.model.entity.UserAwardRecordEntity;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 用户中奖记录聚合对象 【聚合代表一个事务操作】
 * @since 2024/10/14 11:06
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAwardRecordAggregate {

    private UserAwardRecordEntity userAwardRecordEntity;

    private TaskEntity taskEntity;

}
