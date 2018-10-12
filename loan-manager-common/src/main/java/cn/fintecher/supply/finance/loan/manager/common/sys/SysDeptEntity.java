package cn.fintecher.supply.finance.loan.manager.common.sys;

import cn.fintecher.supply.finance.loan.manager.common.util.group.AddGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;


/**
 * 部门管理
 * 
 * @author hujinbao
 */
@Data
public class SysDeptEntity implements Serializable {

	private static final long serialVersionUID = 3530695570580136661L;

	/**
	 * 部门ID
	 */
	@ApiModelProperty(value="部门ID")
	private Integer deptId;

	/**
	 * 上级部门ID，参数值0 : 为夫级，否则为子集
	 */
	@ApiModelProperty(value="上级部门ID，参数值0 : 为夫级，否则为子集")
	private Integer parantId;

	/**
	 * 部门名称
	 */
	@NotBlank(message="部门名称不能为空", groups = {AddGroup.class})
	@ApiModelProperty(value="部门名称")
	private String name;

	/**
	 * 排序
	 */
	@ApiModelProperty(value="排序")
	private Integer orderNum;

	/**
	 * 状态，'1:正常,0:删除'
	 */
	@ApiModelProperty(value="状态，'1:正常,0:删除'")
	private String status;

	/**
	 * 部门类型，1：公司，2：部门
	 */
	@ApiModelProperty(value="部门类型，1：公司，2：部门")
	private String deptType;

	/**
	 * 地址
	 */
	@ApiModelProperty(value="地址")
	private String address;

	/**
	 * 电话
	 */
	@ApiModelProperty(value="电话")
	private String tel;

	/**
	 * 邮箱
	 */
	@ApiModelProperty(value="邮箱")
	private String email;

	/**
	 * 负责人
	 */
	@ApiModelProperty(value="负责人")
	private String custodianName;

	/**
	 * 负责人手机号码
	 */
	@ApiModelProperty(value="负责人手机号码")
	private String mobile;
	
	/**
	 * 部门编号
	 */
	@ApiModelProperty(value="部门编号")
	private String number;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private String createTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间")
	private String updateTime;

	/**
	 * 部门所属公司，参数值为自己的ID时，本身是公司
	 */
	@ApiModelProperty(value="部门所属公司，参数值为自己的ID时，本身是公司")
	private Integer compId;

	/**
	 * 创建人的ID
	 */
	@ApiModelProperty(value="创建人ID")
	private Integer createUserId;

	/**
	 * 更新人的ID
	 */
	@ApiModelProperty(value="更新人ID")
	private Integer updateUserId;

	/**
	 * 当前操作人userName
	 * */
	private String operateByName;
}
