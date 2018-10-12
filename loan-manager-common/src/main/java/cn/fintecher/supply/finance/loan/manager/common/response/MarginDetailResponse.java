package cn.fintecher.supply.finance.loan.manager.common.response;

import lombok.Data;

/**
 * @author lss
 * @date 2018-8-28
 */
@Data
public class MarginDetailResponse {
	
	/**
	 * 还款时间
	 */
	private java.util.Date repaymentTime;
	/**
	*还款金额
	*/
	private Long repaymentPrice;
	/**
	*完整路径
	*/
	private String fullPath;

}
