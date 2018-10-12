package cn.fintecher.supply.finance.loan.manager.common.sys;

import java.io.Serializable;

public class RoleResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SysRoleEntity role;

	public SysRoleEntity getRole() {
		return role;
	}

	public void setRole(SysRoleEntity role) {
		this.role = role;
	}

	
	
}
