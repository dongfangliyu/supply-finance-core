package cn.fintecher.supply.finance.loan.manager.common.warehouse.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author WuTianJuan
 * @Date Created in 10:30 2018/8/23
 */
public class WarehouseUnpackResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *供货商名称
     */
    @ApiModelProperty(value="供货商名称")
    private String supplierName;

    /**
     *品牌
     */
    @ApiModelProperty(value="品牌")
    private String productBrand;

    /**
     *品名
     */
    @ApiModelProperty(value="品名")
    private String productType;

    /**
     *型号
     */
    @ApiModelProperty(value="型号")
    private String productModel;

    /**
     *数量
     */
    @ApiModelProperty(value="数量")
    private BigDecimal productNumber;

    /**
     *单位
     */
    @ApiModelProperty(value="数量单位")
    private String productUnit;

    /**
     *单价
     */
    @ApiModelProperty(value="单价")
    private BigDecimal unitPrice;

    /**
     *单价单位
     */
    @ApiModelProperty(value="单价单位")
    private String unitPriceUnit;

    /**
     *审批质押率
     */
    @ApiModelProperty(value="审批质押率")
    private String approvalPledgeRate;

    /**
     * 审批期限
     */
    @ApiModelProperty(value="审批期限")
    private String approvalPriod;

    /**
     * 审批金额
     */
    @ApiModelProperty(value = "审批金额")
    private String approvalAmout;

    /**
     * 放款周期
     */
    @ApiModelProperty(value = "放款周期")
    private String loanPriod;

    /**
     * 放款时间
     */
    @ApiModelProperty(value = "放款时间")
    private String loanTime;
}
