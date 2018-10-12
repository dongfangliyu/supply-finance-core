package cn.fintecher.supply.finance.loan.manager.common.confirming.request;

import cn.fintecher.supply.finance.loan.manager.common.util.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wuxiaoxing
 * @time 2018/9/4 11:00
 */
@Data
public class ConfirmingStockInfoResquest extends PageInfo implements Serializable {

    @ApiModelProperty(value = "产品编号")
    private String productNO;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "业务编号")
    private String bussineNo;

    @ApiModelProperty(value = "核心企业名称")
    private String companyName;

    @ApiModelProperty(value = "经销商名称")
    private String companyDealerName;

    @ApiModelProperty(value = "合同类型")
    private String contractType;

    @ApiModelProperty(value = "经销商确认状态")
    private String confirmDealerState;

    @ApiModelProperty(value = "核心企业确认状态")
    private String confirmCorecompanyState;

    @ApiModelProperty(value = "商务合同编号")
    private  String contractNo;

    @ApiModelProperty(value = "融资状态")
    private  String confirmApplyState;

    @ApiModelProperty(value = "经销商id",hidden = true)
    private Long companyDealerId;

    @ApiModelProperty(value = "核心企业id",hidden = true)
    private Long companyId;

    @ApiModelProperty(value = "核心企业用户名称",hidden = true)
    private String companyUserName;

    @ApiModelProperty(value = "经销商用户名称",hidden = true)
    private String companyDealerUserName;

}
