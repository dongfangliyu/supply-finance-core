package cn.fintecher.supply.finance.loan.manager.common.form;

import java.io.Serializable;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import lombok.Data;

/**
 * @author lss
 * @date 2018-8-28
 */
@Data
public class PayMarginForm implements Serializable {
	
	private Long marginId;  //保兑仓id
	
	private Long bussenssFileId;  //文件id
	
	private Long companyDealerId;  //经销商id
	
	private Long repaymentPrice;  //还款金额
	
	private String bankCard;  //保证金账号
	
}
