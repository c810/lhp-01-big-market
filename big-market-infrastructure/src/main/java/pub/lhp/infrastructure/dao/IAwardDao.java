package pub.lhp.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import pub.lhp.infrastructure.dao.po.Award;

import java.util.List;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 奖品表 DAO
 * @since 2024/9/28 23:33
 */
@Mapper
public interface IAwardDao {
    List<Award> queryAwardList();
}
