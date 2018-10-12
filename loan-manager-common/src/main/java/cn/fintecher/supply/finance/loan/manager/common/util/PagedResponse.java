package cn.fintecher.supply.finance.loan.manager.common.util;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 带分页的返回模型
 * @author huanglei
 *
 * @param <T>
 */
public class PagedResponse<T> implements Serializable {

	private static final long serialVersionUID = -8835791622389513023L;

	// 总条数
	@ApiModelProperty(value="总条数")
	private long total;

	// 记录
	@ApiModelProperty(value="数据集合")
	private T data;

	// 是否成功
	@ApiModelProperty(value="是否成功[true:成功，false:失败]")
	private boolean success = false;

	// 返回信息
	@ApiModelProperty(value="返回信息")
	private String msg;

    // 返回编码
	@ApiModelProperty(value="返回编码 [1:成功，x00000：系统错误,请稍后重试或者联系管理员，x00001：验证码不正确，x00002：用户名不能为空，x00003：密码不能为空" +
    "，x00004：账号或密码错误，x00005：账号未启用，x00006：密码错误，x01001：登录用户名已存在，x01002：没有权限")
    private String code;

	// 页脚
	@ApiModelProperty(value="页脚",hidden=true)
	private List<Map<String,String>> footer;

	// 排序字段
	@ApiModelProperty(value="排序字段",hidden=true)
    private String sort;
    // 顺序
	@ApiModelProperty(value="顺序",hidden=true)
    private String order;

	@ApiModelProperty(value="当前第几页")
    private int pageNo;
    
	@ApiModelProperty(value="每页显示多少条")
    private int pageSize;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

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

	public List<Map<String,String>> getFooter() {
		return footer;
	}

	public void setFooter(List<Map<String,String>> footer) {
		this.footer = footer;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
