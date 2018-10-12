package cn.fintecher.supply.finance.loan.manager.common.sys;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 * 
 * @author hujinbao
 */
public class SysRoleUserEntity implements Serializable {

	private static final long serialVersionUID = -9187134120051910716L;

	/**
	 * 角色ID
	 */
	private Integer userId ;

	/**
	 * 用户ID
	 */
    private List<Integer> roleIdList;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Integer> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}
}
