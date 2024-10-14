package pub.lhp.domain.activity.model.entity;

import lombok.Data;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 参与抽奖活动实体对象
 * @since 2024/10/14 10:34
 */
@Data
public class PartakeRaffleActivityEntity {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 活动ID
     */
    private Long activityId;

}
