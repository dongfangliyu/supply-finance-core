package cn.fintecher.supply.finance.loan.manager.common.sys;

import java.io.Serializable;
import java.util.List;

public class DeptsResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SysDeptEntity> depts;

	public List<SysDeptEntity> getDepts() {
		return depts;
	}

	public void setDepts(List<SysDeptEntity> depts) {
		this.depts = depts;
	}
	
	

}
