package my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.domain.Tablewear;
import my.mapper.TablewearMapper;
import my.util.BeanUtil;
import my.vo.TablewearVo;

@Service
public class TablewearServiceImpl implements TablewearService {
	
	@Autowired
	TablewearMapper tablewearMapper;
	
	@Override
	public List<TablewearVo> queryTablewear(TablewearVo vo){
		//vo.setStatus("ENABLED");
		return tablewearMapper.queryTablewear(vo);
	}

	@Override
	public int addTablewear(TablewearVo vo){
		Tablewear po = BeanUtil.copy(vo, Tablewear.class);
		//po.setStatus("ENABLED");
		//po.setUpdateTime(new Date());
		return tablewearMapper.insertSelective(po);
	}
	
	@Override
	public TablewearVo findTablewearById(Long id){
		Tablewear po = tablewearMapper.selectByPrimaryKey(id);
		return BeanUtil.copy(po, TablewearVo.class);
	}
	
	@Override
	public int updateTablewearById(TablewearVo vo){
		Tablewear po = BeanUtil.copy(vo, Tablewear.class);
		//po.setUpdateTime(new Date());
		return tablewearMapper.updateByPrimaryKeySelective(po);
	}
	
	@Override
	public int delTablewearById(Long id){
		//Tablewear po = new Tablewear();
		//po.setId(id);
		//po.setStatus("DISABLED");
		//po.setUpdateTime(new Date());
		return tablewearMapper.deleteByPrimaryKey(id);
	}
	
}
