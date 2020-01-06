package my.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.vo.SpotCheckVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RequestMapping("/spotCheck")
public interface SpotCheckApi {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2020/1/2 14:37:08
	 * @author: wu.kaibin
	 */
	@RequestMapping("/querySpotCheck")
	PageVo<SpotCheckVo> querySpotCheck(@RequestBody SpotCheckVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2020/1/2 14:37:08
	 * @author: wu.kaibin
	 */
	@RequestMapping("/addSpotCheck")
	ResponseVo<SpotCheckVo> addSpotCheck(@RequestBody SpotCheckVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2020/1/2 14:37:08
	 * @author: wu.kaibin
	 */
	@RequestMapping("/findSpotCheckById")
	SpotCheckVo findSpotCheckById(@RequestParam("id") Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2020/1/2 14:37:08
	 * @author: wu.kaibin
	 */
	@RequestMapping("/updateSpotCheckById")
	ResponseVo<SpotCheckVo> updateSpotCheckById(@RequestBody SpotCheckVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2020/1/2 14:37:08
	 * @author: wu.kaibin
	 */
	@RequestMapping("/delSpotCheckById")
	ResponseVo<SpotCheckVo> delSpotCheckById(@RequestParam("id") Long id);
	
}
