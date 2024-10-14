package pub.lhp.domain.task.model.entity;

import lombok.Data;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 任务实体对象
 * @since 2024/10/14 11:10
 */
@Data
public class TaskEntity {

    /** 活动ID */
    private String userId;
    /** 消息主题 */
    private String topic;
    /** 消息编号 */
    private String messageId;
    /** 消息主体 */
    private String message;

}
