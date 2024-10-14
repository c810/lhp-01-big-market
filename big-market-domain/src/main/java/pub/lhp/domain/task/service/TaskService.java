package pub.lhp.domain.task.service;

import org.springframework.stereotype.Service;
import pub.lhp.domain.task.model.entity.TaskEntity;
import pub.lhp.domain.task.repository.ITaskRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 消息任务服务
 * @since 2024/10/14 11:12
 */
@Service
public class TaskService implements ITaskService {

    @Resource
    private ITaskRepository taskRepository;

    @Override
    public List<TaskEntity> queryNoSendMessageTaskList() {
        return taskRepository.queryNoSendMessageTaskList();
    }

    @Override
    public void sendMessage(TaskEntity taskEntity) {
        taskRepository.sendMessage(taskEntity);
    }

    @Override
    public void updateTaskSendMessageCompleted(String userId, String messageId) {
        taskRepository.updateTaskSendMessageCompleted(userId, messageId);
    }

    @Override
    public void updateTaskSendMessageFail(String userId, String messageId) {
        taskRepository.updateTaskSendMessageFail(userId, messageId);
    }

}
