package cn.fintecher.supply.finance.loan.manager.common.confirming.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lss
 * @date 2018-8-28
 */
@Data
public class ConfirmingStockInfoResponse implements Serializable {
	
	/**
	*记录id
	*/
    private Long pid;
    /**
     *经销商id
     */
    private Long companyDealerId;
    
    /**
	*业务编号(YW+当日日期+243+流水号)
	*/
    private String bussineNo;
    /**
   	*敞口金额 （待还总额）
   	*/
    private Long confirmNotAmount;
    /**
     *产品名称
     */
    private String name;
    /**
     *产品编号
     */
    private String productNo;
    /**
     *保证金卡号
     */
    private String bankCard;
    /**
     *累计保证金金额
     */
    private int repaymentPrice;

}
