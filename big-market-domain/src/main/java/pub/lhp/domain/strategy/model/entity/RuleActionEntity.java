package pub.lhp.domain.strategy.model.entity;

import lombok.*;
import pub.lhp.domain.strategy.model.vo.RuleLogicCheckTypeVO;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 规则动作实体
 * @since 2024/10/2 21:56
 */
// 传来的泛型T必须是RuleActionEntity.RaffleEntity的子类
// 也就是，只能传来我们定义的RaffleEntity或其子类，别的类都不行
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleActionEntity<T extends RuleActionEntity.RaffleEntity> {

    private String code = RuleLogicCheckTypeVO.ALLOW.getCode();
    private String info = RuleLogicCheckTypeVO.ALLOW.getInfo();
    private String ruleModel;
    private T data;



    static public class RaffleEntity {

    }

    /**
     * 抽奖前
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static public class RaffleBeforeEntity extends RaffleEntity {
        /**
         * 策略ID
         */
        private Long strategyId;

        /**
         * 权重值Key：用于抽奖时可以选择权重抽奖
         */
        private String ruleWeightValueKey;

        /**
         * 奖品ID
         */
        private Integer awardId;
    }

    /**
     * 抽奖中
     */
    static public class RaffleInEntity extends RaffleEntity {

    }

    /**
     * 抽奖后
     */
    static public class RaffleAfterEntity extends RaffleEntity {

    }
}
