package my.util;

import java.io.Serializable;

/**
 * 基础值对象。
 * 
 * 1. 可序列化。
 * 2. 分页支持：pageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero
 * 		注意：pageNum 和 pageSize 两个属性同时存在才会触发分页操作，在这个前提下，其他的分页参数才会生效。
 * 
 * @author wuzhaobin
 *
 */
public class BaseVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 页码，从1开始。
	 */
	private Integer pageNum;

	/**
	 * 页面大小。
	 */
	private Integer pageSize;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
