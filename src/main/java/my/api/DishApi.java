package my.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.vo.DishVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RequestMapping("/dish")
public interface DishApi {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/10 22:09:38
	 * @author: wu.kaibin
	 */
	@RequestMapping("/queryDish")
	PageVo<DishVo> queryDish(@RequestBody DishVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/10 22:09:38
	 * @author: wu.kaibin
	 */
	@RequestMapping("/addDish")
	ResponseVo<DishVo> addDish(@RequestBody DishVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/10 22:09:38
	 * @author: wu.kaibin
	 */
	@RequestMapping("/findDishById")
	DishVo findDishById(@RequestParam("id") Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/10 22:09:38
	 * @author: wu.kaibin
	 */
	@RequestMapping("/updateDishById")
	ResponseVo<DishVo> updateDishById(@RequestBody DishVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/10 22:09:38
	 * @author: wu.kaibin
	 */
	@RequestMapping("/delDishById")
	ResponseVo<DishVo> delDishById(@RequestParam("id") Long id);
	
}
