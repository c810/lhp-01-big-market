package pub.lhp.trigger.api.dto;

import lombok.Data;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖奖品列表，请求对象
 * @since 2024/10/5 12:09
 */
@Data
public class RaffleAwardListRequestDTO {

    // 抽奖策略ID
    private Long strategyId;

}
