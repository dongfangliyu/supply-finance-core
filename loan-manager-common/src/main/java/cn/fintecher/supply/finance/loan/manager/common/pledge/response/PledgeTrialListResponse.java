package cn.fintecher.supply.finance.loan.manager.common.pledge.response;


import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 入库信息表
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
@Data
public class PledgeTrialListResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	*记录id
	*/
	@ApiModelProperty(value="记录id")
	private Long pid;
	
	
	/**
	*仓单编号
	*/
	@ApiModelProperty(value="仓单编号")
	private String applyNumber;
	
	/**
	*客户名称
	*/
	@ApiModelProperty(value="客户名称")
	private String customerName;
	
	/**
	 *供货商名称
	 */
	@ApiModelProperty(value="供货商名称")
	private String supplierName;
	
	/**
	*总价值
	*/
	@ApiModelProperty(value="总价值")
	private BigDecimal totalPrice;
	
	/**
	*申请金额
	*/
	@ApiModelProperty(value="申请金额")
	private BigDecimal pledgeFinanceAmount;
	
	/**
	*预计使用期限 30,60,90,120
	*/
	@ApiModelProperty(value="预计使用期限 30,60,90,120")
	private Long pledgeTerm;
	
	/**
	*状态  0待领取 1待审核 2拒绝 3取消 4通过
	*/
	@ApiModelProperty(value="状态  0待领取 1待审核 2拒绝 3取消 4通过")
	private String state;
	
	/**
	 *审核时间
	 */
	@ApiModelProperty(value="审核时间")
	private String trialTime;
	

}