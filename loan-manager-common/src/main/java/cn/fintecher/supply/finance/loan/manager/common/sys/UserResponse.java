package cn.fintecher.supply.finance.loan.manager.common.sys;

import java.io.Serializable;

public class UserResponse implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SysUserAdminEntity user;

	public SysUserAdminEntity getUser() {
		return user;
	}

	public void setUser(SysUserAdminEntity user) {
		this.user = user;
	}

}
