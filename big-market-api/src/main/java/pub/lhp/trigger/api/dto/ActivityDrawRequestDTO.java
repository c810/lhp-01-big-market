package pub.lhp.trigger.api.dto;

import lombok.Data;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 活动抽奖请求对象
 * @since 2024/10/14 12:01
 */
@Data
public class ActivityDrawRequestDTO {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 活动ID
     */
    private Long activityId;

}
