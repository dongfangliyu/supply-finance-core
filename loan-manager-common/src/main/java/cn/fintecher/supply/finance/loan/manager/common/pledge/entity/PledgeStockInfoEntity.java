package  cn.fintecher.supply.finance.loan.manager.common.pledge.entity;


import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
/**
 * 仓单质押申请信息表
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 14:14:01
 */
@Data
public class PledgeStockInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*记录id
	*/
	@ApiModelProperty(value="记录id")
	private Long pid;
	
	/**
	*来源 commodity_stock_info(入库信息表) 的pid 
	*/
	@ApiModelProperty(value="来源 commodity_stock_info(入库信息表) 的pid ")
	private Long commodityId;
	
	/**
	*质押状态 融资未申请:0 ; 融资申请通过:1； 融资申请中:2 ， 默认为空
	*/
	@ApiModelProperty(value="质押状态 融资未申请:0 ; 融资申请通过:1； 融资申请中:2 ， 默认为空")
	private String pledgeApplyState;
	
	/**
	*质押申请时间
	*/
	@ApiModelProperty(value="质押申请时间")
	private Date pledgeApplyTime;
	
	/**
	*质押申请通过时间/质押申请未通过时间
	*/
	@ApiModelProperty(value="质押申请通过时间/质押申请未通过时间")
	private Date pledgeNoPassTime;
	
	/**
	*期望融资金额
	*/
	@ApiModelProperty(value="期望融资金额")
	private BigDecimal pledgeFinanceAmount;
	
	/**
	*预计使用期限 30,60,90,120
	*/
	@ApiModelProperty(value="预计使用期限 30,60,90,120")
	private Long pledgeTerm;
	
	/**
	*资金用途
	*/
	@ApiModelProperty(value="资金用途")
	private String pledgeEarmarking;
	
	/**
	*仓单质押状态
 		0待领取 1待初审 2拒绝 3取消 4回退 10初审通过/待领取 
 		11待复审 12拒绝 13取消 14回退 20复审通过/待领取 
 		21待终审  22拒绝 23取消 24回退 30终审通过
	*/
	@ApiModelProperty(value="仓单质押状态 0待领取 1待初审 2拒绝 3取消 4回退 10初审通过/待领取 11待复审 12拒绝 13取消 14回退 20复审通过/待领取 21待终审  22拒绝 23取消 24回退 30终审通过")
	private String trialState;
	
	/**
	*签约状态：待签约:0 ; 已签约:1     默认为空
	*/
	@ApiModelProperty(value="签约状态：待签约:0 ; 已签约:1     默认为空")
	private String contractApplyState;
	
	/**
	*用户签约时间
	*/
	@ApiModelProperty(value="用户签约时间")
	private Date contractUserTime;
	
	/**
	*审批质押率
	*/
	@ApiModelProperty(value="审批质押率")
	private BigDecimal contractApplyPledgeRate;
	
	/**
	*审批期限
	*/
	@ApiModelProperty(value="审批期限")
	private Long contractApplyTerm;
	
	/**
	*审批金额
	*/
	@ApiModelProperty(value="审批金额")
	private BigDecimal contractApplyAmount;
	
	/**
	*审批单价
	*/
	@ApiModelProperty(value="审批单价")
	private BigDecimal contractApplyPrice;
	
	/**
	*服务费
	*/
	@ApiModelProperty(value="服务费")
	private BigDecimal contractApplyServiceFee;
	
	/**
	*利息
	*/
	@ApiModelProperty(value="利息")
	private BigDecimal contractApplyInterest;
	
	/**
	*借款期限
	*/
	@ApiModelProperty(value="借款期限")
	private Long contractApproveTerm;
	
	/**
	*电子签约 已签约 1，未签约 0
	*/
	@ApiModelProperty(value="电子签约 已签约 1，未签约 0")
	private String contractElectronics;
	
	/**
	*审批金额
	*/
	@ApiModelProperty(value="审批金额")
	private BigDecimal contractApproveAmount;
	
	/**
	*产品id （pro_product)产品表
	*/
	@ApiModelProperty(value="产品id （pro_product)产品表")
	private Long proRoductId;
	
	/**
	*合同ID  合同表pro_contract
	*/
	@ApiModelProperty(value="合同ID  合同表pro_contract")
	private Long proContractId;
	
	/**
	*待放款 0，已放款 1，放款拒绝 2 
	*/
	@ApiModelProperty(value="待放款 0，已放款 1，放款拒绝 2 ")
	private String loanState;
	
	/**
	*放款时间
	*/
	@ApiModelProperty(value="放款时间")
	private Date loanTime;
	
	/**
	*放款周期开始时间
	*/
	@ApiModelProperty(value="放款周期开始时间")
	private Date loanStartTime;
	
	/**
	*放款周期结束时间
	*/
	@ApiModelProperty(value="放款周期结束时间")
	private Date loanEndTime;
	
	/**
	*仓单解压状态 已解压 1，未解压 0
	*/
	@ApiModelProperty(value="仓单解压状态 已解压 1，未解压 0")
	private String releasePledge;
	
	/**
	*仓单解压解压时间
	*/
	@ApiModelProperty(value="仓单解压解压时间")
	private Date releaseTime;
	
	/**
	*申请解押时间
	*/
	@ApiModelProperty(value="申请解押时间")
	private Date releaseApplTime;
	
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

	/**
	*平台签约时间
	*/
	@ApiModelProperty(value="平台签约时间")
	private Date contractPlatformTime;

	/**
	 * 放款状态
	 */
	private String financeState;

	/**
	 * 还款日期
	 */
	@ApiModelProperty(value="还款日期")
	private Date releaseRepaymentTime;

}