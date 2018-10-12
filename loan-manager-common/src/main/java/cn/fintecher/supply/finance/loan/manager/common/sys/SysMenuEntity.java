package cn.fintecher.supply.finance.loan.manager.common.sys;


import cn.fintecher.supply.finance.loan.manager.common.util.group.AddGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单管理
 * 
 * @author hujinbao
 */
@Data
public class SysMenuEntity implements Serializable {

	private static final long serialVersionUID = 7698292695971098136L;
	
	
	private List<SysMenuEntity> childrenList;
	/**
	 * 菜单ID
	 */
	@ApiModelProperty(value="菜单ID")
	private Integer menuId;
	
	/**
	 * 夫级菜单，顶级为0
	 */
	@ApiModelProperty(value="夫级菜单，顶级为0")
	private Integer parantId;

	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value="菜单名称")
	@NotBlank(message="菜单名称不能为空", groups = {AddGroup.class})
	private String name;

	/**
	 * 菜单URL
	 */
	@ApiModelProperty(value="菜单URL")
	@NotBlank(message="URL不能为空", groups = {AddGroup.class})
	private String url;

	/**
	 * shiro perms字段
	 */
	@ApiModelProperty(value="shiro perms字段")
	private String perms;

	/**
	 * 类型    0:目录，1：菜单，2：按钮
	 */
	@ApiModelProperty(value="类型    0:目录，1：菜单，2：按钮")
	private Integer type;

	/**
	 * 排序
	 */
	@ApiModelProperty(value="排序")
	private Integer orderNum;

	/**
	 * 状态  0:停用，1：正常
	 */
	@ApiModelProperty(value="状态  0:停用，1：正常")
	private String status;

	/**
	 * 创建者ID
	 */
	@ApiModelProperty(value="创建者ID")
	private Integer createUserId;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private String createTime;

	/**
	 * 更新者ID
	 */
	@ApiModelProperty(value="更新者ID")
	private String updateTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间")
	private Integer updateUserId;
	
	/**
	 * 图标
	 */
	@ApiModelProperty(value="图标")
	private String icon;

	/**
	 * code
	 */
	@ApiModelProperty(value="code")
	private String code;

	/**
	 * 当前操作人userName
	 * */
	private String operateByName;
}
