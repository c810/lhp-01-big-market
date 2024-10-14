package pub.lhp.domain.activity.service.armory;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 活动装配预热
 * @since 2024/10/13 22:17
 */
public interface IActivityArmory {

    boolean assembleActivitySkuByActivityId(Long activityId);

    boolean assembleActivitySku(Long sku);

}
