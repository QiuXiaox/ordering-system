package my.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.vo.DishFlowVo;
import my.vo.OrderVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RequestMapping("/order")
public interface OrderApi {
	
	/**
	 * 食品流向
	 * @createTime: 2019/12/13 15:30:17
	 * @author: wu.kaibin
	 */
	@RequestMapping("/dishFlow")
	PageVo<DishFlowVo> queryDishFlow(@RequestBody DishFlowVo vo);
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/13 15:30:17
	 * @author: wu.kaibin
	 */
	@RequestMapping("/queryOrder")
	PageVo<OrderVo> queryOrder(@RequestBody OrderVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/13 15:30:17
	 * @author: wu.kaibin
	 */
	@RequestMapping("/addOrder")
	ResponseVo<OrderVo> addOrder(@RequestBody OrderVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/13 15:30:17
	 * @author: wu.kaibin
	 */
	@RequestMapping("/findOrderById")
	OrderVo findOrderById(@RequestParam("id") Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/13 15:30:17
	 * @author: wu.kaibin
	 */
	@RequestMapping("/updateOrderById")
	ResponseVo<OrderVo> updateOrderById(@RequestBody OrderVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/13 15:30:17
	 * @author: wu.kaibin
	 */
	@RequestMapping("/delOrderById")
	ResponseVo<OrderVo> delOrderById(@RequestParam("id") Long id);
	
}
