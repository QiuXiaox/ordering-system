package my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.domain.OrderItem;
import my.mapper.OrderItemMapper;
import my.util.BeanUtil;
import my.vo.OrderItemVo;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	
	@Autowired
	OrderItemMapper orderItemMapper;
	
	@Override
	public List<OrderItemVo> queryOrderItem(OrderItemVo vo){
		//vo.setStatus("ENABLED");
		return orderItemMapper.queryOrderItem(vo);
	}

	@Override
	public int addOrderItem(OrderItemVo vo){
		OrderItem po = BeanUtil.copy(vo, OrderItem.class);
		//po.setStatus("ENABLED");
		//po.setUpdateTime(new Date());
		return orderItemMapper.insertSelective(po);
	}
	
	@Override
	public OrderItemVo findOrderItemById(Long id){
		OrderItem po = orderItemMapper.selectByPrimaryKey(id);
		return BeanUtil.copy(po, OrderItemVo.class);
	}
	
	@Override
	public int updateOrderItemById(OrderItemVo vo){
		OrderItem po = BeanUtil.copy(vo, OrderItem.class);
		//po.setUpdateTime(new Date());
		return orderItemMapper.updateByPrimaryKeySelective(po);
	}
	
	@Override
	public int delOrderItemById(Long id){
		//OrderItem po = new OrderItem();
		//po.setId(id);
		//po.setStatus("DISABLED");
		//po.setUpdateTime(new Date());
		return orderItemMapper.deleteByPrimaryKey(id);
	}
	
}
