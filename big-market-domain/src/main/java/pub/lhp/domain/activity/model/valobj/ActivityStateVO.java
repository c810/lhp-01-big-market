package pub.lhp.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 活动状态值对象
 * @since 2024/10/12 22:42
 */
@Getter
@AllArgsConstructor
public enum ActivityStateVO {

    create("create", "创建");

    private final String code;
    private final String desc;

}
