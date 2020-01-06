package my.service;

import java.util.List;

import my.vo.FoodVo;

public interface FoodService {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/10 22:19:09
	 * @author: wu.kaibin
	 */
	List<FoodVo> queryFood(FoodVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/10 22:19:09
	 * @author: wu.kaibin
	 */
	int addFood(FoodVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/10 22:19:09
	 * @author: wu.kaibin
	 */
	FoodVo findFoodById(Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/10 22:19:09
	 * @author: wu.kaibin
	 */
	int updateFoodById(FoodVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/10 22:19:09
	 * @author: wu.kaibin
	 */
	int delFoodById(Long id);
	
}
