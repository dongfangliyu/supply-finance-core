package cn.fintecher.supply.finance.loan.manager.common.pledge.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 签约返回列表response
 */
@Data
public class PledgeSigningResponse extends PledgeStockInfoResponse implements Serializable {

    @ApiModelProperty(value="审批单价")
    private String contractApplyPrice;

    @ApiModelProperty(value="审批质押率")
    private BigDecimal  contractApplyPledgeRate;

    @ApiModelProperty(value="审批期限")
    private int contractApplyTerm;

    @ApiModelProperty(value="审批金额")
    private BigDecimal contractApplyAmount;

    @ApiModelProperty(value="状态")
    private String state;

    @ApiModelProperty(value="客户签约时间/平台签约时间")
    private String contractUserTime;
}
