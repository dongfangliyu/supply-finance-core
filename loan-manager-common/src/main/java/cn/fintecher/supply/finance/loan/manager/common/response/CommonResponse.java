package cn.fintecher.supply.finance.loan.manager.common.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 返回结果
 * @author huanglei
 *
 */
public class CommonResponse<T> implements Serializable {

	private static final long serialVersionUID = 7223184927894637746L;
	
	// 是否成功
    @ApiModelProperty(value="是否成功[true:成功，false:失败]", required=true)
    private boolean success;
    // 返回消息
    @ApiModelProperty(value="返回消息")
    private String msg;
    // 返回编码
    @ApiModelProperty(value="返回编码 [1:成功，x00000：系统错误,请稍后重试或者联系管理员，x00001：验证码不正确，x00002：用户名不能为空，x00003：密码不能为空"
    		+ "，x00004：账号或密码错误，x00005：账号未启用，x00006：密码错误，x01001：登录用户名已存在，x01002：没有权限",required=true)
    private String code;
    // 返回内容
    @ApiModelProperty(value="返回内容")
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CommonResponse [success=" + success + ", msg=" + msg + ", code=" + code + ", data=" + data + "]";
	}

}
