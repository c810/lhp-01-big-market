package pub.lhp.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 策略奖品实体
 * @since 2024/10/1 17:06
 */
// 用于生成一个构建器模式（Builder Pattern）的实现。
// 通过@Builder，你可以使用流畅接口（Fluent Interface）风格来创建对象实例，而不需要直接调用构造函数。
@Data
@Builder
@AllArgsConstructor // 全参构造器
@NoArgsConstructor // 无参构造器
public class StrategyAwardEntity {
    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖奖品ID - 内部流转使用 */
    private Integer awardId;
    /** 抽奖奖品标题 */
    private String awardTitle;
    /** 抽奖奖品副标题 */
    private String awardSubtitle;
    /** 奖品库存总量 */
    private Integer awardCount;
    /** 奖品库存剩余 */
    private Integer awardCountSurplus;
    /** 奖品中奖概率 */
    private BigDecimal awardRate;
    /** 排序 */
    private Integer sort;
    /** 规则模型，rule配置的模型同步到此表，便于使用 */
    private String ruleModels;
}
