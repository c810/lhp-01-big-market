package pub.lhp.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import pub.lhp.infrastructure.persistent.po.RuleTreeNode;

import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 规则树节点表DAO
 * @since 2024/10/4 17:09
 */
@Mapper
public interface IRuleTreeNodeDao {

    List<RuleTreeNode> queryRuleTreeNodeListByTreeId(String treeId);

    List<RuleTreeNode> queryRuleLocks(String[] treeIds);

}
