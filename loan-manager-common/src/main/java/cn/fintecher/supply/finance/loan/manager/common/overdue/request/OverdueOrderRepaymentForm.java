package cn.fintecher.supply.finance.loan.manager.common.overdue.request;


import java.io.Serializable;

import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 逾期还款提交
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 16:01:09
 */
@Data
public class OverdueOrderRepaymentForm implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@ApiModelProperty(value="orderId")
	public Long id;

	@ApiModelProperty(value="还款时间")
	public String repaymentTime;
	
	@ApiModelProperty(value="还款金额")
	public String repaymentPrice;
	
	@ApiModelProperty(value="当前操作人用户id",hidden = true)
	public String userId;
	
	@ApiModelProperty(value="数据状态，可用状态",hidden = true)
    public String status = Constants.DATA_STATUS_NOL;
	
	

	

}