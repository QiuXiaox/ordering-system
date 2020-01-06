package my.service;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.domain.DishType;
import my.mapper.DishTypeMapper;
import my.util.BeanUtil;
import my.vo.DishTypeVo;

@Service
public class DishTypeServiceImpl implements DishTypeService {
	
	@Autowired
	DishTypeMapper dishTypeMapper;
	
	@Override
	public List<DishTypeVo> queryDishType(DishTypeVo vo){
		//vo.setStatus("ENABLED");
		return dishTypeMapper.queryDishType(vo);
	}

	@Override
	public int addDishType(DishTypeVo vo){
		DishType po = BeanUtil.copy(vo, DishType.class);
		//po.setStatus("ENABLED");
		//po.setUpdateTime(new Date());
		return dishTypeMapper.insertSelective(po);
	}
	
	@Override
	public DishTypeVo findDishTypeById(Long id){
		DishType po = dishTypeMapper.selectByPrimaryKey(id);
		return BeanUtil.copy(po, DishTypeVo.class);
	}
	
	@Override
	public int updateDishTypeById(DishTypeVo vo){
		DishType po = BeanUtil.copy(vo, DishType.class);
		//po.setUpdateTime(new Date());
		return dishTypeMapper.updateByPrimaryKeySelective(po);
	}
	
	@Override
	public int delDishTypeById(Long id){
		DishType po = new DishType();
		//po.setId(id);
		//po.setStatus("DISABLED");
		//po.setUpdateTime(new Date());
		return dishTypeMapper.deleteByPrimaryKey(id);
	}
	
}
