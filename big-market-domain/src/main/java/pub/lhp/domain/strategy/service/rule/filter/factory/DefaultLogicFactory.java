package pub.lhp.domain.strategy.service.rule.filter.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import pub.lhp.domain.strategy.model.entity.RuleActionEntity;
import pub.lhp.domain.strategy.service.annotation.LogicStrategy;
import pub.lhp.domain.strategy.service.rule.ILogicFilter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 规则工厂，管理和提供基于逻辑模式的逻辑过滤器
 * @since 2024/10/2 22:11
 */
@Service
public class DefaultLogicFactory {
    /**
     * logicFilterMap 是一个并发哈希映射（ConcurrentHashMap），用于存储逻辑过滤器（ILogicFilter），以逻辑模式代码（String）为键。
     * 这个映射在运行时根据不同的逻辑模式执行相应的策略。
     */
    public Map<String, ILogicFilter<?>> logicFilterMap = new ConcurrentHashMap<>();

    /**
     * 这个方法是 DefaultLogicFactory 类的构造函数。它的作用是扫描传入的 logicFilters 列表中的所有类，
     * 查找带有 @LogicStrategy 注解的类，并将这些类注册到 logicFilterMap 中。
     * 这样，在运行时可以根据不同的逻辑模式代码从 logicFilterMap 中获取相应的逻辑过滤器类，并执行相应的策略。
     * @param logicFilters 逻辑过滤器列表
     */
    public DefaultLogicFactory(List<ILogicFilter<?>> logicFilters) {
        // 遍历 logicFilters 列表中的每个逻辑过滤器类
        logicFilters.forEach(logic -> {
            // 使用 AnnotationUtils.findAnnotation 方法查找每个类上的 @LogicStrategy 注解
            LogicStrategy strategy = AnnotationUtils.findAnnotation(logic.getClass(), LogicStrategy.class);
            if (null != strategy) {
                // 如果找到了 @LogicStrategy 注解，则将该类的逻辑模式代码作为键，逻辑过滤器类作为值，存储到 logicFilterMap 中
                logicFilterMap.put(strategy.logicMode().getCode(), logic);
            }
        });
    }

    /**
     * 将 logicFilterMap 转换为一个通用类型的映射，并返回该映射。
     * 具体来说，它将 logicFilterMap 中的值强制转换为 ILogicFilter<T> 类型的值，并返回这个映射。
     * 这个方法的主要用途是提供一个类型安全的方式来访问 logicFilterMap，
     * 以便在运行时根据不同的逻辑模式代码获取相应的逻辑过滤器类，并执行相应的策略。
     * @return 逻辑过滤器映射
     * @param <T> 泛型
     */
    public <T extends RuleActionEntity.RaffleEntity> Map<String, ILogicFilter<T>> openLogicFilter() {
        return (Map<String, ILogicFilter<T>>) (Map<?, ?>) logicFilterMap;
    }

    @Getter
    @AllArgsConstructor
    public enum LogicModel {

        RULE_WIGHT("rule_weight", "【抽奖前规则】根据抽奖权重返回可抽奖范围KEY", "before"),
        RULE_BLACKLIST("rule_blacklist", "【抽奖前规则】黑名单规则过滤，命中黑名单则直接返回", "before"),
        RULE_LOCK("rule_lock", "【抽奖中规则】抽奖n次后，对应奖品可解锁抽奖", "center"),
        RULE_LUCK_AWARD("rule_luck_award", "【抽奖后规则】幸运奖兜底", "after"),
        ;

        private final String code;
        private final String info;
        private final String type;

        public static boolean isCenter(String code){
            return "center".equals(LogicModel.valueOf(code.toUpperCase()).type);
        }

        public static boolean isAfter(String code){
            return "after".equals(LogicModel.valueOf(code.toUpperCase()).type);
        }
    }
}
