package cn.fintecher.supply.finance.loan.manager.common.sys;

import java.io.Serializable;
import java.util.List;

public class EmpsResponse implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SysUserAdminEntity> emps;

	public List<SysUserAdminEntity> getEmps() {
		return emps;
	}

	public void setEmps(List<SysUserAdminEntity> emps) {
		this.emps = emps;
	}
	
	
}
