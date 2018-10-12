package cn.fintecher.supply.finance.loan.manager.common.pledge.response;


import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 审核意见
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 16:01:09
 */
@Data
public class PledgeSumbitTrialRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	
	/**
	*审批id
	*/
	@ApiModelProperty(value="审批id")
	private Long id;
	
	/**
	 *审批结果
	 */
	@ApiModelProperty(value="审批结果 2拒绝 3取消 4通过  ")
	private String result;
	
	/**
	 *审批意见
	 */
	@ApiModelProperty(value="审批意见")
	private String content;
	
	/**
	 *审批金额
	 */
	@ApiModelProperty(value="审批金额")
	private String approvalAmount;
	
	/**
	 *审批期限
	 */
	@ApiModelProperty(value="审批期限")
	private String approvalTerm;
	
	
	@ApiModelProperty(value="产品类型")
	private String productType;
	
	
	
	@ApiModelProperty(value="产品代号")
	private String productNo;
	
	@ApiModelProperty(value="产品代号id")
	private Long productNoId;
	
	
	@ApiModelProperty(value="合同版本号")
	private String contractNo;
	
	@ApiModelProperty(value="合同版本号id")
	private Long contractNoId;
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

	
	
	@ApiModelProperty(value="当前操作人用户名称",hidden = true)
	public String userName;
	
	
	
	
	
	

}