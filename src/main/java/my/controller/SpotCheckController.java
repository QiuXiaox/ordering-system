package my.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.api.SpotCheckApi;
import my.service.SpotCheckService;
import my.util.BeanUtil;
import my.vo.SpotCheckVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RestController
public class SpotCheckController implements SpotCheckApi {
	
	@Autowired
	SpotCheckService spotCheckService;
	
	@Override
	public PageVo<SpotCheckVo> querySpotCheck(@RequestBody SpotCheckVo vo){
		List<SpotCheckVo> list = spotCheckService.querySpotCheck(vo);
		return BeanUtil.page(list);	
	}

	@Override
	public ResponseVo<SpotCheckVo> addSpotCheck(@RequestBody SpotCheckVo vo){
		ResponseVo<SpotCheckVo> result = new ResponseVo<SpotCheckVo>();
		try {
			spotCheckService.addSpotCheck(vo);
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
	public SpotCheckVo findSpotCheckById(@RequestParam("id") Long id){
		return spotCheckService.findSpotCheckById(id);
	}
	
	@Override
	public ResponseVo<SpotCheckVo> updateSpotCheckById(@RequestBody SpotCheckVo vo){
		ResponseVo<SpotCheckVo> result = new ResponseVo<SpotCheckVo>();
		try {
			spotCheckService.updateSpotCheckById(vo);
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
	public ResponseVo<SpotCheckVo> delSpotCheckById(@RequestParam("id") Long id){
		ResponseVo<SpotCheckVo> result = new ResponseVo<SpotCheckVo>();
		try {
			spotCheckService.delSpotCheckById(id);
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
