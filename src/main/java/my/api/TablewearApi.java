package my.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.vo.TablewearVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RequestMapping("/tablewear")
public interface TablewearApi {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/10 22:28:33
	 * @author: wu.kaibin
	 */
	@RequestMapping("/queryTablewear")
	PageVo<TablewearVo> queryTablewear(@RequestBody TablewearVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/10 22:28:33
	 * @author: wu.kaibin
	 */
	@RequestMapping("/addTablewear")
	ResponseVo<TablewearVo> addTablewear(@RequestBody TablewearVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/10 22:28:33
	 * @author: wu.kaibin
	 */
	@RequestMapping("/findTablewearById")
	TablewearVo findTablewearById(@RequestParam("id") Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/10 22:28:33
	 * @author: wu.kaibin
	 */
	@RequestMapping("/updateTablewearById")
	ResponseVo<TablewearVo> updateTablewearById(@RequestBody TablewearVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/10 22:28:33
	 * @author: wu.kaibin
	 */
	@RequestMapping("/delTablewearById")
	ResponseVo<TablewearVo> delTablewearById(@RequestParam("id") Long id);
	
}
