package pub.lhp.test.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pub.lhp.domain.strategy.service.armory.IStrategyArmory;
import pub.lhp.domain.strategy.service.armory.IStrategyDispatch;

import javax.annotation.Resource;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 策略装配 单元测试
 * @since 2024/10/1 22:06
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyTest {

    @Resource
    private IStrategyArmory strategyArmory;
    @Resource
    private IStrategyDispatch strategyDispatch;

    /**
     * 测试装配，策略ID：100001L、100002L 装配的时候创建策略表写入到 Redis Map 中
     */
    @Before
    public void test_assembleLotteryStrategy() {
        boolean success = strategyArmory.assembleLotteryStrategy(100001L);
        log.info("装配结果: {}", success);
    }

    /**
     * 测试抽奖
     */
    @Test
    public void test_getRandomAwardId() {
        log.info("测试结果: {} - 奖品ID值", strategyDispatch.getRandomAwardId(100001L));
        log.info("测试结果: {} - 奖品ID值", strategyDispatch.getRandomAwardId(100001L));
        log.info("测试结果: {} - 奖品ID值", strategyDispatch.getRandomAwardId(100001L));
    }


    /**
     * 根据策略ID+权重值，从装配的策略中随机获取奖品ID值
     * 4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109
     */
    @Test
    public void test_getRandomAwardIdRuleWeightValue() {
        log.info("测试结果: {} - 4000 策略配置", strategyDispatch.getRandomAwardId(100001L, "4000:102,103,104,105"));
        log.info("测试结果: {} - 5000 策略配置", strategyDispatch.getRandomAwardId(100001L, "5000:102,103,104,105,106,107"));
        log.info("测试结果: {} - 6000 策略配置", strategyDispatch.getRandomAwardId(100001L, "6000:102,103,104,105,106,107,108,109"));
    }
}
