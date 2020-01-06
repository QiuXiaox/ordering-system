package my.service;

import java.util.List;

import my.vo.NoticeVo;

public interface NoticeService {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/10 22:25:25
	 * @author: wu.kaibin
	 */
	List<NoticeVo> queryNotice(NoticeVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/10 22:25:25
	 * @author: wu.kaibin
	 */
	int addNotice(NoticeVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/10 22:25:25
	 * @author: wu.kaibin
	 */
	NoticeVo findNoticeById(Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/10 22:25:25
	 * @author: wu.kaibin
	 */
	int updateNoticeById(NoticeVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/10 22:25:25
	 * @author: wu.kaibin
	 */
	int delNoticeById(Long id);
	
}
