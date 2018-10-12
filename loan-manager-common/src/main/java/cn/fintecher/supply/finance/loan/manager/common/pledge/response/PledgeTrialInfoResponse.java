package cn.fintecher.supply.finance.loan.manager.common.pledge.response;

import java.io.Serializable;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyOperationEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PledgeTrialInfoResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("审批历史")
	private List<AuditTaskHistoryEntity> resultlist;
	
	@ApiModelProperty("审批结果")
	private AuditTaskHistoryEntity taskResult;
	
	@ApiModelProperty("仓单信息")
	private CommodityStockInfoEntity commodityStockInfo;
	
	@ApiModelProperty("申请详情信息")
	private PledgeApplyStockInfoEntity applyStockInfo;
	
	@ApiModelProperty("申请信息")
	private PledgeStockInfoEntity stockInfo;
	
	@ApiModelProperty("权属证明")
	private List<BusinessFileEntity> files;
	
	@ApiModelProperty("企业信息")
	private CompanyEnterpriseEntity companyEnterprise;
	
	@ApiModelProperty("企业信息详情")
	private CompanyEnterpriseInfoEntity companyEnterpriseInfo;
	
	@ApiModelProperty("企业联系人列表")
	private List<CompanyOperationEntity> operationList;
	
	@ApiModelProperty("企业银行卡信息")
	private BaseBankInfoEntity baseBankInfo;
	
	@ApiModelProperty("企业文件列表")
	private List<CompanyFileEntity> fileEntities;
	
}
