package cn.fintecher.supply.finance.loan.manager.common.sys;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 角色查询条件
 * @author hujinbao
 *
 */
@JsonIgnoreProperties
public class RoleSearchForm extends PageInfo implements Serializable {
	
	@ApiModelProperty(value="角色名称")
    private String roleName;
	@ApiModelProperty(value="状态")
    private Integer status;

    private static final long serialVersionUID = 1L;

	

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
}