package my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.domain.Dish;
import my.mapper.DishMapper;
import my.util.BeanUtil;
import my.vo.DishVo;

@Service
public class DishServiceImpl implements DishService {
	
	@Autowired
	DishMapper dishMapper;
	
	@Override
	public List<DishVo> queryDish(DishVo vo){
		//vo.setStatus("ENABLED");
		return dishMapper.queryDish(vo);
	}

	@Override
	public int addDish(DishVo vo){
		Dish po = BeanUtil.copy(vo, Dish.class);
		//po.setStatus("ENABLED");
		//po.setUpdateTime(new Date());
		return dishMapper.insertSelective(po);
	}
	
	@Override
	public DishVo findDishById(Long id){
		Dish po = dishMapper.selectByPrimaryKey(id);
		return BeanUtil.copy(po, DishVo.class);
	}
	
	@Override
	public int updateDishById(DishVo vo){
		Dish po = BeanUtil.copy(vo, Dish.class);
		//po.setUpdateTime(new Date());
		return dishMapper.updateByPrimaryKeySelective(po);
	}
	
	@Override
	public int delDishById(Long id){
		//Dish po = new Dish();
		//po.setId(id);
		//po.setStatus("DISABLED");
		//po.setUpdateTime(new Date());
		return dishMapper.deleteByPrimaryKey(id);
	}
	
}
