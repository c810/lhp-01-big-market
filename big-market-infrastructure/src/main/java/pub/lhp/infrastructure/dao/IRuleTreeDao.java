package pub.lhp.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import pub.lhp.infrastructure.dao.po.RuleTree;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 规则树表DAO
 * @since 2024/10/4 17:09
 */
@Mapper
public interface IRuleTreeDao {

    RuleTree queryRuleTreeByTreeId(String treeId);

}
