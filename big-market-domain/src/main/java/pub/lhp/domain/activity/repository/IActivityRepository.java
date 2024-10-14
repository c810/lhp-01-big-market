package pub.lhp.domain.activity.repository;

import pub.lhp.domain.activity.model.aggregate.CreatePartakeOrderAggregate;
import pub.lhp.domain.activity.model.aggregate.CreateQuotaOrderAggregate;
import pub.lhp.domain.activity.model.entity.*;
import pub.lhp.domain.activity.model.valobj.ActivitySkuStockKeyVO;

import java.util.Date;
import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 活动仓储接口
 * @since 2024/10/12 22:43
 */
public interface IActivityRepository {

    ActivitySkuEntity queryActivitySku(Long sku);

    ActivityEntity queryRaffleActivityByActivityId(Long activityId);

    ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId);

    void doSaveOrder(CreateQuotaOrderAggregate createOrderAggregate);

    void cacheActivitySkuStockCount(String cacheKey, Integer stockCount);

    boolean subtractionActivitySkuStock(Long sku, String cacheKey, Date endDateTime);

    void activitySkuStockConsumeSendQueue(ActivitySkuStockKeyVO activitySkuStockKeyVO);

    ActivitySkuStockKeyVO takeQueueValue();

    void clearQueueValue();

    void updateActivitySkuStock(Long sku);

    void clearActivitySkuStock(Long sku);

    UserRaffleOrderEntity queryNoUsedRaffleOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity);

    ActivityAccountEntity queryActivityAccountByUserId(String userId, Long activityId);

    ActivityAccountMonthEntity queryActivityAccountMonthByUserId(String userId, Long activityId, String month);

    ActivityAccountDayEntity queryActivityAccountDayByUserId(String userId, Long activityId, String day);

    void saveCreatePartakeOrderAggregate(CreatePartakeOrderAggregate createPartakeOrderAggregate);

    List<ActivitySkuEntity> queryActivitySkuListByActivityId(Long activityId);

}
