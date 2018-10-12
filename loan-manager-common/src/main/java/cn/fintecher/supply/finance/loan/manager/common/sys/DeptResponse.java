package cn.fintecher.supply.finance.loan.manager.common.sys;

import java.io.Serializable;

public class DeptResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SysDeptEntity dept;

	public SysDeptEntity getDept() {
		return dept;
	}

	public void setDept(SysDeptEntity dept) {
		this.dept = dept;
	}

	
	
	

}
