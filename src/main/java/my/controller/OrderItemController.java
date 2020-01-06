package my.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.api.OrderItemApi;
import my.service.OrderItemService;
import my.util.BeanUtil;
import my.vo.OrderItemVo;
import my.util.PageVo;

@RestController
public class OrderItemController implements OrderItemApi {
	
	@Autowired
	OrderItemService orderItemService;
	
	@Override
	public PageVo<OrderItemVo> queryOrderItem(@RequestBody OrderItemVo vo){
		List<OrderItemVo> list = orderItemService.queryOrderItem(vo);
		return BeanUtil.page(list);	
	}

	@Override
	public int addOrderItem(@RequestBody OrderItemVo vo){
		return orderItemService.addOrderItem(vo);
	}
	
	@Override
	public OrderItemVo findOrderItemById(@RequestParam("id") Long id){
		return orderItemService.findOrderItemById(id);
	}
	
	@Override
	public int updateOrderItemById(@RequestBody OrderItemVo vo){
		return orderItemService.updateOrderItemById(vo);
	}
	
	@Override
	public int delOrderItemById(@RequestParam("id") Long id){
		return orderItemService.delOrderItemById(id);
	}
	
}
