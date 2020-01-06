package my.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.vo.LeaveMessageVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RequestMapping("/leaveMessage")
public interface LeaveMessageApi {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/10 22:22:15
	 * @author: wu.kaibin
	 */
	@RequestMapping("/queryLeaveMessage")
	PageVo<LeaveMessageVo> queryLeaveMessage(@RequestBody LeaveMessageVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/10 22:22:15
	 * @author: wu.kaibin
	 */
	@RequestMapping("/addLeaveMessage")
	ResponseVo<LeaveMessageVo> addLeaveMessage(@RequestBody LeaveMessageVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/10 22:22:15
	 * @author: wu.kaibin
	 */
	@RequestMapping("/findLeaveMessageById")
	LeaveMessageVo findLeaveMessageById(@RequestParam("id") Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/10 22:22:15
	 * @author: wu.kaibin
	 */
	@RequestMapping("/updateLeaveMessageById")
	ResponseVo<LeaveMessageVo> updateLeaveMessageById(@RequestBody LeaveMessageVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/10 22:22:15
	 * @author: wu.kaibin
	 */
	@RequestMapping("/delLeaveMessageById")
	ResponseVo<LeaveMessageVo> delLeaveMessageById(@RequestParam("id") Long id);
	
}
