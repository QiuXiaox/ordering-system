package my.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.api.OrderApi;
import my.service.OrderService;
import my.util.BeanUtil;
import my.vo.DishFlowVo;
import my.vo.OrderVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RestController
public class OrderController implements OrderApi {
	
	@Autowired
	OrderService orderService;
	
	@Override
	public PageVo<OrderVo> queryOrder(@RequestBody OrderVo vo){
		List<OrderVo> list = orderService.queryOrder(vo);
		return BeanUtil.page(list);	
	}

	@Override
	public ResponseVo<OrderVo> addOrder(@RequestBody OrderVo vo){
		ResponseVo<OrderVo> result = new ResponseVo<OrderVo>();
		try {
			orderService.addOrder(vo);
			result.setResult("SUCCESS");
			result.setStatus(200);
			result.setMsg("成功");
			return result;
		}catch(Exception e) {
			result.setResult("ERROR");
			result.setStatus(400);
			result.setMsg("失败");
			return result;
		}
	}
	
	@Override
	public OrderVo findOrderById(@RequestParam("id") Long id){
		return orderService.findOrderById(id);
	}
	
	@Override
	public ResponseVo<OrderVo> updateOrderById(@RequestBody OrderVo vo){
		ResponseVo<OrderVo> result = new ResponseVo<OrderVo>();
		try {
			orderService.updateOrderById(vo);
			result.setResult("SUCCESS");
			result.setStatus(200);
			result.setMsg("成功");
			return result;
		}catch(Exception e) {
			result.setResult("ERROR");
			result.setStatus(400);
			result.setMsg("失败");
			return result;
		}
	}
	
	@Override
	public ResponseVo<OrderVo> delOrderById(@RequestParam("id") Long id){
		ResponseVo<OrderVo> result = new ResponseVo<OrderVo>();
		try {
			orderService.delOrderById(id);
			result.setResult("SUCCESS");
			result.setStatus(200);
			result.setMsg("成功");
			return result;
		}catch(Exception e) {
			result.setResult("ERROR");
			result.setStatus(400);
			result.setMsg("失败");
			return result;
		}
	}

	@Override
	public PageVo<DishFlowVo> queryDishFlow(@RequestBody DishFlowVo vo) {
		List<DishFlowVo> list = orderService.dishFlow(vo);
		return BeanUtil.page(list);	
	}
	
}
