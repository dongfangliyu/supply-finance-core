package cn.fintecher.supply.finance.loan.manager.common.sys;

import java.io.Serializable;

public class EmpResponse implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SysUserAdminEntity emp;

	public SysUserAdminEntity getEmp() {
		return emp;
	}

	public void setEmp(SysUserAdminEntity emp) {
		this.emp = emp;
	}

	
}
