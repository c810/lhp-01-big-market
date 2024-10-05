package pub.lhp.trigger.api;

import pub.lhp.trigger.api.dto.RaffleAwardListRequestDTO;
import pub.lhp.trigger.api.dto.RaffleAwardListResponseDTO;
import pub.lhp.trigger.api.dto.RaffleRequestDTO;
import pub.lhp.trigger.api.dto.RaffleResponseDTO;
import pub.lhp.types.model.Response;

import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖服务接口
 * @since 2024/10/5 12:12
 */
public interface IRaffleService {

    /**
     * 策略装配接口
     *
     * @param strategyId 策略ID
     * @return 装配结果
     */
    Response<Boolean> strategyArmory(Long strategyId);

    /**
     * 查询抽奖奖品列表配置
     *
     * @param requestDTO 抽奖奖品列表查询请求参数
     * @return 奖品列表数据
     */
    Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(RaffleAwardListRequestDTO requestDTO);

    /**
     * 随机抽奖接口
     *
     * @param requestDTO 请求参数
     * @return 抽奖结果
     */
    Response<RaffleResponseDTO> randomRaffle(RaffleRequestDTO requestDTO);

}
