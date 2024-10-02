package pub.lhp.test.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pub.lhp.domain.strategy.service.armory.IStrategyArmory;

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
public class StrategyArmoryTest {

    @Resource
    private IStrategyArmory strategyArmory;

    /**
     * 测试装配
     */
    @Test
    public void testAssembleLotteryStrategy() {
        strategyArmory.assembleLotteryStrategy(100002L);
    }

    /**
     * 测试抽奖
     */
    @Test
    public void testGetRandomAwardId() {
        log.info("随机奖品ID: {}", strategyArmory.getRandomAwardId(100002L));
        log.info("随机奖品ID: {}", strategyArmory.getRandomAwardId(100002L));
        log.info("随机奖品ID: {}", strategyArmory.getRandomAwardId(100002L));
        log.info("随机奖品ID: {}", strategyArmory.getRandomAwardId(100002L));
        log.info("随机奖品ID: {}", strategyArmory.getRandomAwardId(100002L));
        log.info("随机奖品ID: {}", strategyArmory.getRandomAwardId(100002L));
        log.info("随机奖品ID: {}", strategyArmory.getRandomAwardId(100002L));
        log.info("随机奖品ID: {}", strategyArmory.getRandomAwardId(100002L));
        log.info("随机奖品ID: {}", strategyArmory.getRandomAwardId(100002L));
        log.info("随机奖品ID: {}", strategyArmory.getRandomAwardId(100002L));
    }
}
