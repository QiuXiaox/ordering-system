package my.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.api.NoticeApi;
import my.service.NoticeService;
import my.util.BeanUtil;
import my.vo.NoticeVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RestController
public class NoticeController implements NoticeApi {
	
	@Autowired
	NoticeService noticeService;
	
	@Override
	public PageVo<NoticeVo> queryNotice(@RequestBody NoticeVo vo){
		List<NoticeVo> list = noticeService.queryNotice(vo);
		return BeanUtil.page(list);	
	}

	@Override
	public ResponseVo<NoticeVo> addNotice(@RequestBody NoticeVo vo, HttpServletRequest req){
		ResponseVo<NoticeVo> result = new ResponseVo<NoticeVo>();
		try {
/*			EmployeeVo user = (EmployeeVo)req.getSession().getAttribute("user");
			vo.setEmpId(user.getId());*/
			vo.setCreateTime(new Date());
			noticeService.addNotice(vo);
			result.setResult("SUCCESS");
			result.setStatus(200);
			result.setMsg("添加成功");
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			result.setResult("ERROR");
			result.setStatus(400);
			result.setMsg("添加失败");
			return result;
		}
	}
	
	@Override
	public NoticeVo findNoticeById(@RequestParam("id") Long id){
		return noticeService.findNoticeById(id);
	}
	
	@Override
	public ResponseVo<NoticeVo> updateNoticeById(@RequestBody NoticeVo vo){
		ResponseVo<NoticeVo> result = new ResponseVo<NoticeVo>();
		try {
			noticeService.updateNoticeById(vo);
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
	public ResponseVo<NoticeVo> delNoticeById(@RequestParam("id") Long id){
		ResponseVo<NoticeVo> result = new ResponseVo<NoticeVo>();
		try {
			noticeService.delNoticeById(id);
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
