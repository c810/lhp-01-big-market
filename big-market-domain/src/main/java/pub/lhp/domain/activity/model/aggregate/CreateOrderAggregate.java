package pub.lhp.domain.activity.model.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pub.lhp.domain.activity.model.entity.ActivityAccountEntity;
import pub.lhp.domain.activity.model.entity.ActivityOrderEntity;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 下单聚合对象
 * @since 2024/10/12 22:45
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderAggregate {

    /**
     * 活动账户实体
     */
    private ActivityAccountEntity activityAccountEntity;
    /**
     * 活动订单实体
     */
    private ActivityOrderEntity activityOrderEntity;

}
