package cn.fintecher.supply.finance.loan.manager.common.pledge.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PledgeStockInfoResponse implements Serializable {


    /**
     *记录id
     */
    @ApiModelProperty(value="记录id")
    private Long pid;

    @ApiModelProperty(value="质押id")
    private Long commId;

    @ApiModelProperty(value="客户名称")
    private String companyName;
    /**
     *申请编号
     */
    @ApiModelProperty(value="仓单编号")
    private String applyNumber;

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
     *总价值
     */
    @ApiModelProperty(value="总价值")
    private BigDecimal totalPrice;

}
