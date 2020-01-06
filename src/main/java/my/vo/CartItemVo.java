package my.vo;

import my.domain.Dish;

public class CartItemVo{
	private Dish dish;
	private int quantity;
	//该购物项（菜品--不一定只有一道）的价钱应该等于菜品的数量*价格
	private double price;
	
	public double getPrice() {
		return dish.getPrice().doubleValue() * this.quantity;
	}
	
	public Dish getDish() {
		return dish;
	}
	
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}
