package pub.lhp.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import pub.lhp.infrastructure.persistent.po.RuleTreeNodeLine;

import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 规则树节点连线表DAO
 * @since 2024/10/4 17:09
 */
@Mapper
public interface IRuleTreeNodeLineDao {

    List<RuleTreeNodeLine> queryRuleTreeNodeLineListByTreeId(String treeId);

}
