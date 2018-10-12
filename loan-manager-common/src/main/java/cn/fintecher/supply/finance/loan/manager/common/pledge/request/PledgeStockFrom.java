package cn.fintecher.supply.finance.loan.manager.common.pledge.request;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PledgeStockFrom extends PageInfo implements Serializable {


    @ApiModelProperty(value = "客户名称")
    private String companyName;

    @ApiModelProperty(value = "仓单编号")
    private String applyNumber;

    @ApiModelProperty(value = "供货商名称")
    private String supplieName;

    @ApiModelProperty(value = "签约开始时间")
    private String signingStartTime;

    @ApiModelProperty(value = "签约结束时间")
    private String signingEndTime;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "拥有者",hidden = true)
    private String ownerId;
}