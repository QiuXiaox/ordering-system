package my.service;

import java.util.List;

import my.vo.DishTypeVo;

public interface DishTypeService {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/16 14:11:35
	 * @author: wu.kaibin
	 */
	List<DishTypeVo> queryDishType(DishTypeVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/16 14:11:35
	 * @author: wu.kaibin
	 */
	int addDishType(DishTypeVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/16 14:11:35
	 * @author: wu.kaibin
	 */
	DishTypeVo findDishTypeById(Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/16 14:11:35
	 * @author: wu.kaibin
	 */
	int updateDishTypeById(DishTypeVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/16 14:11:35
	 * @author: wu.kaibin
	 */
	int delDishTypeById(Long id);
	
}
