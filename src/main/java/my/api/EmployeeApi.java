package my.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.vo.EmployeeVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RequestMapping("/employee")
public interface EmployeeApi {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/10 22:13:15
	 * @author: wu.kaibin
	 */
	@RequestMapping("/queryEmployee")
	PageVo<EmployeeVo> queryEmployee(EmployeeVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/10 22:13:15
	 * @author: wu.kaibin
	 */
	@RequestMapping("/addEmployee")
	ResponseVo<EmployeeVo> addEmployee(@RequestBody EmployeeVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/10 22:13:15
	 * @author: wu.kaibin
	 */
	@RequestMapping("/findEmployeeById")
	EmployeeVo findEmployeeById(@RequestParam("id") Long id);
	
	/**
	 * 查看当前登入员工
	 * @createTime: 2019/12/10 22:13:15
	 * @author: wu.kaibin
	 */
	@RequestMapping("/getEmployee")
	EmployeeVo getEmployee(HttpServletRequest req);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/10 22:13:15
	 * @author: wu.kaibin
	 */
	@RequestMapping("/updateEmployeeById")
	ResponseVo<EmployeeVo> updateEmployeeById(@RequestBody EmployeeVo vo, HttpServletRequest req);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/10 22:13:15
	 * @author: wu.kaibin
	 */
	@RequestMapping("/delEmployeeById")
	int delEmployeeById(@RequestParam("id") Long id);
	
	@RequestMapping("/login")
	ResponseVo<EmployeeVo> Login(@RequestBody EmployeeVo user, HttpServletRequest req);
	
	@RequestMapping("/logout")
	ResponseVo<EmployeeVo> logout(HttpServletRequest req);
}
