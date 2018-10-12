package  cn.fintecher.supply.finance.loan.manager.common.audit.entity;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
/**
 * 审批历史
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 17:03:17
 */
@Data
public class AuditTaskHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*
	*/
	@ApiModelProperty(value="")
	private Long pid;
	
	/**
	*节点  “初审”“复审”“终审” 
	*/
	@ApiModelProperty(value="节点  “初审”“复审”“终审” ")
	private String node;
	
	/**
	*审批时间
	*/
	@ApiModelProperty(value="审批时间")
	private Date approvalTime;
	
	/**
	*审批结果  “初审通过”“初审拒绝”等
	*/
	@ApiModelProperty(value="审批结果  “初审通过”“初审拒绝”等")
	private String result;
	
	/**
	*审批状态  1通过  2拒绝  3取消  4回退
	*/
	@ApiModelProperty(value="审批状态  1通过  2拒绝  3取消  4回退")
	private String resultStatus;
	
	/**
	*审批金额
	*/
	@ApiModelProperty(value="审批金额")
	private String applicationAmount;
	
	/**
	*0 不担保 1担保
	*/
	@ApiModelProperty(value="0 不担保 1担保")
	private String guarantee;
	
	/**
	*审批期限
	*/
	@ApiModelProperty(value="审批期限")
	private Integer applicationTerm;
	
	/**
	*审批人
	*/
	@ApiModelProperty(value="审批人")
	private String approvalMan;
	
	/**
	*订单id
	*/
	@ApiModelProperty(value="订单id")
	private Long orderId;
	
	/**
	*审批意见
	*/
	@ApiModelProperty(value="审批意见")
	private String content;
	
	/**
	*产品类型
	*/
	@ApiModelProperty(value="产品类型")
	private String productType;
	
	/**
	*产品代号
	*/
	@ApiModelProperty(value="产品代号")
	private String productNo;
	
	/**
	*审批单价
	*/
	@ApiModelProperty(value="审批单价")
	private BigDecimal contractApplyPrice;
	
	/**
	*审批质押率
	*/
	@ApiModelProperty(value="审批质押率")
	private BigDecimal contractApplyPledgeRate;
	
	/**
	*合同版本号
	*/
	@ApiModelProperty(value="合同版本号")
	private String contractNo;
	
	/**
	*类型 1反向保理 2仓单质押
	*/
	@ApiModelProperty(value="类型 1反向保理 2仓单质押")
	private String type;
	
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
	*更新时间
	*/
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

}