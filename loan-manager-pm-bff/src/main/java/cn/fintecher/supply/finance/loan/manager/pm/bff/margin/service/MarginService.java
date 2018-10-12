package cn.fintecher.supply.finance.loan.manager.pm.bff.margin.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.MarginForm;
import cn.fintecher.supply.finance.loan.manager.common.form.PayMarginForm;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchMarginDetailForm;

/**
 * @author lss
 * @date 2018/8/28
 */
public interface MarginService {

	Message searchListMargin(MarginForm marginForm);

	Message payMargin(PayMarginForm payMarginForm);

	Message searchMarginDetail(SearchMarginDetailForm searchMarginDetailForm);

}
