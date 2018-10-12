package cn.fintecher.supply.finance.loan.manager.common.warehouse.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author WuTianJuan
 * @Date Created in 18:07 2018/8/27
 */
@Data
public class WarehouseUnpackListResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="审批单价")
    private String contractApplyPrice;

    @ApiModelProperty(value="审批质押率")
    private BigDecimal  contractApplyPledgeRate;

    @ApiModelProperty(value="审批期限")
    private int contractApplyTerm;

    @ApiModelProperty(value="审批金额")
    private BigDecimal contractApplyAmount;

    @ApiModelProperty(value="状态")
    private String releasePledge;

    @ApiModelProperty(value="记录id")
    private Long pid;

    @ApiModelProperty(value="仓单编号")
    private String applyNumber;

    @ApiModelProperty(value="供货商名称")
    private String supplierName;

    @ApiModelProperty(value="品牌")
    private String productBrand;

    @ApiModelProperty(value="品名")
    private String productType;

    @ApiModelProperty(value="型号")
    private String productModel;

    @ApiModelProperty(value="数量")
    private BigDecimal productNumber;

    @ApiModelProperty(value="数量单位")
    private String productUnit;

    @ApiModelProperty(value="单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value="单价单位")
    private String unitPriceUnit;

    @ApiModelProperty(value="总价值")
    private BigDecimal totalPrice;

    @ApiModelProperty(value="申请解押时间")
    private String releaseApplyTime;

    @ApiModelProperty(value="解押时间")
    private String releaseTime;

    @ApiModelProperty(value="放款周期开始时间")
    private String loanStartTime;

    @ApiModelProperty(value="放款周期结束时间")
    private String loanEndTime;

    @ApiModelProperty(value="放款时间")
    private String loanTime;

    @ApiModelProperty(value="还款日期")
    private String repaymentTime;

    @ApiModelProperty(value="单位")
    private String value;

    @ApiModelProperty(value="客户名称")
    private String companyName;
}
