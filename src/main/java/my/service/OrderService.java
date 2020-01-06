package my.service;

import java.util.List;

import my.vo.DishFlowVo;
import my.vo.OrderVo;

public interface OrderService {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/13 15:25:09
	 * @author: wu.kaibin
	 */
	List<OrderVo> queryOrder(OrderVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/13 15:25:09
	 * @author: wu.kaibin
	 */
	int addOrder(OrderVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/13 15:25:09
	 * @author: wu.kaibin
	 */
	OrderVo findOrderById(Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/13 15:25:09
	 * @author: wu.kaibin
	 */
	int updateOrderById(OrderVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/13 15:25:09
	 * @author: wu.kaibin
	 */
	int delOrderById(Long id);
	
	List<DishFlowVo> dishFlow(DishFlowVo vo);
}
