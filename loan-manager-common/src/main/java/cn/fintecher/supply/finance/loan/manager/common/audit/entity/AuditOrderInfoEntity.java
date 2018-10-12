package  cn.fintecher.supply.finance.loan.manager.common.audit.entity;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 审批交易订单
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:10:50
 */
@Data
public class AuditOrderInfoEntity implements Serializable {
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
	*供应商id
	*/
	@ApiModelProperty(value="供应商id")
	private Long supplierId;
	
	/**
	*核心企业名称
	*/
	@ApiModelProperty(value="核心企业名称")
	private String enterpriseName;
	
	/**
	*供应商名称
	*/
	@ApiModelProperty(value="供应商名称")
	private String supplierName;
	
	/**
	*应收账款编号
	*/
	@ApiModelProperty(value="应收账款编号")
	private String accountno;
	
	/**
	*账款凭证金额
	*/
	@ApiModelProperty(value="账款凭证金额")
	private String certificateAmount;
	
	/**
	*申请金额
	*/
	@ApiModelProperty(value="申请金额")
	private String applicationAmount;
	
	/**
	*申请期限
	*/
	@ApiModelProperty(value="申请期限")
	private Integer applicationTerm;
	
	/**
	*审批金额
	*/
	@ApiModelProperty(value="审批金额")
	private String approvalAmount;

	/**
	*审批期限
	*/
	@ApiModelProperty(value="审批期限")
	private Integer approvalTerm;
	
	/**
	*平台企业签约时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="平台企业签约时间")
	private Date platformSigningTime;
	/**
	 *核心企业签约时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="核心企业签约时间")
	private Date entertSigningTime;
	/**
	 *财务复核时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="财务复核时间")
	private Date financialReviewTime;
	
	/**
	*初审时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="初审时间")
	private Date firstTrialTime;
	/**
	 *放款时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="放款时间")
	private Date loanTime;
	
	/**
	*复审时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="复审时间")
	private Date secondTrialTime;
	
	/**
	*终审时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="终审时间")
	private Date thirdTrialTime;
	
	/**
	*流程状态 0待领取 1待初审 2拒绝 3取消 4回退  10初审通过/待领取 11待复审 12拒绝 13取消 14回退 20复审通过/待领取 21待终审  22拒绝 23取消 24回退 30终审/待担保 31平台通过/供应商待审核  32平台拒绝  33供应商拒绝40供应商通过/待签约
	*/
	@ApiModelProperty(value="流程状态 0待领取 1待初审 2拒绝 3取消 4回退  10初审通过/待领取 11待复审 12拒绝 13取消 14回退 20复审通过/待领取 21待终审  22拒绝 23取消 24回退 30终审/待担保 31平台通过/供应商待审核  32平台拒绝  33供应商拒绝40供应商通过/待签约")
	private String state;
	
	/**
	*融资订单id
	*/
	@ApiModelProperty(value="融资订单id")
	private Long orderId;

//	@ApiModelProperty(value="是否同意擔保 0：不同意 1：同意")
//	private String guarantee;
//
//	@ApiModelProperty(value="擔保時間")
//	private String guaranteeTime;

	/**
	*是否同意担保（0：不同意   1：同意）
	*/
	@ApiModelProperty(value="是否同意担保（0：不同意   1：同意）")
	private String guarantee;

	/**
	*担保时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="担保时间")
	private Date guaranteeTime;
	
	/**
	*财务初审时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="财务初审时间")
	private Date financeFirstTime;
	
	/**
	*签约时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="签约时间")
	private Date signingTime;
	
	/**
	*签约状态
	*/
	@ApiModelProperty(value="签约状态")
	private String signingStatus;
	
	/**
	*合同编号
	*/
	@ApiModelProperty(value="合同编号")
	private String contractNo;
	
	/**
	*账款起日
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="账款起日")
	private Date accountStartTime;
	
	/**
	*账款止日
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="账款止日")
	private Date accountEndTime;
	
	/**
	*核心企业账款凭证类型
	*/
	@ApiModelProperty(value="核心企业账款凭证类型")
	private String enterCategory;
	
	/**
	*核心企业账款凭证类型名称
	*/
	@ApiModelProperty(value="核心企业账款凭证类型名称")
	private String enterCategoryValue;
	
	/**
	*供应商账款凭证类型id
	*/
	@ApiModelProperty(value="供应商账款凭证类型id")
	private String suppCategory;
	
	/**
	*供应商账款凭证类型名称
	*/
	@ApiModelProperty(value="供应商账款凭证类型名称")
	private String suppCategoryValue;
	
	/**
	*供应商文件code
	*/
	@ApiModelProperty(value="供应商文件code")
	private String suppCode;
	
	/**
	*平台服务费
	*/
	@ApiModelProperty(value="平台服务费")
	private Integer serviceFee;
	
	/**
	*利率
	*/
	@ApiModelProperty(value="利率")
	private Integer interestRate;
	
	/**
	*返佣
	*/
	@ApiModelProperty(value="返佣")
	private Integer rebateRatio;
	
	/**
	*申请通过时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="申请通过时间")
	private Date passingTime;
	
	/**
	*提交申请时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="提交申请时间")
	private Date submitTime;
	
	/**
	*邀请时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="邀请时间")
	private Date inviteTime;
	
	/**
	*应收账款确认时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="应收账款确认时间")
	private Date accountConfirmTime;
	
	/**
	*融资状态 0 待申请，1申请中，2申请通过， 3申请不通过
	*/
	@ApiModelProperty(value="融资状态 0 待申请，1申请中，2申请通过， 3申请不通过")
	private String financingStatus;
	
	/**
	*文件对应的code
	*/
	@ApiModelProperty(value="文件对应的code")
	private String orderCode;

	/**
	 *是否设置提醒 0未设置  1已设置 1
	 */
	@ApiModelProperty(value="是否设置提醒 0未设置  1已设置 1")
	private String remind;

	/**
	 *应还日期
	 */
	@ApiModelProperty(value="应还日期")
	private Date shouldTime;

	/**
	 *还款结清时间
	 */
	@ApiModelProperty(value="还款结清时间")
	private Date settleTime;

	/**
	 *还款提醒设置时间
	 */
	@ApiModelProperty(value="还款提醒设置时间")
	private Date setReminderTime;

	/**
	 *复核通过时间
	 */
	@ApiModelProperty(value="复核通过时间")
	private Date reviewAgreeTime;

	/**
	 *是否逾期 0 未逾期 1已逾期
	 */
	@ApiModelProperty(value="是否逾期 0 未逾期 1已逾期")
	private String overdueType;
	/**
	 *还款是否复核 0未复核 1已复核
	 */
	@ApiModelProperty(value="还款是否复核 0未复核 1已复核")
	private String repaymentReview;

	/**
	 *还款时间
	 */
	@ApiModelProperty(value="还款时间")
	private Date repaymentTime;
	
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
	*更新时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="更新时间")
	private Date updateAt;
	
	/**
	*更新人账号
	*/
	@ApiModelProperty(value="更新人账号")
	private String updateBy;
	
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
	
	/**
	*利息
	*/
	@ApiModelProperty(value="利息")
	private String interest;


}