package pub.lhp.infrastructure.dao.po;

import lombok.Data;

import java.util.Date;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖活动表 持久化对象
 * @since 2024/10/10 22:09
 */
@Data
public class RaffleActivity {

    /**
     * 自增ID
     */
    private Long id;

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
     * 抽奖策略ID
     */
    private Long strategyId;

    /**
     * 活动状态
     */
    private String state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
