package cn.fintecher.supply.finance.loan.manager.common.audit.request;


import java.io.Serializable;

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
public class AuditSumbitContentRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	
	/**
	*审批id
	*/
	@ApiModelProperty(value="审批id")
	private Long id;
	
	/**
	 *审批结果
	 */
	@ApiModelProperty(value="审批结果")
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
	

	
	
	@ApiModelProperty(value="当前操作人用户名称",hidden = true)
	public String userName;
	
	
	
	
	
	

}