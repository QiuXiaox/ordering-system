package my.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.domain.Dish;
import my.domain.Order;
import my.domain.OrderItem;
import my.mapper.DishMapper;
import my.mapper.OrderItemMapper;
import my.mapper.OrderMapper;
import my.vo.CartItemVo;
import my.vo.CartVo;
import my.vo.ClientVo;

@Service
public class CartService {
	
	@Autowired
	DishMapper dishMapper;
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	OrderItemMapper orderItemMapper;
	
	/*把用户想买的书籍添加到当前用户的购物车上*/
	public void buyDish(Long id, Integer quantity, CartVo cart) {
		/*Dish dish = dishMapper.selectByPrimaryKey(id);
		cart.addDish(dish);*/
		Dish dish = dishMapper.selectByPrimaryKey(id);
		cart.addDish(dish);
		cart.getDishMap().get(id).setQuantity(quantity);
	}
	
	/*用户要在购物车中删除某个购物项*/
	public void deleteDish(Long id, CartVo cart) {
		if(cart == null) 
			return;
		cart.getDishMap().remove(id);
	}
	
	/* 更新购买数量*/
	public void updateQuantity(Long id, CartVo cart, Integer quantity) {
		if (cart == null) {
			return;
		}
		cart.getDishMap().get(id).setQuantity(quantity);
	}
	
	/*清空购物车*/
	public void clearCart(CartVo cart) {
		if (cart == null) {
		    return;
		 }
		cart.getDishMap().clear();
	}
	
	/*结算购物车*/
	public int countCart(CartVo cart,ClientVo user) {
		if (cart == null || cart.getDishMap().isEmpty()) {
		    return 0;
		 }
		
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		
		Order order = new Order();
		order.setClientid(user.getId());
		order.setTotalPrice(new BigDecimal(cart.getPrice()));
		order.setCreatetime(timeStamp);
		order.setState("1");
		orderMapper.insertSelective(order);
		
		for(Map.Entry<Long, CartItemVo> item : cart.getDishMap().entrySet()) {
			CartItemVo cartItem = item.getValue();
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(orderMapper.getMaxId());
			orderItem.setDishId(cartItem.getDish().getId());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setPrice(new BigDecimal(cartItem.getPrice()));
			orderItemMapper.insertSelective(orderItem);
		}
		return 1;
	}
}