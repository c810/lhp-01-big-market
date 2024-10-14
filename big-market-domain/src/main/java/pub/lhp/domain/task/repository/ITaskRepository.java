package pub.lhp.domain.task.repository;

import pub.lhp.domain.task.model.entity.TaskEntity;

import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 任务服务仓储接口
 * @since 2024/10/14 11:11
 */
public interface ITaskRepository {

    List<TaskEntity> queryNoSendMessageTaskList();

    void sendMessage(TaskEntity taskEntity);

    void updateTaskSendMessageCompleted(String userId, String messageId);

    void updateTaskSendMessageFail(String userId, String messageId);

}
