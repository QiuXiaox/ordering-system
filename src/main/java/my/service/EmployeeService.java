package my.service;

import java.util.List;

import my.util.ResponseVo;
import my.vo.EmployeeVo;

public interface EmployeeService {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/10 22:12:36
	 * @author: wu.kaibin
	 */
	List<EmployeeVo> queryEmployee(EmployeeVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/10 22:12:36
	 * @author: wu.kaibin
	 */
	ResponseVo<EmployeeVo> addEmployee(EmployeeVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/10 22:12:36
	 * @author: wu.kaibin
	 */
	EmployeeVo findEmployeeById(Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/10 22:12:36
	 * @author: wu.kaibin
	 */
	ResponseVo<EmployeeVo> updateEmployeeById(EmployeeVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/10 22:12:36
	 * @author: wu.kaibin
	 */
	int delEmployeeById(Long id);
	
	/**
	 * 员工登入
	 * @param account
	 * @return
	 */
	ResponseVo<EmployeeVo> employeeLogin(EmployeeVo vo);
}
