package my.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.vo.OrderItemVo;
import my.util.PageVo;

@RequestMapping("/orderItem")
public interface OrderItemApi {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/13 15:30:30
	 * @author: wu.kaibin
	 */
	@RequestMapping("/queryOrderItem")
	PageVo<OrderItemVo> queryOrderItem(@RequestBody OrderItemVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/13 15:30:30
	 * @author: wu.kaibin
	 */
	@RequestMapping("/addOrderItem")
	int addOrderItem(@RequestBody OrderItemVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/13 15:30:30
	 * @author: wu.kaibin
	 */
	@RequestMapping("/findOrderItemById")
	OrderItemVo findOrderItemById(@RequestParam("id") Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/13 15:30:30
	 * @author: wu.kaibin
	 */
	@RequestMapping("/updateOrderItemById")
	int updateOrderItemById(@RequestBody OrderItemVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/13 15:30:30
	 * @author: wu.kaibin
	 */
	@RequestMapping("/delOrderItemById")
	int delOrderItemById(@RequestParam("id") Long id);
	
}
