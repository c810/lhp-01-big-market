package pub.lhp.domain.activity.service;

import org.springframework.stereotype.Service;
import pub.lhp.domain.activity.repository.IActivityRepository;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖活动服务
 * @since 2024/10/12 22:44
 */
@Service
public class RaffleActivityService extends AbstractRaffleActivity {

    public RaffleActivityService(IActivityRepository activityRepository) {
        super(activityRepository);
    }

}
