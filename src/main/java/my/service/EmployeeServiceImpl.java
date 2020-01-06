package my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import my.domain.Employee;
import my.mapper.EmployeeMapper;
import my.util.BeanUtil;
import my.util.ResponseVo;
import my.vo.EmployeeVo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Override
	public List<EmployeeVo> queryEmployee(EmployeeVo vo){
		//vo.setStatus("ENABLED");
		return employeeMapper.queryEmployee(vo);
	}

	@Override
	public ResponseVo<EmployeeVo> addEmployee(EmployeeVo vo){
		Employee po = BeanUtil.copy(vo, Employee.class);
		EmployeeVo employeeVo = employeeMapper.EmployeeLogin(vo.getAccount());
		ResponseVo<EmployeeVo> response = new ResponseVo<EmployeeVo>();
		if(!ObjectUtils.isEmpty(employeeVo)) {
			response.setStatus(400);
			response.setResult("EXIST");
			response.setMsg("该账号已存在，请重新输入");
			return response;
		}
		try{
			employeeMapper.insertSelective(po);
			response.setStatus(200);
			response.setResult("SUCCESS");
			response.setMsg("添加成功");
			return response;
		}catch(Exception e) {
			response.setStatus(400);
			response.setResult("ERROR");
			response.setMsg("添加失败");
			return response;
		}
	}
	
	@Override
	public EmployeeVo findEmployeeById(Long id){
		Employee po = employeeMapper.selectByPrimaryKey(id);
		return BeanUtil.copy(po, EmployeeVo.class);
	}
	
	@Override
	public ResponseVo<EmployeeVo> updateEmployeeById(EmployeeVo vo){
		Employee po = BeanUtil.copy(vo, Employee.class);
		ResponseVo<EmployeeVo> response = new ResponseVo<EmployeeVo>();
		employeeMapper.updateByPrimaryKeySelective(po);
		response.setResult("SUCCESS");
		response.setStatus(200);
		response.setData(vo);
		response.setMsg("修改成功");		
		return response;
	}
	
	@Override
	public int delEmployeeById(Long id){
		//Employee po = new Employee();
		//po.setId(id);
		//po.setStatus("DISABLED");
		//po.setUpdateTime(new Date());
		return employeeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ResponseVo<EmployeeVo> employeeLogin(EmployeeVo vo) {
		EmployeeVo employeeVo = employeeMapper.EmployeeLogin(vo.getAccount());
		ResponseVo<EmployeeVo> response = new ResponseVo<EmployeeVo>();
		if(ObjectUtils.isEmpty(employeeVo)) {
			response.setStatus(400);
			response.setResult("ERROR");
			response.setMsg("用户不存在");
			return response;
		}
		if(!employeeVo.getPwd().equals(vo.getPwd())) {
			response.setStatus(400);
			response.setResult("ERROR");
			response.setMsg("密码错误");
			return response;			
		}
		response.setResult("SUCCESS");
		response.setStatus(200);
		response.setData(employeeVo);
		response.setMsg("登入成功");
		return response;
	}
	
}
