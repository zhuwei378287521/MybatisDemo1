package cn.zjw.mybaits.mapper.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.zjw.mybaits.mapper.OrdersCustomMapper;
import cn.zjw.mybaits.mapper.po.OrdersCustom;
import junit.framework.TestCase;

public class OrderCustromTest extends TestCase {
	public SqlSessionFactory sqlSessionFactory;

	protected void setUp() throws Exception {
		// mybatis配置文件
		String resource = "sqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 使用SqlSessionFactoryBuilder创建sessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testfindOrdersList() throws Exception {
		// 获取session
		SqlSession session = sqlSessionFactory.openSession();
		// 获限mapper接口实例
		OrdersCustomMapper ordersCustomMapper = session.getMapper(OrdersCustomMapper.class);
		// 查询订单信息
		List<OrdersCustom> list = ordersCustomMapper.findOrdersList();
		System.out.println(list);
		for (OrdersCustom ordersCustom : list) {
			System.out.println("用户"+ordersCustom.getUsername()+" 订单id："+ordersCustom.getId());
		}
		// 关闭session
		session.close();

	}
}
