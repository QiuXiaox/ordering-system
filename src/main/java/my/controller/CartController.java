package my.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.api.CartApi;
import my.service.CartService;
import my.util.ResponseVo;
import my.vo.CartVo;
import my.vo.ClientVo;

@RestController
public class CartController implements CartApi{

	@Autowired
	CartService cartService;
	
	@Override
	public ResponseVo<CartVo> queryItem(HttpServletRequest req) {
		ResponseVo<CartVo> result = new ResponseVo<CartVo>();
		try {
			CartVo cart = (CartVo)req.getSession().getAttribute("cart");
			if(cart == null) {
				cart = new CartVo();
				req.getSession().setAttribute("cart", cart);
			}
			result.setData(cart);
			result.setMsg("查询成功");
			result.setResult("SUCCESS");
			result.setStatus(200);
			return result;
		}catch (Exception e) {
			result.setMsg("查询失败");
			result.setResult("ERROR");
			result.setStatus(400);
			return result;
		}
	}

	@Override
	public ResponseVo<CartVo> addItem(@RequestParam("id") Long id, @RequestParam("quantity") Integer quantity, HttpServletRequest request) {
		ResponseVo<CartVo> result = new ResponseVo<CartVo>();
		try {
			CartVo cart = (CartVo) request.getSession().getAttribute("cart");
			if(cart == null) {
				cart = new CartVo();
				request.getSession().setAttribute("cart", cart);
			}
			cartService.buyDish(id, quantity, cart);
			result.setData(cart);
			result.setMsg("添加成功");
			result.setResult("SUCCESS");
			result.setStatus(200);		
			return result;
		}catch (Exception e) {
			result.setMsg("添加失败");
			result.setResult("ERROR");
			result.setStatus(400);		
			return result;
		}
	}

	@Override
	public ResponseVo<CartVo> delItem(@RequestParam("id") Long id, HttpServletRequest request) {
		ResponseVo<CartVo> result = new ResponseVo<CartVo>();
		try{
			CartVo cart = (CartVo) request.getSession().getAttribute("cart");
			cartService.deleteDish(id, cart);
			
			result.setData(cart);
			result.setMsg("删除成功");
			result.setResult("SUCCESS");
			result.setStatus(200);	
			return result;
		}catch (Exception e) {
			result.setMsg("删除失败");
			result.setResult("ERROR");
			result.setStatus(400);	
			return result;
		}
	}

	@Override
	public ResponseVo<CartVo> modItem(@RequestParam("id") Long id, @RequestParam("quantity") Integer quantity, HttpServletRequest request) {
		ResponseVo<CartVo> result = new ResponseVo<CartVo>();
		try{
			CartVo cart = (CartVo) request.getSession().getAttribute("cart");
			cartService.updateQuantity(id, cart, quantity);
			result.setData(cart);
			result.setMsg("修改成功");
			result.setResult("SUCCESS");
			result.setStatus(200);	
			return result;
		}catch (Exception e) {
			result.setMsg("修改失败");
			result.setResult("ERROR");
			result.setStatus(400);	
			return result;
		}
	}

	@Override
	public ResponseVo<CartVo> clearItem(HttpServletRequest request) {
		ResponseVo<CartVo> result = new ResponseVo<CartVo>();
		try{
			CartVo cart = (CartVo) request.getSession().getAttribute("cart");
			cartService.clearCart(cart);
			result.setData(cart);
			result.setMsg("清空成功");
			result.setResult("SUCCESS");
			result.setStatus(200);	
			return result;
		}catch (Exception e) {
			result.setMsg("清空失败");
			result.setResult("ERROR");
			result.setStatus(400);	
			return result;
		}
	}

	@Override
	public ResponseVo<CartVo> countItem(HttpServletRequest request) {
		ResponseVo<CartVo> result = new ResponseVo<CartVo>();
		try{
			CartVo cart = (CartVo) request.getSession().getAttribute("cart");
			ClientVo user = (ClientVo)request.getSession().getAttribute("user");
			int r =  cartService.countCart(cart, user);
			if(r == 0) {
				result.setData(cart);
				result.setMsg("您的购物车为空");
				result.setResult("EMPTY");
				result.setStatus(200);	
				return result;
			}else{
				result.setData(cart);
				result.setMsg("结算成功");
				result.setResult("SUCCESS");
				result.setStatus(200);
				cart.getDishMap().clear();
				return result;
			}
		}catch (Exception e) {
			result.setMsg("结算失败");
			result.setResult("ERROR");
			result.setStatus(400);	
			return result;
		}
	}
	
	
}
