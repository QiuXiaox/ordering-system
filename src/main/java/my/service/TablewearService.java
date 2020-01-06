package my.service;

import java.util.List;

import my.vo.TablewearVo;

public interface TablewearService {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/10 22:28:07
	 * @author: wu.kaibin
	 */
	List<TablewearVo> queryTablewear(TablewearVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/10 22:28:07
	 * @author: wu.kaibin
	 */
	int addTablewear(TablewearVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/10 22:28:07
	 * @author: wu.kaibin
	 */
	TablewearVo findTablewearById(Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/10 22:28:07
	 * @author: wu.kaibin
	 */
	int updateTablewearById(TablewearVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/10 22:28:07
	 * @author: wu.kaibin
	 */
	int delTablewearById(Long id);
	
}
