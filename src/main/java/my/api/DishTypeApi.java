package my.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.vo.DishTypeVo;
import my.util.PageVo;

@RequestMapping("/dishType")
public interface DishTypeApi {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/16 14:12:17
	 * @author: wu.kaibin
	 */
	@RequestMapping("/queryDishType")
	PageVo<DishTypeVo> queryDishType(@RequestBody DishTypeVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/16 14:12:17
	 * @author: wu.kaibin
	 */
	@RequestMapping("/addDishType")
	int addDishType(@RequestBody DishTypeVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/16 14:12:17
	 * @author: wu.kaibin
	 */
	@RequestMapping("/findDishTypeById")
	DishTypeVo findDishTypeById(@RequestParam("id") Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/16 14:12:17
	 * @author: wu.kaibin
	 */
	@RequestMapping("/updateDishTypeById")
	int updateDishTypeById(@RequestBody DishTypeVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/16 14:12:17
	 * @author: wu.kaibin
	 */
	@RequestMapping("/delDishTypeById")
	int delDishTypeById(@RequestParam("id") Long id);
	
}
