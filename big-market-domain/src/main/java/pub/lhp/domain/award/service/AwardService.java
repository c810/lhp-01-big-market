package pub.lhp.domain.award.service;

import org.springframework.stereotype.Service;
import pub.lhp.domain.award.event.SendAwardMessageEvent;
import pub.lhp.domain.award.model.aggregate.UserAwardRecordAggregate;
import pub.lhp.domain.award.model.entity.TaskEntity;
import pub.lhp.domain.award.model.entity.UserAwardRecordEntity;
import pub.lhp.domain.award.model.valobj.TaskStateVO;
import pub.lhp.domain.award.repository.IAwardRepository;
import pub.lhp.types.event.BaseEvent;

import javax.annotation.Resource;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 奖品服务
 * @since 2024/10/14 11:09
 */
@Service
public class AwardService implements IAwardService {

    @Resource
    private IAwardRepository awardRepository;
    @Resource
    private SendAwardMessageEvent sendAwardMessageEvent;

    @Override
    public void saveUserAwardRecord(UserAwardRecordEntity userAwardRecordEntity) {
        // 构建消息对象
        SendAwardMessageEvent.SendAwardMessage sendAwardMessage = new SendAwardMessageEvent.SendAwardMessage();
        sendAwardMessage.setUserId(userAwardRecordEntity.getUserId());
        sendAwardMessage.setAwardId(userAwardRecordEntity.getAwardId());
        sendAwardMessage.setAwardTitle(userAwardRecordEntity.getAwardTitle());

        BaseEvent.EventMessage<SendAwardMessageEvent.SendAwardMessage> sendAwardMessageEventMessage = sendAwardMessageEvent.buildEventMessage(sendAwardMessage);

        // 构建任务对象
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setUserId(userAwardRecordEntity.getUserId());
        taskEntity.setTopic(sendAwardMessageEvent.topic());
        taskEntity.setMessageId(sendAwardMessageEventMessage.getId());
        taskEntity.setMessage(sendAwardMessageEventMessage);
        taskEntity.setState(TaskStateVO.create);

        // 构建聚合对象
        UserAwardRecordAggregate userAwardRecordAggregate = UserAwardRecordAggregate.builder()
                .taskEntity(taskEntity)
                .userAwardRecordEntity(userAwardRecordEntity)
                .build();

        // 存储聚合对象 - 一个事务下，用户的中奖记录
        awardRepository.saveUserAwardRecord(userAwardRecordAggregate);
    }

}
