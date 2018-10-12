package cn.fintecher.supply.finance.loan.manager.core.margin.service;

import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.form.MarginForm;

/**
 * @author lss
 * @date 2018/8/28
 */
public interface MarginService {

	int searchMarginListCount(MarginForm marginForm);

	List<ConfirmingStockInfoResponse> searchProductList(MarginForm marginForm);

	void saveAuditTaskEntryEntity(AuditTaskEntryEntity auditTaskEntryEntity);

}
