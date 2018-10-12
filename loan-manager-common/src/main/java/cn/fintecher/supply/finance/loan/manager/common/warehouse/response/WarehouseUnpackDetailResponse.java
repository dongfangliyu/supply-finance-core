package cn.fintecher.supply.finance.loan.manager.common.warehouse.response;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 17:51 2018/8/23
 */
@Data
public class WarehouseUnpackDetailResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="记录id")
    private Long pid;

    @ApiModelProperty(value="申请编号")
    private String applyNumber;

    @ApiModelProperty(value="供货商名称")
    private String supplierName;

    @ApiModelProperty(value="品牌")
    private String productBrand;

    @ApiModelProperty(value="品名")
    private String productType;

    @ApiModelProperty(value="数量")
    private BigDecimal productNumber;

    @ApiModelProperty(value="单位")
    private String productUnit;

    @ApiModelProperty(value="单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value="单价单位")
    private String unitPriceUnit;

    @ApiModelProperty(value="总价值")
    private BigDecimal totalPrice;

    @ApiModelProperty(value="产地省份")
    private String productAddressProvince;

    @ApiModelProperty(value="产地市")
    private String productAddressCity;

    @ApiModelProperty(value="型号")
    private String productModel;

    @ApiModelProperty(value="等级")
    private String productGrade;

    @ApiModelProperty(value="质量标准")
    private String productStandard;

    @ApiModelProperty(value="大小")
    private String productSize;

    @ApiModelProperty(value="有效期开始时间")
    private  String effectiveStartTime;

    @ApiModelProperty(value="有效期结束时间")
    private  String effectiveEndTime;

    @ApiModelProperty(value="生产日期")
    private String productionDate;

    @ApiModelProperty(value="颜色")
    private String productColour;

    @ApiModelProperty(value="服务费率")
    private String serviceFeeRate;

    @ApiModelProperty(value="利率")
    private String interestRate;

    @ApiModelProperty(value="利息")
    private BigDecimal interest;

    @ApiModelProperty(value="服务费")
    private BigDecimal serviceFee;

    @ApiModelProperty(value="申请解押时间")
    private String releaseApplyTime;

    @ApiModelProperty(value="解押时间")
    private String releaseTime;

    @ApiModelProperty(value="放款金额")
    private BigDecimal loanMoney;

    @ApiModelProperty(value="还款金额")
    private BigDecimal repaymentMoney;

    @ApiModelProperty(value="客户名称")
    private String customerName;

    @ApiModelProperty(value="放款开始时间")
    private String loanStartTime;

    @ApiModelProperty(value="放款结束时间")
    private String loanEndTime;

    @ApiModelProperty(value="放款时间")
    private String loanTime;

    @ApiModelProperty(value="实际还款日期")
    private String releaseRepaymentTime;

    @ApiModelProperty(value="还款日期")
    private String repaymentTime;

    @ApiModelProperty(value="实际还款金额")
    private double approvalMoney;

    @ApiModelProperty(value="期限")
    private Long period;

    @ApiModelProperty(value="文件")
    private List<BusinessFileEntity> businessFileEntities;
}
