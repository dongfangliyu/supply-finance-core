package cn.fintecher.supply.finance.loan.manager.common.sys;


import cn.fintecher.supply.finance.loan.manager.common.util.group.AddGroup;
import cn.fintecher.supply.finance.loan.manager.common.util.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * 系统用户
 *
 * @author fengqiang
 */
public class SysUserAdminEntity implements Serializable {

    private static final long serialVersionUID = 8178577388355468705L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private transient String password;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    @NotBlank(message = "真实姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String realname;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空", groups = AddGroup.class)
    private String mobile;

    /**
     * 状态  1：正常，2：停用，0：删除
     */
    @ApiModelProperty(value = "状态  1：正常，2：停用，0：删除")
    private String status;

    /**
     * 部门Id
     */
    @ApiModelProperty(value = "部门Id")
    private Integer deptId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id")
    private Integer compId;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String compName;

    /**
     * 角色ID列表
     */
    @ApiModelProperty(value = "角色ID列表")
    private List<SysRoleEntity> roleList;

    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID")
    private Integer createUserId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    /**
     * 更新者ID
     */
    @ApiModelProperty(value = "更新者ID")
    private Integer updateUserId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String updateTime;

    /**
     * 职位
     */
    @ApiModelProperty(value = "职位")
    private String position;

    private String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 设置：用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置：密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置：手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     *
     * @return String
     */
    public String getMobile() {
        return mobile;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        this.userId = id;
    }

    /**
     * 设置：创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     *
     * @return Date
     */
    public String getCreateTime() {
        return createTime;
    }


    public List<SysRoleEntity> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRoleEntity> roleList) {
        this.roleList = roleList;
    }


    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getCompId() {
        return compId;
    }

    public void setCompId(Integer compId) {
        this.compId = compId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
        this.id = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 当前操作人userName
     */
    private String operateByName;

    public String getOperateByName() {
        return operateByName;
    }

    public void setOperateByName(String operateByName) {
        this.operateByName = operateByName;
    }
}
