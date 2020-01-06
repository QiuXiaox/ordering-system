package my.service;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.domain.SpotCheck;
import my.mapper.SpotCheckMapper;
import my.util.BeanUtil;
import my.vo.SpotCheckVo;

@Service
public class SpotCheckServiceImpl implements SpotCheckService {
	
	@Autowired
	SpotCheckMapper spotCheckMapper;
	
	@Override
	public List<SpotCheckVo> querySpotCheck(SpotCheckVo vo){
		//vo.setStatus("ENABLED");
		return spotCheckMapper.querySpotCheck(vo);
	}

	@Override
	public int addSpotCheck(SpotCheckVo vo){
		SpotCheck po = BeanUtil.copy(vo, SpotCheck.class);
		po.setCheckTime(new Date());
		//po.setStatus("ENABLED");
		//po.setUpdateTime(new Date());
		return spotCheckMapper.insertSelective(po);
	}
	
	@Override
	public SpotCheckVo findSpotCheckById(Long id){
		SpotCheck po = spotCheckMapper.selectByPrimaryKey(id);
		return BeanUtil.copy(po, SpotCheckVo.class);
	}
	
	@Override
	public int updateSpotCheckById(SpotCheckVo vo){
		SpotCheck po = BeanUtil.copy(vo, SpotCheck.class);
		po.setCheckTime(new Date());
		return spotCheckMapper.updateByPrimaryKeySelective(po);
	}
	
	@Override
	public int delSpotCheckById(Long id){
		SpotCheck po = new SpotCheck();
		//po.setId(id);
		//po.setStatus("DISABLED");
		//po.setUpdateTime(new Date());
		return spotCheckMapper.deleteByPrimaryKey(id);
	}
	
}
