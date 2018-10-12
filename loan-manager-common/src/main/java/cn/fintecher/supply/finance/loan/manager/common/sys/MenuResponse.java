package cn.fintecher.supply.finance.loan.manager.common.sys;

import java.io.Serializable;

public class MenuResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SysMenuEntity menu;

	public SysMenuEntity getMenu() {
		return menu;
	}

	public void setMenu(SysMenuEntity menu) {
		this.menu = menu;
	}

}
