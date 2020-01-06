package my.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.api.DishTypeApi;
import my.service.DishTypeService;
import my.util.BeanUtil;
import my.vo.DishTypeVo;
import my.util.PageVo;

@RestController
public class DishTypeController implements DishTypeApi {
	
	@Autowired
	DishTypeService dishTypeService;
	
	@Override
	public PageVo<DishTypeVo> queryDishType(@RequestBody DishTypeVo vo){
		List<DishTypeVo> list = dishTypeService.queryDishType(vo);
		return BeanUtil.page(list);	
	}

	@Override
	public int addDishType(@RequestBody DishTypeVo vo){
		return dishTypeService.addDishType(vo);
	}
	
	@Override
	public DishTypeVo findDishTypeById(@RequestParam("id") Long id){
		return dishTypeService.findDishTypeById(id);
	}
	
	@Override
	public int updateDishTypeById(@RequestBody DishTypeVo vo){
		return dishTypeService.updateDishTypeById(vo);
	}
	
	@Override
	public int delDishTypeById(@RequestParam("id") Long id){
		return dishTypeService.delDishTypeById(id);
	}
	
}
