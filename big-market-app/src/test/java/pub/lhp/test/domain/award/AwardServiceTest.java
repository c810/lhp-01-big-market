package pub.lhp.test.domain.award;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pub.lhp.domain.award.model.entity.UserAwardRecordEntity;
import pub.lhp.domain.award.model.valobj.AwardStateVO;
import pub.lhp.domain.award.service.IAwardService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 奖品服务测试
 * @since 2024/10/14 11:23
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AwardServiceTest {

    @Resource
    private IAwardService awardService;

    /**
     * 模拟发放抽奖记录，流程中会发送MQ，以及接收MQ消息，还有 task 表，补偿发送MQ
     */
    @Test
    public void test_saveUserAwardRecord() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            UserAwardRecordEntity userAwardRecordEntity = new UserAwardRecordEntity();
            userAwardRecordEntity.setUserId("xiaofuge");
            userAwardRecordEntity.setActivityId(100301L);
            userAwardRecordEntity.setStrategyId(100006L);
            userAwardRecordEntity.setOrderId(RandomStringUtils.randomNumeric(12));
            userAwardRecordEntity.setAwardId(101);
            userAwardRecordEntity.setAwardTitle("OpenAI 增加使用次数");
            userAwardRecordEntity.setAwardTime(new Date());
            userAwardRecordEntity.setAwardState(AwardStateVO.create);
            awardService.saveUserAwardRecord(userAwardRecordEntity);
            Thread.sleep(500);
        }

        new CountDownLatch(1).await();
    }

}
