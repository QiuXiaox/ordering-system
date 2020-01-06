package my.vo;

import java.util.LinkedHashMap;
import java.util.Map;

import my.domain.Dish;

public class CartVo {
	//关键字是caipin的id，值是cai'pin 
	private Map<Long, CartItemVo> dishMap = new LinkedHashMap<>();
	
	//代表购物车的总价
	private double price;
	
	//把购物项（用户传递进来的菜品）加入到购物车里边去，也应该是购物车的功能
	public void addDish(Dish dish) {
		//获取得到购物项
		CartItemVo cartItem  = dishMap.get(dish.getId());
		
		//判断购物车是否存在购物项，如果不存在
		if(cartItem == null) {
			//创建这个购物项对象
			cartItem = new CartItemVo();
			//将用户传递过来的菜品作为购物项
			cartItem.setDish(dish);
			//吧该购物项的数量设置为1
			cartItem.setQuantity(1);
			//ba购物车项加入到购物车去
			dishMap.put(dish.getId(), cartItem);
		}else {
			//如果存在该购物项
			cartItem.setQuantity(cartItem.getQuantity() + 1);
		}
	}
	
	//购物车的总价就是所有购物项的加个加起来
	public double getPrice() {
		double totalPrice = 0;
		
		for(Map.Entry<Long, CartItemVo> item : dishMap.entrySet()) {
			//得到每个购物项
			CartItemVo cartItem = item.getValue();
			//将每个购物项的钱加起来，就是购物车的总价了！
			totalPrice += cartItem.getPrice();
		}
		return totalPrice;
	}
	
	public Map<Long, CartItemVo> getDishMap() {
		return dishMap;
	}
	
	public void setDishMap(Map<Long, CartItemVo> dishMap) {
		this.dishMap = dishMap;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}
