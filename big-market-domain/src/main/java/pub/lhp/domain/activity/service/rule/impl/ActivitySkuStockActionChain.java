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
 * @description 商品库存规则节点
 * @since 2024/10/13 21:03
 */
@Slf4j
@Component("activity_sku_stock_action")
public class ActivitySkuStockActionChain extends AbstractActionChain {

    @Override
    public boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {
        log.info("活动责任链-商品库存处理【校验&扣减】开始。");

        return true;
    }

}
