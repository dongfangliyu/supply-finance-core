package cn.fintecher.supply.finance.loan.manager.common.sys;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 当前登录用户信息
 */
public class ShiroAdminUser implements Serializable {

	private static final long serialVersionUID = 1255065441334374823L;
	
	private Integer id;
    private String loginName;
    private String realName;
    private Integer companyId;
    private Integer deptId;
    private Integer depotId;
    private String company;
    private String dept;
    private String depot;
    private String status;
    private String phone;
    private String position;
    Set<String> roleSet;
    Set<String> urlSet;
    List<SysMenuEntity> mudules;
    List<SysRoleEntity> roles;
    private Integer roleId;
    private String roleCode;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStatus() {
		return status;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<String> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<String> roleSet) {
		this.roleSet = roleSet;
	}

	public Set<String> getUrlSet() {
		return urlSet;
	}

	public void setUrlSet(Set<String> urlSet) {
		this.urlSet = urlSet;
	}

	public String getName() {
        return loginName;
    }


	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public List<SysMenuEntity> getMudules() {
		return mudules;
	}

	public void setMudules(List<SysMenuEntity> mudules) {
		this.mudules = mudules;
	}

	public List<SysRoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRoleEntity> roles) {
		this.roles = roles;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getDepotId() {
		return depotId;
	}

	public void setDepotId(Integer depotId) {
		this.depotId = depotId;
	}

	public String getDepot() {
		return depot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}

	/**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return loginName;
    }

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}