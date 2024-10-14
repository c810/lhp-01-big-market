package pub.lhp.domain.task.service;

import pub.lhp.domain.task.model.entity.TaskEntity;

import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 消息任务服务接口
 * @since 2024/10/14 11:12
 */
public interface ITaskService {

    /**
     * 查询发送MQ失败和超时1分钟未发送的MQ
     *
     * @return 未发送的任务消息列表10条
     */
    List<TaskEntity> queryNoSendMessageTaskList();

    void sendMessage(TaskEntity taskEntity);

    void updateTaskSendMessageCompleted(String userId, String messageId);

    void updateTaskSendMessageFail(String userId, String messageId);

}
