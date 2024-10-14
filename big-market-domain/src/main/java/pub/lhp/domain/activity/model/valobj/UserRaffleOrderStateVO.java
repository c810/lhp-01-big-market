package pub.lhp.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 用户抽奖订单状态枚举
 * @since 2024/10/14 10:35
 */
@Getter
@AllArgsConstructor
public enum UserRaffleOrderStateVO {

    create("create", "创建"),
    used("used", "已使用"),
    cancel("cancel", "已作废"),
    ;

    private final String code;
    private final String desc;

}
