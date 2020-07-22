package xyz.idiosoul.fair.order.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.idiosoul.fair.order.vo.ShoppingGroupOfShopVO;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    /**
     * 获取购物车
     *
     * @param buyerId
     * @return
     */
    List<ShoppingGroupOfShopVO> getShoppingCartByBuyerIdAndDataSpace(@Param("buyerId") int buyerId);

    List<ShoppingGroupOfShopVO> getShoppingCartByBuyerItemIds(@Param("itemIds") List<Long> itemIds);
}
