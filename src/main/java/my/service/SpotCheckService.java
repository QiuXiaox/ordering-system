package my.service;

import java.util.List;

import my.vo.SpotCheckVo;

public interface SpotCheckService {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2020/1/2 14:36:03
	 * @author: wu.kaibin
	 */
	List<SpotCheckVo> querySpotCheck(SpotCheckVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2020/1/2 14:36:03
	 * @author: wu.kaibin
	 */
	int addSpotCheck(SpotCheckVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2020/1/2 14:36:03
	 * @author: wu.kaibin
	 */
	SpotCheckVo findSpotCheckById(Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2020/1/2 14:36:03
	 * @author: wu.kaibin
	 */
	int updateSpotCheckById(SpotCheckVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2020/1/2 14:36:03
	 * @author: wu.kaibin
	 */
	int delSpotCheckById(Long id);
	
}
