package pub.lhp.infrastructure.persistent.repository;

import org.springframework.stereotype.Repository;
import pub.lhp.domain.task.model.entity.TaskEntity;
import pub.lhp.domain.task.repository.ITaskRepository;
import pub.lhp.infrastructure.event.EventPublisher;
import pub.lhp.infrastructure.persistent.dao.ITaskDao;
import pub.lhp.infrastructure.persistent.po.Task;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 任务服务仓储实现
 * @since 2024/10/14 11:18
 */
@Repository
public class TaskRepository implements ITaskRepository {

    @Resource
    private ITaskDao taskDao;
    @Resource
    private EventPublisher eventPublisher;

    @Override
    public List<TaskEntity> queryNoSendMessageTaskList() {
        List<Task> tasks = taskDao.queryNoSendMessageTaskList();
        List<TaskEntity> taskEntities = new ArrayList<>(tasks.size());
        for (Task task : tasks) {
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setUserId(task.getUserId());
            taskEntity.setTopic(task.getTopic());
            taskEntity.setMessageId(task.getMessageId());
            taskEntity.setMessage(task.getMessage());
            taskEntities.add(taskEntity);
        }
        return taskEntities;
    }

    @Override
    public void sendMessage(TaskEntity taskEntity) {
        eventPublisher.publish(taskEntity.getTopic(), taskEntity.getMessage());
    }

    @Override
    public void updateTaskSendMessageCompleted(String userId, String messageId) {
        Task taskReq = new Task();
        taskReq.setUserId(userId);
        taskReq.setMessageId(messageId);
        taskDao.updateTaskSendMessageCompleted(taskReq);
    }

    @Override
    public void updateTaskSendMessageFail(String userId, String messageId) {
        Task taskReq = new Task();
        taskReq.setUserId(userId);
        taskReq.setMessageId(messageId);
        taskDao.updateTaskSendMessageFail(taskReq);
    }

}
