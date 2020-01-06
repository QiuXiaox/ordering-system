package my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import my.domain.Client;
import my.mapper.ClientMapper;
import my.util.BeanUtil;
import my.util.ResponseVo;
import my.vo.ClientVo;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	ClientMapper clientMapper;
	
	@Override
	public List<ClientVo> queryClient(ClientVo vo){
		//vo.setStatus("ENABLED");
		return clientMapper.queryClient(vo);
	}

	@Override
	public ResponseVo<ClientVo> addClient(ClientVo vo){
		Client po = BeanUtil.copy(vo, Client.class);
		ClientVo clientVo = clientMapper.clientLogin(vo.getAccount());
		ResponseVo<ClientVo> response = new ResponseVo<ClientVo>();
		if(!ObjectUtils.isEmpty(clientVo)) {
			response.setStatus(400);
			response.setResult("EXIST");
			response.setMsg("该账号已存在，请重新输入");
			return response;
		}
		try{
			clientMapper.insertSelective(po);
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
	public ClientVo findClientById(Long id){
		Client po = clientMapper.selectByPrimaryKey(id);
		return BeanUtil.copy(po, ClientVo.class);
	}
	
	@Override
	public ResponseVo<ClientVo> updateClientById(ClientVo vo){
		Client po = BeanUtil.copy(vo, Client.class);
		//po.setUpdateTime(new Date());
		ResponseVo<ClientVo> response = new ResponseVo<ClientVo>();
		clientMapper.updateByPrimaryKeySelective(po);
		response.setResult("SUCCESS");
		response.setStatus(200);
		response.setData(vo);
		response.setMsg("修改成功");		
		return response;
	}
	
	@Override
	public int delClientById(Long id){
		//Client po = new Client();
		//po.setId(id);
		//po.setStatus("DISABLED");
		//po.setUpdateTime(new Date());
		return clientMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ResponseVo<ClientVo> clientLogin(ClientVo vo) {
		ClientVo clientVo = clientMapper.clientLogin(vo.getAccount());
		ResponseVo<ClientVo> response = new ResponseVo<ClientVo>();
		if(ObjectUtils.isEmpty(clientVo)) {
			response.setStatus(400);
			response.setResult("ERROR");
			response.setMsg("用户不存在");
			return response;

		}
		if(!clientVo.getPwd().equals(vo.getPwd())) {
			response.setStatus(400);
			response.setResult("ERROR");
			response.setMsg("密码错误");
			return response;
		}
		response.setResult("SUCCESS");
		response.setStatus(200);
		response.setData(clientVo);
		response.setMsg("登入成功");
		return response;
	}
	
}
