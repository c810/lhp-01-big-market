package pub.lhp.domain.activity.service;

import pub.lhp.domain.activity.model.entity.ActivityCountEntity;
import pub.lhp.domain.activity.model.entity.ActivityEntity;
import pub.lhp.domain.activity.model.entity.ActivitySkuEntity;
import pub.lhp.domain.activity.repository.IActivityRepository;
import pub.lhp.domain.activity.service.rule.factory.DefaultActivityChainFactory;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖活动的支撑类
 * @since 2024/10/13 21:01
 */
public class RaffleActivitySupport {

    protected DefaultActivityChainFactory defaultActivityChainFactory;

    protected IActivityRepository activityRepository;

    public RaffleActivitySupport(IActivityRepository activityRepository, DefaultActivityChainFactory defaultActivityChainFactory) {
        this.activityRepository = activityRepository;
        this.defaultActivityChainFactory = defaultActivityChainFactory;
    }

    public ActivitySkuEntity queryActivitySku(Long sku) {
        return activityRepository.queryActivitySku(sku);
    }

    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        return activityRepository.queryRaffleActivityByActivityId(activityId);
    }

    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {
        return activityRepository.queryRaffleActivityCountByActivityCountId(activityCountId);
    }

}
