package pub.lhp.trigger.api;

import pub.lhp.trigger.api.dto.ActivityDrawRequestDTO;
import pub.lhp.trigger.api.dto.ActivityDrawResponseDTO;
import pub.lhp.types.model.Response;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖活动服务
 * @since 2024/10/14 12:00
 */
public interface IRaffleActivityService {

    /**
     * 活动装配，数据预热缓存
     * @param activityId 活动ID
     * @return 装配结果
     */
    Response<Boolean> armory(Long activityId);

    /**
     * 活动抽奖接口
     * @param request 请求对象
     * @return 返回结果
     */
    Response<ActivityDrawResponseDTO> draw(ActivityDrawRequestDTO request);

}
