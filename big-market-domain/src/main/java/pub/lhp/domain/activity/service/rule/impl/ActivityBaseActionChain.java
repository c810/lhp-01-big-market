package pub.lhp.domain.activity.service.rule.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pub.lhp.domain.activity.model.entity.ActivityCountEntity;
import pub.lhp.domain.activity.model.entity.ActivityEntity;
import pub.lhp.domain.activity.model.entity.ActivitySkuEntity;
import pub.lhp.domain.activity.service.rule.AbstractActionChain;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 活动规则过滤【日期、状态】
 * @since 2024/10/13 21:03
 */
@Slf4j
@Component("activity_base_action")
public class ActivityBaseActionChain extends AbstractActionChain {

    @Override
    public boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {

        log.info("活动责任链-基础信息【有效期、状态】校验开始。");

        return next().action(activitySkuEntity, activityEntity, activityCountEntity);
    }

}
