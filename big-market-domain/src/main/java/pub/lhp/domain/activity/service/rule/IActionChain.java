package pub.lhp.domain.activity.service.rule;

import pub.lhp.domain.activity.model.entity.ActivityCountEntity;
import pub.lhp.domain.activity.model.entity.ActivityEntity;
import pub.lhp.domain.activity.model.entity.ActivitySkuEntity;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 下单规则过滤接口
 * @since 2024/10/13 21:04
 */
public interface IActionChain extends IActionChainArmory {

    boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);

}
