package my.service;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.domain.Order;
import my.mapper.OrderMapper;
import my.util.BeanUtil;
import my.vo.DishFlowVo;
import my.vo.OrderVo;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderMapper orderMapper;
	
	@Override
	public List<OrderVo> queryOrder(OrderVo vo){
		//vo.setStatus("ENABLED");
		return orderMapper.queryOrder(vo);
	}

	@Override
	public int addOrder(OrderVo vo){
		Order po = BeanUtil.copy(vo, Order.class);
		//po.setStatus("ENABLED");
		//po.setUpdateTime(new Date());
		return orderMapper.insertSelective(po);
	}
	
	@Override
	public OrderVo findOrderById(Long id){
		Order po = orderMapper.selectByPrimaryKey(id);
		return BeanUtil.copy(po, OrderVo.class);
	}
	
	@Override
	public int updateOrderById(OrderVo vo){
		Order po = BeanUtil.copy(vo, Order.class);
		//po.setUpdateTime(new Date());
		return orderMapper.updateByPrimaryKeySelective(po);
	}
	
	@Override
	public int delOrderById(Long id){
		//Order po = new Order();
		//po.setId(id);
		//po.setStatus("DISABLED");
		//po.setUpdateTime(new Date());
		return orderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<DishFlowVo> dishFlow(DishFlowVo vo) {
		return orderMapper.queryDishFlow(vo);
	}
	
}
