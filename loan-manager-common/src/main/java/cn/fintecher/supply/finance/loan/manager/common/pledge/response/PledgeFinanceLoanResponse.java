package cn.fintecher.supply.finance.loan.manager.common.pledge.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author gonghebin
 * @date 2018/8/23 0023下午 3:25
 */
@Data
public class PledgeFinanceLoanResponse implements Serializable{

    /**
     *记录id
     */
    @ApiModelProperty(value="记录id")
    private Long pid;

    /**
     *客户名称
     */
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
     *审批单价
     */
    @ApiModelProperty(value="审批单价")
    private BigDecimal contractApplyPrice;

    /**
     *审批质押率
     */
    @ApiModelProperty(value="审批质押率")
    private BigDecimal contractApplyPledgeRate;

    /**
     *审批期限
     */
    @ApiModelProperty(value="审批期限")
    private Long contractApplyTerm;

    /**
     *审批金额
     */
    @ApiModelProperty(value="审批金额")
    private BigDecimal contractApplyAmount;

    /**
     *初审状态
     */
    @ApiModelProperty(value="初审状态")
    private String financeFirstState;

    /**
     *复核状态
     */
    @ApiModelProperty(value="复核状态")
    private String financeRehearState;

    /**
     *初核时间
     */
    @ApiModelProperty(value="初核时间")
    private String financeFirstTime;

    /**
     *复核时间
     */
    @ApiModelProperty(value="复核时间")
    private String financeRehearTime;

    /**
     *放款时间
     */
    @ApiModelProperty(value="放款时间")
    private String loanTime;

    /**
     *放款周期开始时间
     */
    @ApiModelProperty(value="放款周期开始时间")
    private String loanStartTime;

    /**
     *放款周期结束时间
     */
    @ApiModelProperty(value="放款周期结束时间")
    private String loanEndTime;

}
