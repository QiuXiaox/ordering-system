package my.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.api.TablewearApi;
import my.service.TablewearService;
import my.util.BeanUtil;
import my.vo.TablewearVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RestController
public class TablewearController implements TablewearApi {
	
	@Autowired
	TablewearService tablewearService;
	
	@Override
	public PageVo<TablewearVo> queryTablewear(@RequestBody TablewearVo vo){
		List<TablewearVo> list = tablewearService.queryTablewear(vo);
		return BeanUtil.page(list);	
	}

	@Override
	public ResponseVo<TablewearVo> addTablewear(@RequestBody TablewearVo vo){
		ResponseVo<TablewearVo> result = new ResponseVo<TablewearVo>();
		try {
			tablewearService.addTablewear(vo);
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
	public TablewearVo findTablewearById(@RequestParam("id") Long id){
		return tablewearService.findTablewearById(id);
	}
	
	@Override
	public ResponseVo<TablewearVo> updateTablewearById(@RequestBody TablewearVo vo){
		ResponseVo<TablewearVo> result = new ResponseVo<TablewearVo>();
		try {
			tablewearService.updateTablewearById(vo);
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
	public ResponseVo<TablewearVo> delTablewearById(@RequestParam("id") Long id){
		ResponseVo<TablewearVo> result = new ResponseVo<TablewearVo>();
		try {
			tablewearService.delTablewearById(id);
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
