package my.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.vo.ClientVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RequestMapping("/client")
public interface ClientApi {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/10 22:00:28
	 * @author: wu.kaibin
	 */
	@RequestMapping("/queryClient")
	PageVo<ClientVo> queryClient(ClientVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/10 22:00:28
	 * @author: wu.kaibin
	 */
	@RequestMapping("/addClient")
	ResponseVo<ClientVo> addClient(@RequestBody ClientVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/10 22:00:28
	 * @author: wu.kaibin
	 */
	@RequestMapping("/findClientById")
	ClientVo findClientById(@RequestParam("id") Long id);
	
	/**
	 * 当前登入用户信息
	 * @createTime: 2019/12/10 22:00:28
	 * @author: wu.kaibin
	 */
	@RequestMapping("/getClient")
	ClientVo getClient(HttpServletRequest req);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/10 22:00:28
	 * @author: wu.kaibin
	 */
	@RequestMapping("/updateClientById")
	ResponseVo<ClientVo> updateClientById(@RequestBody ClientVo vo, HttpServletRequest req);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/10 22:00:28
	 * @author: wu.kaibin
	 */
	@RequestMapping("/delClientById")
	int delClientById(@RequestParam("id") Long id);
	
	@RequestMapping("/login")
	ResponseVo<ClientVo> Login(@RequestBody ClientVo user, HttpServletRequest req);
	
	@RequestMapping("/logout")
	 ResponseVo<ClientVo> logout(HttpServletRequest req);
}
