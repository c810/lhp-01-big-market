package pub.lhp.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pub.lhp.domain.activity.model.valobj.ActivityStateVO;

import java.util.Date;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 活动实体对象
 * @since 2024/10/12 22:39
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityEntity {

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动描述
     */
    private String activityDesc;

    /**
     * 开始时间
     */
    private Date beginDateTime;

    /**
     * 结束时间
     */
    private Date endDateTime;

    /**
     * 活动参与次数配置
     */
    private Long activityCountId;

    /**
     * 抽奖策略ID
     */
    private Long strategyId;

    /**
     * 活动状态
     */
    private ActivityStateVO state;

}
