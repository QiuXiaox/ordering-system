package my.service;

import java.util.List;

import my.util.ResponseVo;
import my.vo.ClientVo;

public interface ClientService {
	
	/**
	 * 查找所有XXXXX
	 * @createTime: 2019/12/10 21:59:33
	 * @author: wu.kaibin
	 */
	List<ClientVo> queryClient(ClientVo vo);

	/**
	 * 新增XXXXX
	 * @createTime: 2019/12/10 21:59:33
	 * @author: wu.kaibin
	 */
	ResponseVo<ClientVo> addClient(ClientVo vo);
	
	/**
	 * 通过id查看XXXXX信息
	 * @createTime: 2019/12/10 21:59:33
	 * @author: wu.kaibin
	 */
	ClientVo findClientById(Long id);
	
	/**
	 * 通过id修改XXXXX
	 * @createTime: 2019/12/10 21:59:33
	 * @author: wu.kaibin
	 */
	ResponseVo<ClientVo> updateClientById(ClientVo vo);
	
	/**
	 * 通过id软删除XXXXX信息
	 * @createTime: 2019/12/10 21:59:33
	 * @author: wu.kaibin
	 */
	int delClientById(Long id);
	
	/**
	 * 客户登入
	 * @param account
	 * @return
	 */
	ResponseVo<ClientVo> clientLogin(ClientVo vo);
}
