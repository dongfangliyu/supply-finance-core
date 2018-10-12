package cn.fintecher.supply.finance.loan.manager.common.sys;

import java.io.Serializable;
import java.util.List;

public class RolesResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SysRoleEntity> roles;

	public List<SysRoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRoleEntity> roles) {
		this.roles = roles;
	}
	
	
}
