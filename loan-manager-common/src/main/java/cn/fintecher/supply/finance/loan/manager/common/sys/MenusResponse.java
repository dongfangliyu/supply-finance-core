package cn.fintecher.supply.finance.loan.manager.common.sys;

import java.io.Serializable;
import java.util.List;

public class MenusResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SysMenuEntity> menus;

	public List<SysMenuEntity> getMenus() {
		return menus;
	}

	public void setMenus(List<SysMenuEntity> menus) {
		this.menus = menus;
	}
	
	

}
