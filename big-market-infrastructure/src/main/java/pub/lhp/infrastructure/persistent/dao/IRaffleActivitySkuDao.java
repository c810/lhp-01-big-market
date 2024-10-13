package pub.lhp.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import pub.lhp.infrastructure.persistent.po.RaffleActivitySku;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 商品sku dao
 * @since 2024/10/12 22:18
 */
@Mapper
public interface IRaffleActivitySkuDao {

    RaffleActivitySku queryActivitySku(Long sku);

    void updateActivitySkuStock(Long sku);

    void clearActivitySkuStock(Long sku);

}
