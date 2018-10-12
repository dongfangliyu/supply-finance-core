package cn.fintecher.supply.finance.loan.manager.core.margin.service.impl;

import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.MarginForm;
import cn.fintecher.supply.finance.loan.manager.core.margin.dao.MarginDao;
import cn.fintecher.supply.finance.loan.manager.core.margin.service.MarginService;

/**
 * @author lss
 * @date 2018/8/28
 */
@Service
public class MarginServiceImpl implements MarginService{

	@Autowired
    private MarginDao marginDao;
	
	@Override
	public int searchMarginListCount(MarginForm marginForm) {
		return marginDao.searchMarginListCount(marginForm);
	}

	@Override
	public List<ConfirmingStockInfoResponse> searchProductList(MarginForm marginForm) {
		return marginDao.searchMarginList(marginForm);
	}

	@Override
	public void saveAuditTaskEntryEntity(AuditTaskEntryEntity auditTaskEntryEntity) {
		marginDao.saveAuditTaskEntryEntity(auditTaskEntryEntity);
	}



}
