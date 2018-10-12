package cn.fintecher.supply.finance.loan.manager.common.audit.entity;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
 * 
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-21 17:17:15
 */
@Data
public class AuditTaskEntryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	@ApiModelProperty(value="")
	private Long pid;
	
	/**
	*还款时间
	*/
	@ApiModelProperty(value="还款时间")
	private Date repaymentTime;
	
	/**
	*还款金额
	*/
	@ApiModelProperty(value="还款金额")
	private Long repaymentPrice;
	
	/**
	*汇款账户
	*/
	@ApiModelProperty(value="汇款账户")
	private String account;
	/**
	*汇款账户类型
	*/
	@ApiModelProperty(value="汇款账户类型")
	private String accountType;

	/**
	*凭证id
	*/
	@ApiModelProperty(value="凭证id")
	private Long resourceId;
	
	/**
	*录入者    0orderInfoId  1核心企业 enterId
	*/
	@ApiModelProperty(value="录入者    0orderInfoId  1核心企业 enterId")
	private Long objectId;
	
	/**
	*录入者类型   0平台  1核心企业
	*/
	@ApiModelProperty(value="录入者类型   0平台  1核心企业")
	private String objectType;
	
	/**
	*复核状态  0未复核  1已复核  2已驳回
	*/
	@ApiModelProperty(value="复核状态  0未复核  1已复核  2已驳回")
	private String state;
	
	/**
	*还款时逾期状态  0未逾期  1已逾期  
	*/
	@ApiModelProperty(value="还款时逾期状态  0未逾期  1已逾期  ")
	private String type;
	
	/**
	*拥有者
	*/
	@ApiModelProperty(value="拥有者")
	private Long orderInfoId;
	
	/**
	*创建人账号
	*/
	@ApiModelProperty(value="创建人账号")
	private String createBy;
	
	/**
	*创建时间
	*/
	@ApiModelProperty(value="创建时间")
	private Date createAt;
	
	/**
	*更新人账号
	*/
	@ApiModelProperty(value="更新人账号")
	private String updateBy;
	
	/**
	*更新时间
	*/
	@ApiModelProperty(value="更新时间")
	private Date updateAt;
	
	/**
	*状态 DEL删除，STP停用，NOL正常
	*/
	@ApiModelProperty(value="状态 DEL删除，STP停用，NOL正常")
	private String status;
	
	/**
	*备注
	*/
	@ApiModelProperty(value="备注")
	private String remark;
	

}