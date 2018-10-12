package cn.fintecher.supply.finance.loan.manager.common.confirming.response;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/5 15:38
 */
@Data
public class ConfirmingStockInfoDetailResponse implements Serializable {

    @ApiModelProperty(value = "融资基本信息")
    private ConfirmingStockInfoEntity confirmingStockInfoEntity;

    @ApiModelProperty(value = "核心企业信息")
    private CompanyEnterpriseEntity companyEnterpriseEntity;

    @ApiModelProperty(value = "经销商信息")
    private CompanyEnterpriseEntity dealerEntity;

    @ApiModelProperty(value = "产品信息")
    private ProProductEntity proProductEntity;

    @ApiModelProperty(value = "采购商品明细")
    private BusinessFileEntity purchaseDetails;

    @ApiModelProperty(value = "经销商合同文件")
    private List<BusinessFileEntity> loanContactFile;

    @ApiModelProperty(value = "核心企业合同文件")
    private List<BusinessFileEntity> coreContactFile;

}