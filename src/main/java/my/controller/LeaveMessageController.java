package my.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.api.LeaveMessageApi;
import my.service.LeaveMessageService;
import my.util.BeanUtil;
import my.vo.LeaveMessageVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RestController
public class LeaveMessageController implements LeaveMessageApi {
	
	@Autowired
	LeaveMessageService leaveMessageService;
	
	@Override
	public PageVo<LeaveMessageVo> queryLeaveMessage(@RequestBody LeaveMessageVo vo){
		List<LeaveMessageVo> list = leaveMessageService.queryLeaveMessage(vo);
		return BeanUtil.page(list);	
	}

	@Override
	public ResponseVo<LeaveMessageVo> addLeaveMessage(@RequestBody LeaveMessageVo vo){
		ResponseVo<LeaveMessageVo> result = new ResponseVo<LeaveMessageVo>();
		try {
			vo.setLeaveDate(new Date());
			leaveMessageService.addLeaveMessage(vo);
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
	public LeaveMessageVo findLeaveMessageById(@RequestParam("id") Long id){
		return leaveMessageService.findLeaveMessageById(id);
	}
	
	@Override
	public ResponseVo<LeaveMessageVo> updateLeaveMessageById(@RequestBody LeaveMessageVo vo){
		ResponseVo<LeaveMessageVo> result = new ResponseVo<LeaveMessageVo>();
		try {
			vo.setLeaveDate(new Date());
			leaveMessageService.updateLeaveMessageById(vo);
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
	public ResponseVo<LeaveMessageVo> delLeaveMessageById(@RequestParam("id") Long id){
		ResponseVo<LeaveMessageVo> result = new ResponseVo<LeaveMessageVo>();
		try {
			leaveMessageService.delLeaveMessageById(id);
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
