package pub.lhp.domain.award.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 任务状态值对象
 * @since 2024/10/14 11:08
 */
@Getter
@AllArgsConstructor
public enum TaskStateVO {

    create("create", "创建"),
    complete("complete", "发送完成"),
    fail("fail", "发送失败"),
    ;

    private final String code;
    private final String desc;

}
