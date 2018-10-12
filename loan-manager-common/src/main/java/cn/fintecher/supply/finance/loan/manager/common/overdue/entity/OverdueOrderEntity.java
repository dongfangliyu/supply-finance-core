package  cn.fintecher.supply.finance.loan.manager.common.overdue.entity;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
/**
 * 逾期订单
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 17:42:39
 */
@Data
public class OverdueOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	@ApiModelProperty(value="")
	private Long pid;
	
	/**
	*核心企业id
	*/
	@ApiModelProperty(value="核心企业id")
	private Long enterpriseId;
	
	/**
	*核心企业名称
	*/
	@ApiModelProperty(value="核心企业名称")
	private String enterpriseName;
	
	/**
	*核心企业联系人
	*/
	@ApiModelProperty(value="核心企业联系人")
	private String enterpriseMan;
	
	/**
	*核心企业联系人电话
	*/
	@ApiModelProperty(value="核心企业联系人电话")
	private String enterpriseMobile;
	
	/**
	*供应商id
	*/
	@ApiModelProperty(value="供应商id")
	private Long supplierId;
	
	/**
	*供应商名称
	*/
	@ApiModelProperty(value="供应商名称")
	private String supplierName;
	
	/**
	*供应商联系人
	*/
	@ApiModelProperty(value="供应商联系人")
	private String supplierMan;
	
	/**
	*供应商联系人电话
	*/
	@ApiModelProperty(value="供应商联系人电话")
	private String supplierMobile;
	
	/**
	*商务合同编号
	*/
	@ApiModelProperty(value="商务合同编号")
	private String contractNo;
	
	/**
	*应收账款编号（保理合同编号）
	*/
	@ApiModelProperty(value="应收账款编号（保理合同编号）")
	private String accountNo;
	
	/**
	*账款凭证金额
	*/
	@ApiModelProperty(value="账款凭证金额")
	private String certificateAmount;
	
	/**
	*审批金额（放款金额）
	*/
	@ApiModelProperty(value="审批金额（放款金额）")
	private Long approvalAmount;
	
	/**
	*审批期限（放款期限）
	*/
	@ApiModelProperty(value="审批期限（放款期限）")
	private Integer approvalTerm;
	
	/**
	*放款时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="放款时间")
	private Date loanTime;
	
	/**
	*放款周期
	*/
	@ApiModelProperty(value="放款周期")
	private String loanCycle;
	
	/**
	*利率
	*/
	@ApiModelProperty(value="利率")
	private Integer interestRate;
	
	/**
	*利息
	*/
	@ApiModelProperty(value="利息")
	private Long interestPrice;
	
	/**
	*平台服务费率
	*/
	@ApiModelProperty(value="平台服务费率")
	private Integer serviceFee;
	
	/**
	*平台服务费
	*/
	@ApiModelProperty(value="平台服务费")
	private Long servicePrice;
	
	/**
	*罚息费率（日）
	*/
	@ApiModelProperty(value="罚息费率（日）")
	private Integer penaltyFee;
	
	/**
	*已还本金
	*/
	@ApiModelProperty(value="已还本金")
	private Long returnedPrincipal;
	
	/**
	*已还利息
	*/
	@ApiModelProperty(value="已还利息")
	private Long returnedInterest;
	
	/**
	*已付罚息
	*/
	@ApiModelProperty(value="已付罚息")
	private Long returnedPenalty;
	
	
	
	/**
	*未还本金
	*/
	@ApiModelProperty(value="未还本金")
	private Long unpaidPrincipal;
	
	/**
	*未付利息
	*/
	@ApiModelProperty(value="未付利息")
	private Long unpaidInterest;
	
	/**
	*未还平台服务费
	*/
	@ApiModelProperty(value="未还平台服务费")
	private Long unpaidService;
	
	/**
	*已还平台服务费
	*/
	@ApiModelProperty(value="已还平台服务费")
	private Long returnedService;
	
	/**
	*是否结清
	*/
	@ApiModelProperty(value="是否结清")
	private String settle;
	
	/**
	*结清时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="结清时间")
	private Date settleTime;
	
	/**
	*审核订单id
	*/
	@ApiModelProperty(value="审核订单id")
	private Long orderInfoId;
	
	/**
	*创建人账号
	*/
	@ApiModelProperty(value="创建人账号")
	private String createBy;
	
	/**
	*创建时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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