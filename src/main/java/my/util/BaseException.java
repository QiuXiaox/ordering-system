package my.util;

/**
 * 基础异常类。
 * 
 * @author wuzhaobin
 *
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public BaseException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public BaseException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BaseException(Throwable cause) {
		super(cause);
	}

}
