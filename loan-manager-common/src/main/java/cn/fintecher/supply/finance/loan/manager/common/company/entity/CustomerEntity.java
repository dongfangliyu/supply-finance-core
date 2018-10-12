package cn.fintecher.supply.finance.loan.manager.common.company.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户id")
    public String id;

    @ApiModelProperty(value = "企业名称")
    public String name;

    @ApiModelProperty(value = "账号")
    public String userName;

    @ApiModelProperty(value = "注册人姓名")
    public String linkName;

    @ApiModelProperty(value = "注册时间")
    public String time;

    @ApiModelProperty(value = "添加黑名单时间")
    public String blacklistTime;
}
