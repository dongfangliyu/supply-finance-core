package cn.fintecher.supply.finance.loan.manager.common.sys;


import cn.fintecher.supply.finance.loan.manager.common.util.group.AddGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 * 
 * @author hujinbao
 */
@Data
public class SysRoleEntity implements Serializable {

	private static final long serialVersionUID = -7077435484252475363L;
	
	/**
	 * 角色ID
	 */
	@ApiModelProperty(value="角色ID")
	private Integer roleId;

	/**
	 * 角色名称
	 */
	@ApiModelProperty(value="角色名称")
	@NotBlank(message="角色名称不能为空", groups = {AddGroup.class})
    private String roleName;

	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
    private String remark;

	/**
	 * 部门名称
	 */
	@ApiModelProperty(value="部门名称")
    private Integer deptId;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
    private String createTime;

	/**
	 *状态   1：正常，2：停用，0：删除
	 */
	@ApiModelProperty(value="状态   1：正常，2：停用，0：删除")
    private String status;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value="修改时间")
    private String updateTime;

	/**
	 * 创建数据的用户ID
	 */
	@ApiModelProperty(value="创建数据的用户ID")
    private Integer createUserId;

	/**
	 * 修改数据的用户ID
	 */
	@ApiModelProperty(value="修改数据的用户ID")
    private Integer updateUserId;

	@ApiModelProperty(value="角色code")
	@NotBlank(message="角色code不能为空", groups = {AddGroup.class})
    private String roleCode; //角色code  配合工作流使用
    
    /**
	 * 菜单列表
	 */
	@ApiModelProperty(value="菜单Id列表")
    private List<Integer> menuIdList;
    
    /**
     * 部门列表
     */
	@ApiModelProperty(value="部门Id列表")
	private List<Integer> deptIdList;

	/**
	 * 当前操作人userName
	 * */
	private String operateByName;

}
