package my.service;

import java.util.List;

import my.vo.LeaveMessageVo;

public interface LeaveMessageService {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/10 22:21:53
	 * @author: wu.kaibin
	 */
	List<LeaveMessageVo> queryLeaveMessage(LeaveMessageVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/10 22:21:53
	 * @author: wu.kaibin
	 */
	int addLeaveMessage(LeaveMessageVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/10 22:21:53
	 * @author: wu.kaibin
	 */
	LeaveMessageVo findLeaveMessageById(Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/10 22:21:53
	 * @author: wu.kaibin
	 */
	int updateLeaveMessageById(LeaveMessageVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/10 22:21:53
	 * @author: wu.kaibin
	 */
	int delLeaveMessageById(Long id);
	
}
