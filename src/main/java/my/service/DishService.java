package my.service;

import java.util.List;

import my.vo.DishVo;

public interface DishService {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/10 22:08:19
	 * @author: wu.kaibin
	 */
	List<DishVo> queryDish(DishVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/10 22:08:19
	 * @author: wu.kaibin
	 */
	int addDish(DishVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/10 22:08:19
	 * @author: wu.kaibin
	 */
	DishVo findDishById(Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/10 22:08:19
	 * @author: wu.kaibin
	 */
	int updateDishById(DishVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/10 22:08:19
	 * @author: wu.kaibin
	 */
	int delDishById(Long id);
	
}
