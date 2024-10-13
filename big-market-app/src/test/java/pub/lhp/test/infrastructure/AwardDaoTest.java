package pub.lhp.test.infrastructure;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pub.lhp.infrastructure.persistent.dao.IAwardDao;
import pub.lhp.infrastructure.persistent.po.Award;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 奖品持久化 单元测试
 * @since 2024/10/1 10:53
 */
// @Slf4j: 这是Lombok库提供的一个注解，它会自动生成一个名为log的Logger对象，方便在类中进行日志记录。
// @RunWith(SpringRunner.class): 指定运行器为SpringRunner，这样可以支持Spring TestContext Framework的功能。
// @SpringBootTest: 表示这是一个Spring Boot应用的集成测试，它将启动整个Spring应用上下文来执行测试。
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AwardDaoTest {

    // 注入奖品持久化 DAO
    @Resource
    private IAwardDao awardDao;

    @Test
    public void test_queryAwardList() {
        List<Award> awards = awardDao.queryAwardList();
        // 将奖品列表转换成JSON字符串格式输出
        log.info("奖品列表: {}", JSON.toJSONString(awards));
    }
}
