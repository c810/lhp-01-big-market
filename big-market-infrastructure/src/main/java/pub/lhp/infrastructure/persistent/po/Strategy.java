package pub.lhp.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖策略
 * @since 2024/9/28 23:24
 */
@Data
public class Strategy {
    /** 自增ID */
    private Long id;
    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖策略描述 */
    private String strategyDesc;
    /** 抽奖规则模型 */
    private String ruleModels;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
}
