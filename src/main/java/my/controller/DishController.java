package my.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.api.DishApi;
import my.service.DishService;
import my.util.BeanUtil;
import my.vo.DishVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RestController
public class DishController implements DishApi {
	
	@Autowired
	DishService dishService;
	
	@Override
	public PageVo<DishVo> queryDish(@RequestBody DishVo vo){
		List<DishVo> list = dishService.queryDish(vo);
		return BeanUtil.page(list);	
	}

	@Override
	public ResponseVo<DishVo> addDish(@RequestBody DishVo vo){
		ResponseVo<DishVo> result = new ResponseVo<DishVo>();
		try {
			dishService.addDish(vo);
			result.setResult("SUCCESS");
			result.setStatus(200);
			result.setMsg("添加成功");
			return result;
		}catch(Exception e) {
			result.setResult("ERROR");
			result.setStatus(400);
			result.setMsg("添加失败");
			return result;
		}
	}
	
	@Override
	public DishVo findDishById(@RequestParam("id") Long id){
		return dishService.findDishById(id);
	}
	
	@Override
	public ResponseVo<DishVo> updateDishById(@RequestBody DishVo vo){
		ResponseVo<DishVo> result = new ResponseVo<DishVo>();
		try {
			dishService.updateDishById(vo);
			result.setResult("SUCCESS");
			result.setStatus(200);
			result.setMsg("修改成功");
			return result;
		}catch(Exception e) {
			result.setResult("ERROR");
			result.setStatus(400);
			result.setMsg("修改失败");
			return result;
		}
	}
	
	@Override
	public ResponseVo<DishVo> delDishById(@RequestParam("id") Long id){
		ResponseVo<DishVo> result = new ResponseVo<DishVo>();
		try {
			dishService.delDishById(id);
			result.setResult("SUCCESS");
			result.setStatus(200);
			result.setMsg("删除成功");
			return result;
		}catch(Exception e) {
			result.setResult("ERROR");
			result.setStatus(400);
			result.setMsg("删除失败");
			return result;
		}
	}
	
}
