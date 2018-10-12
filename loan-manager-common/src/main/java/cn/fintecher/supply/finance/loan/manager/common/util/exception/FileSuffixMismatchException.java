
package cn.fintecher.supply.finance.loan.manager.common.util.exception;

/**
 * 自定义异常
 * 上传文件类型不匹配
 * @author hujinbbbao
 * @email 
 * @date 2018年4月26日 
 */
public class FileSuffixMismatchException extends RuntimeException {
	private static final long serialVersionUID = -8052774455672358631L;
	private String msg;
    private int code = 500;
    
    public FileSuffixMismatchException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public FileSuffixMismatchException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public FileSuffixMismatchException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public FileSuffixMismatchException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
