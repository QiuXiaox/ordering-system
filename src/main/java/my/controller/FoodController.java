package my.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.api.FoodApi;
import my.service.FoodService;
import my.util.BeanUtil;
import my.vo.FoodVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RestController
public class FoodController implements FoodApi {
	
	@Autowired
	FoodService foodService;
	
	@Override
	public PageVo<FoodVo> queryFood(@RequestBody FoodVo vo){
		List<FoodVo> list = foodService.queryFood(vo);
		return BeanUtil.page(list);	
	}

	@Override
	public ResponseVo<FoodVo> addFood(@RequestBody FoodVo vo){
		ResponseVo<FoodVo> result = new ResponseVo<FoodVo>();
		try {
			foodService.addFood(vo);
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
	public FoodVo findFoodById(@RequestParam("id") Long id){
		return foodService.findFoodById(id);
	}
	
	@Override
	public ResponseVo<FoodVo> updateFoodById(@RequestBody FoodVo vo){
		ResponseVo<FoodVo> result = new ResponseVo<FoodVo>();
		try {
			foodService.updateFoodById(vo);
			result.setResult("SUCCESS");
			result.setStatus(200);
			result.setMsg("保存成功");
			return result;
		}catch(Exception e) {
			result.setResult("ERROR");
			result.setStatus(400);
			result.setMsg("保存失败");
			return result;
		}
	}
	
	@Override
	public ResponseVo<FoodVo> delFoodById(@RequestParam("id") Long id){
		ResponseVo<FoodVo> result = new ResponseVo<FoodVo>();
		try {
			foodService.delFoodById(id);
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
