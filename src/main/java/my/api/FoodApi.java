package my.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.vo.FoodVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RequestMapping("/food")
public interface FoodApi {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/10 22:19:32
	 * @author: wu.kaibin
	 */
	@RequestMapping("/queryFood")
	PageVo<FoodVo> queryFood(@RequestBody FoodVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/10 22:19:32
	 * @author: wu.kaibin
	 */
	@RequestMapping("/addFood")
	ResponseVo<FoodVo> addFood(@RequestBody FoodVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/10 22:19:32
	 * @author: wu.kaibin
	 */
	@RequestMapping("/findFoodById")
	FoodVo findFoodById(@RequestParam("id") Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/10 22:19:32
	 * @author: wu.kaibin
	 */
	@RequestMapping("/updateFoodById")
	ResponseVo<FoodVo> updateFoodById(@RequestBody FoodVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/10 22:19:32
	 * @author: wu.kaibin
	 */
	@RequestMapping("/delFoodById")
	ResponseVo<FoodVo> delFoodById(@RequestParam("id") Long id);
	
}
