package pub.lhp.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import org.apache.ibatis.annotations.Mapper;
import pub.lhp.infrastructure.persistent.po.Task;

import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 任务表，发送MQ
 * @since 2024/10/14 10:10
 */
@Mapper
public interface ITaskDao {

    void insert(Task task);

    @DBRouter
    void updateTaskSendMessageCompleted(Task task);

    @DBRouter
    void updateTaskSendMessageFail(Task task);

    List<Task> queryNoSendMessageTaskList();

}
