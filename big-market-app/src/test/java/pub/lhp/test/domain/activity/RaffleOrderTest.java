package pub.lhp.test.domain.activity;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pub.lhp.domain.activity.model.entity.ActivityOrderEntity;
import pub.lhp.domain.activity.model.entity.SkuRechargeEntity;
import pub.lhp.domain.activity.service.IRaffleOrder;

import javax.annotation.Resource;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖活动订单单测
 * @since 2024/10/12 22:53
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleOrderTest {

    @Resource
    private IRaffleOrder raffleOrder;

    @Test
    public void test_createSkuRechargeOrder() {
        SkuRechargeEntity skuRechargeEntity = new SkuRechargeEntity();
        skuRechargeEntity.setUserId("lihaopeng");
        skuRechargeEntity.setSku(9011L);
        // outBusinessNo 作为幂等仿重使用，同一个业务单号2次使用会抛出索引冲突 Duplicate entry '700091009111' for key 'uq_out_business_no' 确保唯一性。
        skuRechargeEntity.setOutBusinessNo("700091009111");
        String orderId = raffleOrder.createSkuRechargeOrder(skuRechargeEntity);
        log.info("测试结果：{}", orderId);
    }

}
