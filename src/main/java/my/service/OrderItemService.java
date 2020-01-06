package my.service;

import java.util.List;

import my.vo.OrderItemVo;

public interface OrderItemService {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/13 15:25:22
	 * @author: wu.kaibin
	 */
	List<OrderItemVo> queryOrderItem(OrderItemVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/13 15:25:22
	 * @author: wu.kaibin
	 */
	int addOrderItem(OrderItemVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/13 15:25:22
	 * @author: wu.kaibin
	 */
	OrderItemVo findOrderItemById(Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/13 15:25:22
	 * @author: wu.kaibin
	 */
	int updateOrderItemById(OrderItemVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/13 15:25:22
	 * @author: wu.kaibin
	 */
	int delOrderItemById(Long id);
	
}
