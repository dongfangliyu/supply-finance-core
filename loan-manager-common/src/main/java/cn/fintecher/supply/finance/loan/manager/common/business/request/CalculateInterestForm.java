package cn.fintecher.supply.finance.loan.manager.common.business.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * @author wuxiaoxing
 * @time 2018/7/17 17:16
 */
@Data
public class CalculateInterestForm implements Serializable {

    @ApiModelProperty(value="申请金额")
    private Double applicationAmount;

    @ApiModelProperty(value="利率")
    private Integer interestRate;

    @ApiModelProperty(value="申请期限")
    private Integer applicationTerm;

    @ApiModelProperty(value="服务费率")
    private Integer serviceFee;
}
