package cn.fintecher.supply.finance.loan.manager.common.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WuTianJuan
 * @Date Created in 14:21 2018/6/23
 */
@Data
public class CompanyCreditResultResponse implements Serializable {

    @ApiModelProperty(value = "申请授信时间")
    private Date submitTime;

    @ApiModelProperty(value = "申请授信编号")
    private String creditNumber;

    @ApiModelProperty(value = "授信状态")
    private Long creditStatus;

    @ApiModelProperty(value = "授信金额")
    private Double credit_price;

}
