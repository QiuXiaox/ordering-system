package my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.domain.LeaveMessage;
import my.mapper.LeaveMessageMapper;
import my.util.BeanUtil;
import my.vo.LeaveMessageVo;

@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {
	
	@Autowired
	LeaveMessageMapper leaveMessageMapper;
	
	@Override
	public List<LeaveMessageVo> queryLeaveMessage(LeaveMessageVo vo){
		//vo.setStatus("ENABLED");
		return leaveMessageMapper.queryLeaveMessage(vo);
	}

	@Override
	public int addLeaveMessage(LeaveMessageVo vo){
		LeaveMessage po = BeanUtil.copy(vo, LeaveMessage.class);
		//po.setStatus("ENABLED");
		//po.setUpdateTime(new Date());
		return leaveMessageMapper.insertSelective(po);
	}
	
	@Override
	public LeaveMessageVo findLeaveMessageById(Long id){
		LeaveMessage po = leaveMessageMapper.selectByPrimaryKey(id);
		return BeanUtil.copy(po, LeaveMessageVo.class);
	}
	
	@Override
	public int updateLeaveMessageById(LeaveMessageVo vo){
		LeaveMessage po = BeanUtil.copy(vo, LeaveMessage.class);
		//po.setUpdateTime(new Date());
		return leaveMessageMapper.updateByPrimaryKeySelective(po);
	}
	
	@Override
	public int delLeaveMessageById(Long id){
		//LeaveMessage po = new LeaveMessage();
		//po.setId(id);
		//po.setStatus("DISABLED");
		//po.setUpdateTime(new Date());
		return leaveMessageMapper.deleteByPrimaryKey(id);
	}
	
}
