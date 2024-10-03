package pub.lhp.domain.strategy.service.annotation;

import pub.lhp.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 策略自定义枚举
 * @since 2024/10/2 22:12
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogicStrategy {
    /**
     * 用于指定逻辑策略的模式
     * 当你在类上使用 @LogicStrategy 注解时，你需要指定 logicMode 的值
     * 例如 @LogicStrategy(logicMode = DefaultLogicFactory.LogicModel.RULE_BLACKLIST)。
     * 这个注解的作用是为类指定一个逻辑模式，以便在运行时根据不同的逻辑模式执行相应的策略。
     * @return 逻辑模式
     */
    DefaultLogicFactory.LogicModel logicMode();
}
