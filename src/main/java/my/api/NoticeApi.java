package my.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.vo.NoticeVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RequestMapping("/notice")
public interface NoticeApi {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/10 22:25:59
	 * @author: wu.kaibin
	 */
	@RequestMapping("/queryNotice")
	PageVo<NoticeVo> queryNotice(@RequestBody NoticeVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/10 22:25:59
	 * @author: wu.kaibin
	 */
	@RequestMapping("/addNotice")
	ResponseVo<NoticeVo> addNotice(@RequestBody NoticeVo vo, HttpServletRequest req);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/10 22:25:59
	 * @author: wu.kaibin
	 */
	@RequestMapping("/findNoticeById")
	NoticeVo findNoticeById(@RequestParam("id") Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/10 22:25:59
	 * @author: wu.kaibin
	 */
	@RequestMapping("/updateNoticeById")
	ResponseVo<NoticeVo> updateNoticeById(@RequestBody NoticeVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/10 22:25:59
	 * @author: wu.kaibin
	 */
	@RequestMapping("/delNoticeById")
	ResponseVo<NoticeVo> delNoticeById(@RequestParam("id") Long id);
	
}
