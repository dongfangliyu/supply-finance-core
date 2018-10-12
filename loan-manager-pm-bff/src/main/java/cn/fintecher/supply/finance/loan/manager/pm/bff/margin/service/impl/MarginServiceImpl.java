package cn.fintecher.supply.finance.loan.manager.pm.bff.margin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.MarginForm;
import cn.fintecher.supply.finance.loan.manager.common.form.PayMarginForm;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchMarginDetailForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.margin.feign.FCMarginService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.margin.service.MarginService;

/**
 * @author lss
 * @date 2018/8/28
 */
@Service
public class MarginServiceImpl implements MarginService {
	
	@Autowired
    private FCMarginService fcMarginService;

	@Override
	public Message searchListMargin(MarginForm marginFormm) {
		
		return fcMarginService.searchListMargin(marginFormm);
	}

	@Override
	public Message payMargin(PayMarginForm payMarginForm) {
		return fcMarginService.payMargin(payMarginForm);
	}

	@Override
	public Message searchMarginDetail(SearchMarginDetailForm searchMarginDetailForm) {
		return fcMarginService.searchMarginDetail(searchMarginDetailForm);
	}

}
