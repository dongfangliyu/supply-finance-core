package cn.fintecher.supply.finance.loan.manager.common.company.request;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BlackListFrom extends PageInfo implements Serializable {

    @ApiModelProperty(value="客户名称")
    public String companyName;

    @ApiModelProperty(value = "账号")
    public String userName;

    @ApiModelProperty(value = "注册开始时间")
    public String startTime;

    @ApiModelProperty(value = "注册结束时间")
    public String endTime;

    @ApiModelProperty(value = "添加黑名单开始时间")
    public String blackStartTime;

    @ApiModelProperty(value = "添加黑名单结束时间")
    private String blackEndTime;
}
