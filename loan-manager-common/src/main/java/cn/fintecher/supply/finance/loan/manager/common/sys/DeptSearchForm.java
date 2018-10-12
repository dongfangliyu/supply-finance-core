package cn.fintecher.supply.finance.loan.manager.common.sys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 部门查询条件
 * @author hujinbao
 *
 */
@JsonIgnoreProperties
public class DeptSearchForm implements Serializable {
	
	@ApiModelProperty(value="部门名称")
    private String deptName;
	@ApiModelProperty(value="状态")
    private Integer status;

    private static final long serialVersionUID = 1L;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName ==null ? null : deptName.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
}