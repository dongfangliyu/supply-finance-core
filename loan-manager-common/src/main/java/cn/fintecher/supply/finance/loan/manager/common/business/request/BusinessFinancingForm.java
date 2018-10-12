package cn.fintecher.supply.finance.loan.manager.common.business.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wuxiaoxing
 * @time 2018/7/17 17:49
 */
@Data
public class BusinessFinancingForm implements Serializable {

    @ApiModelProperty(value="申请金额")
    private String applicationAmount;

    @ApiModelProperty(value="申请期限")
    private Integer applicationTerm;

    @ApiModelProperty(value="利息")
    private String interest;

    @ApiModelProperty(value="订单id")
    private String orderId;

}
