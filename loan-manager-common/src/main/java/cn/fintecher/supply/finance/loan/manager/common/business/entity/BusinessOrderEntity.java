package  cn.fintecher.supply.finance.loan.manager.common.business.entity;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
/**
 * 订单
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 14:59:16
 */
@Data
public class BusinessOrderEntity implements Serializable {
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
	*合同编号
	*/
	@ApiModelProperty(value="合同编号")
	private String contractNo;
	
	/**
	*账款凭证金额
	*/
	@ApiModelProperty(value="账款凭证金额")
	private String certificateAmount;
	
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
	 *应收账款确认时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="应收账款确认时间")
	private Date accountConfirmTime;
	
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
	*确认状态   0未确认  1已确认
	*/
	@ApiModelProperty(value="确认状态   0未确认  1已确认")
	private String state;
	
	/**
	*邀请时间
	*/
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="邀请时间")
	private Date inviteTime;
	
	/**
	*文件对应的code
	*/
	@ApiModelProperty(value="文件对应的code")
	private String orderCode;
	
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
	 *账款凭证编号
	 */
	@ApiModelProperty(value="账款凭证编号")
	private String accountNo;
	
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
	 *平台返佣
	 */
	@ApiModelProperty(value="平台返佣")
	private Integer rebateRatio;
	
	/**
	 *提交申请时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="提交申请时间")
	private Date submitTime;
	
	/**
	 *申请通过时间
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value="申请通过时间")
	private Date passingTime;
	
	/**
	 *融资申请状态
	 */
	@ApiModelProperty(value="融资申请状态 0 待申请，1申请中，2申请通过， 3申请不通过")
	private String financingStatus;
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
	 *流程状态
	 */
	@ApiModelProperty(value="流程状态 0待领取 1待初审 2拒绝 3取消 4回退  10初审通过/待领取 11待复审 12拒绝 13取消 14回退 20复审通过/待领取 21待终审  22拒绝 23取消 24回退 30终审/待担保 31平台通过/供应商待审核  32平台拒绝  33供应商拒绝40供应商通过/待签约")
	private String processStatus;

	/**
	 *利息
	 */
	@ApiModelProperty(value="利息")
	private String interest;

	/**
	 *服务费用
	 */
	@ApiModelProperty(value="服务费用")
	private String serviceMoney;
}