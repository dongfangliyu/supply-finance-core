package cn.fintecher.supply.finance.loan.manager.common.pledge.response;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.FinanceStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghebin
 * @date 2018/8/23 0023下午 5:17
 */
@Data
public class PledgeFinanceResponse implements Serializable{

    /**
     *入库信息表
     */
    @ApiModelProperty(value="入库信息表")
    private CommodityStockInfoEntity commodityStockInfoEntity;

    /**
     *仓单质押申请信息表
     */
    @ApiModelProperty(value="仓单质押申请信息表")
    private PledgeStockInfoEntity pledgeStockInfoEntity;

    /**
     *银行
     */
    @ApiModelProperty(value="银行")
    private BaseBankInfoEntity baseBankInfoEntity;

    /**
     *仓单放款申请信息表
     */
    @ApiModelProperty(value="仓单放款申请信息表")
    private FinanceStockInfoEntity financeStockInfoEntity;

    /**
     *文件
     */
    @ApiModelProperty(value="文件")
    private BusinessFileEntity businessFileEntity;

    /**
     * 企业
     */
    @ApiModelProperty(value="企业")
    private CompanyEnterpriseEntity companyEnterpriseEntity;

    /**
     * 产品
     */
    @ApiModelProperty(value="产品")
    private ProProductEntity proProductEntity;


}
