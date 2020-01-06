package my.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.api.EmployeeApi;
import my.service.EmployeeService;
import my.util.BeanUtil;
import my.vo.EmployeeVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RestController
public class EmployeeController implements EmployeeApi {
	
	@Autowired
	EmployeeService employeeService;
	
	@Override
	public PageVo<EmployeeVo> queryEmployee(@RequestBody EmployeeVo vo){
		List<EmployeeVo> list = employeeService.queryEmployee(vo);
		return BeanUtil.page(list);	
	}

	@Override
	public ResponseVo<EmployeeVo> addEmployee(@RequestBody EmployeeVo vo){
		ResponseVo<EmployeeVo> result = employeeService.addEmployee(vo);
		return result;
	}
	
	@Override
	public EmployeeVo findEmployeeById(@RequestParam("id") Long id){
		return employeeService.findEmployeeById(id);
	}
	
	@Override
	public ResponseVo<EmployeeVo> updateEmployeeById(@RequestBody EmployeeVo vo, HttpServletRequest req){
		boolean isSelf = false;
		if(vo.getId() == null) {
			EmployeeVo user = (EmployeeVo)req.getSession().getAttribute("user");
			vo.setId(user.getId());
			isSelf = true;
		}
		ResponseVo<EmployeeVo> result = employeeService.updateEmployeeById(vo);
		if(isSelf){
			req.getSession().setAttribute("user", result.getData());
		}
		return result;
	}
	
	@Override
	public int delEmployeeById(@RequestParam("id") Long id){
		return employeeService.delEmployeeById(id);
	}

	@Override
	public ResponseVo<EmployeeVo> Login(@RequestBody EmployeeVo user, HttpServletRequest req) {
		ResponseVo<EmployeeVo> result = employeeService.employeeLogin(user);
		if(result.getStatus() == 200) {
			EmployeeVo data = result.getData();
			req.getSession().setAttribute("user", data);
		}		
		return result;
	}

	@Override
	public ResponseVo<EmployeeVo> logout(HttpServletRequest req) {
		req.getSession().removeAttribute("user");
		ResponseVo<EmployeeVo> result = new ResponseVo<EmployeeVo>();
		result.setResult("SUCCESS");
		result.setStatus(200);
		result.setMsg("登出成功");
		return result;
	}

	@Override
	public EmployeeVo getEmployee(HttpServletRequest req) {
		EmployeeVo user = (EmployeeVo)req.getSession().getAttribute("user");
		return employeeService.findEmployeeById(user.getId());
	}
	
}
