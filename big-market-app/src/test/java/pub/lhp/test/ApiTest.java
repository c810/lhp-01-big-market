package pub.lhp.test;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMap;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pub.lhp.infrastructure.persistent.redis.IRedisService;
import pub.lhp.trigger.api.dto.RaffleAwardListRequestDTO;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Test
    public void test() {
        RaffleAwardListRequestDTO requestDTO = new RaffleAwardListRequestDTO();
        requestDTO.setUserId("lihaopeng");
        requestDTO.setActivityId(100301L);
        log.info(JSON.toJSONString(requestDTO));
    }

    private double convert(double min){
        double current = min;
        double max = 1;
        while (current < 1){
            current = current * 10;
            max = max * 10;
        }
        return max;
    }

}
