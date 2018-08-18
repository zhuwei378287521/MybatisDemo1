package cn.zjw.mybaits.mapper;

import java.util.List;

import cn.zjw.mybaits.mapper.po.OrdersCustom;

public interface OrdersCustomMapper {
	public List<OrdersCustom> findOrdersList() throws Exception;
}
