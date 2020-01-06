package my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.domain.Food;
import my.mapper.FoodMapper;
import my.util.BeanUtil;
import my.vo.FoodVo;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	FoodMapper foodMapper;
	
	@Override
	public List<FoodVo> queryFood(FoodVo vo){
		//vo.setStatus("ENABLED");
		return foodMapper.queryFood(vo);
	}

	@Override
	public int addFood(FoodVo vo){
		Food po = BeanUtil.copy(vo, Food.class);
		//po.setStatus("ENABLED");
		//po.setUpdateTime(new Date());
		return foodMapper.insertSelective(po);
	}
	
	@Override
	public FoodVo findFoodById(Long id){
		Food po = foodMapper.selectByPrimaryKey(id);
		return BeanUtil.copy(po, FoodVo.class);
	}
	
	@Override
	public int updateFoodById(FoodVo vo){
		Food po = BeanUtil.copy(vo, Food.class);
		//po.setUpdateTime(new Date());
		return foodMapper.updateByPrimaryKeySelective(po);
	}
	
	@Override
	public int delFoodById(Long id){
		//Food po = new Food();
		//po.setId(id);
		//po.setStatus("DISABLED");
		//po.setUpdateTime(new Date());
		return foodMapper.deleteByPrimaryKey(id);
	}
	
}
