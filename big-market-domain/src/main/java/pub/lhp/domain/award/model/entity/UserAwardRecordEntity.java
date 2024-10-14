package pub.lhp.domain.award.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pub.lhp.domain.award.model.valobj.AwardStateVO;

import java.util.Date;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 用户中奖记录实体对象
 * @since 2024/10/14 11:07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAwardRecordEntity {

    /** 用户ID */
    private String userId;
    /** 活动ID */
    private Long activityId;
    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖订单ID【作为幂等使用】 */
    private String orderId;
    /** 奖品ID */
    private Integer awardId;
    /** 奖品标题（名称） */
    private String awardTitle;
    /** 中奖时间 */
    private Date awardTime;
    /** 奖品状态；create-创建、completed-发奖完成 */
    private AwardStateVO awardState;

}
