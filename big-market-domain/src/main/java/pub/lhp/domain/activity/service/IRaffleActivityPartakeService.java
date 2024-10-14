package pub.lhp.domain.activity.service;

import pub.lhp.domain.activity.model.entity.PartakeRaffleActivityEntity;
import pub.lhp.domain.activity.model.entity.UserRaffleOrderEntity;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖活动参与服务
 * @since 2024/10/14 10:40
 */
public interface IRaffleActivityPartakeService {

    /**
     * 创建抽奖单；用户参与抽奖活动，扣减活动账户库存，产生抽奖单。如存在未被使用的抽奖单则直接返回已存在的抽奖单。
     *
     * @param partakeRaffleActivityEntity 参与抽奖活动实体对象
     * @return 用户抽奖订单实体对象
     */
    UserRaffleOrderEntity createOrder(PartakeRaffleActivityEntity partakeRaffleActivityEntity);

}
