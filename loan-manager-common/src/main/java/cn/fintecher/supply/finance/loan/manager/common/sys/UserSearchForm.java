package cn.fintecher.supply.finance.loan.manager.common.sys;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户查询条件
 * @author fengqiang
 *
 */
@JsonIgnoreProperties
public class UserSearchForm extends PageInfo implements Serializable {
	
	@ApiModelProperty(value="登录名")
    private String username;
	@ApiModelProperty(value="状态")
    private Integer status;
    @ApiModelProperty(value="手机号")
    private String mobile;
    @ApiModelProperty(value="职位")
    private String position;
    @ApiModelProperty(value="真实姓名")
    private String realName;
    @ApiModelProperty(value="部门ID")
    private Integer deptId;
    @ApiModelProperty(value="公司ID")
    private Integer compId;
    @ApiModelProperty(value="角色名称")
    private String rolename;

    private static final long serialVersionUID = 1L;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position == null ? null : position.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename == null ? null : rolename.trim();
	}
	
	
}