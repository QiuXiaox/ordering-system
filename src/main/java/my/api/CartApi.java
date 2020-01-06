package my.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.util.ResponseVo;
import my.vo.CartVo;

@RequestMapping("/cart")
public interface CartApi {
	
	/**
	 * 查看购物车
	 */
	@RequestMapping("/queryItem")
	ResponseVo<CartVo> queryItem(HttpServletRequest req);
	
	/**
	 * 添加到购物车
	 */
	@RequestMapping("/addItem")
	ResponseVo<CartVo> addItem(@RequestParam("id") Long id, @RequestParam("quantity") Integer quantity, HttpServletRequest req);
	
	/**
	 * 从购物车删除
	 */
	@RequestMapping("/delItem")
	ResponseVo<CartVo> delItem(@RequestParam("id") Long id, HttpServletRequest req);
	
	/**
	 * 修改数量
	 */
	@RequestMapping("/modItem")
	ResponseVo<CartVo> modItem(@RequestParam("id") Long id, @RequestParam("quantity") Integer quantity, HttpServletRequest req);
	
	/**
	 * 清空购物车
	 */
	@RequestMapping("/clearItem")
	ResponseVo<CartVo> clearItem(HttpServletRequest req);
	
	/**
	 * 结算购物车
	 */
	@RequestMapping("countItem")
	ResponseVo<CartVo> countItem(HttpServletRequest req);
}
