package cn.fintecher.supply.finance.loan.manager.common.confirming.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wuxiaoxing
 * @time 2018/9/4 15:29
 */
@Data
public class ConfirmingStockInfoForm implements Serializable {

    @ApiModelProperty(value = "记录id")
    private Long pid;

    @ApiModelProperty(value = "业务编号(YW+当日日期+243+流水号)")
    private String bussineNo;

    @ApiModelProperty(value = "产品id （pro_product)产品表")
    private Long proRoductId;

    @ApiModelProperty(value = "核心企业id  来源 (企业信息表)")
    private Long companyId;

    @ApiModelProperty(value = "经销商id")
    private Long companyDealerId;

    @ApiModelProperty(value = "申请金额 ")
    private BigDecimal contractApplyAmount;

    @ApiModelProperty(value = "申请期限")
    private Long contractApplyTerm;

    @ApiModelProperty(value = "商务合同编号")
    private String contractNo;

    @ApiModelProperty(value = "合同类型")
    private String contractType;

    @ApiModelProperty(value = "合同订单金额")
    private BigDecimal contractAmount;

    @ApiModelProperty(value = "经销商用户名称",hidden = true)
    private String companyDealerUserName;

    @ApiModelProperty(value = "文件owner_id")
    private String fileCode;
}
