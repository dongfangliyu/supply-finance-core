package  cn.fintecher.supply.finance.loan.manager.common.business.entity;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
/**
 * 应收账款
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
@Data
public class BusinessReceivableEntity implements Serializable {
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
	*供应商id（供应商为注册时为链属id）
	*/
	@ApiModelProperty(value="供应商id（供应商为注册时为链属id）")
	private Long supplierId;
	
	/**
	*供应商名称（供应商为注册时为链属名称）
	*/
	@ApiModelProperty(value="供应商名称（供应商为注册时为链属名称）")
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
	*账款凭证类型
	*/
	@ApiModelProperty(value="账款凭证类型")
	private String certificateCategory;
	
	/**
	*账款凭证类型名称
	*/
	@ApiModelProperty(value="账款凭证类型名称")
	private String certificateCategoryValue;
	
	/**
	*邀请状态   0未邀请  1已邀请
	*/
	@ApiModelProperty(value="邀请状态   0未邀请  1已邀请")
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
	

}