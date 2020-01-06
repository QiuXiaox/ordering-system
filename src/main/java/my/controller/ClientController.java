package my.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.api.ClientApi;
import my.service.ClientService;
import my.util.BeanUtil;
import my.vo.ClientVo;
import my.util.PageVo;
import my.util.ResponseVo;

@RestController
public class ClientController implements ClientApi {
	
	@Autowired
	ClientService clientService;
	
	@Override
	public PageVo<ClientVo> queryClient(@RequestBody ClientVo vo){
		List<ClientVo> list = clientService.queryClient(vo);
		return BeanUtil.page(list);	
	}

	@Override
	public ResponseVo<ClientVo> addClient(@RequestBody ClientVo vo){
		ResponseVo<ClientVo> result = clientService.addClient(vo);
		return result;
	}
	
	@Override
	public ClientVo findClientById(@RequestParam("id") Long id){
		return clientService.findClientById(id);
	}
	
	@Override
	public ResponseVo<ClientVo> updateClientById(@RequestBody ClientVo vo, HttpServletRequest req){
		boolean isSelf = false;
		if(vo.getId() == null) {
			ClientVo user = (ClientVo)req.getSession().getAttribute("user");
			vo.setId(user.getId());
			isSelf = true;
		}
		ResponseVo<ClientVo> result =  clientService.updateClientById(vo);
		if(isSelf) {
			req.getSession().setAttribute("user", result.getData());
		}
		return result;
	}
	
	@Override
	public int delClientById(@RequestParam("id") Long id){
		return clientService.delClientById(id);
	}

	@Override
	public ResponseVo<ClientVo> Login(@RequestBody ClientVo user, HttpServletRequest req) {
		ResponseVo<ClientVo> result = clientService.clientLogin(user);
		if(result.getStatus() == 200) {
			ClientVo data = result.getData();
			req.getSession().setAttribute("user", data);
		}
		return result;
	}

	@Override
	public ResponseVo<ClientVo> logout(HttpServletRequest req) {
		req.getSession().removeAttribute("user");
		ResponseVo<ClientVo> result = new ResponseVo<ClientVo>();
		result.setResult("SUCCESS");
		result.setStatus(200);
		result.setMsg("登出成功");
		return result;
	}

	@Override
	public ClientVo getClient(HttpServletRequest req) {
		ClientVo user = (ClientVo)req.getSession().getAttribute("user");
		ClientVo vo = clientService.findClientById(user.getId());
		return vo;
	}
	
}
