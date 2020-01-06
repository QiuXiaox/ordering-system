package my.util;

import java.io.Serializable;

public class ResponseVo<T> implements Serializable{
	
	private static final long serialVersionUID = -2049439550666128636L;

	private String result = "ERROR";
	
	private String msg;
	
	private Integer status = 400;
		
	private T data;

	public ResponseVo(){
		msg = "";
		status=400;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
