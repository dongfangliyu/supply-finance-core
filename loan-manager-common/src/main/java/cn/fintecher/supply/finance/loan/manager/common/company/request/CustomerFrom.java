package cn.fintecher.supply.finance.loan.manager.common.company.request;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 客户管理列表查询条件
 */
@Data
public class CustomerFrom extends PageInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="客户名称")
    public String companyName;

    @ApiModelProperty(value = "账号")
    public String userName;

    @ApiModelProperty(value = "注册开始时间")
    public String startTime;

    @ApiModelProperty(value = "注册结束时间")
    public String endTime;

    @ApiModelProperty(value = "注册人姓名")
    public String linkMan;


}
