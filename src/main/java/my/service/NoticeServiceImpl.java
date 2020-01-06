package my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.domain.Notice;
import my.mapper.NoticeMapper;
import my.util.BeanUtil;
import my.vo.NoticeVo;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	NoticeMapper noticeMapper;
	
	@Override
	public List<NoticeVo> queryNotice(NoticeVo vo){
		//vo.setStatus("ENABLED");
		return noticeMapper.queryNotice(vo);
	}

	@Override
	public int addNotice(NoticeVo vo){
		Notice po = BeanUtil.copy(vo, Notice.class);
		//po.setStatus("ENABLED");
		//po.setUpdateTime(new Date());
		return noticeMapper.insertSelective(po);
	}
	
	@Override
	public NoticeVo findNoticeById(Long id){
		Notice po = noticeMapper.selectByPrimaryKey(id);
		return BeanUtil.copy(po, NoticeVo.class);
	}
	
	@Override
	public int updateNoticeById(NoticeVo vo){
		Notice po = BeanUtil.copy(vo, Notice.class);
		//po.setUpdateTime(new Date());
		return noticeMapper.updateByPrimaryKeySelective(po);
	}
	
	@Override
	public int delNoticeById(Long id){
		//Notice po = new Notice();
		//po.setId(id);
		//po.setStatus("DISABLED");
		//po.setUpdateTime(new Date());
		return noticeMapper.deleteByPrimaryKey(id);
	}
	
}
