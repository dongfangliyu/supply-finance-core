package cn.fintecher.supply.finance.loan.manager.pm.bff.business.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingForm;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingRequest;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessVoucherForm;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 16:59:17
 */
public interface BusinessFinancingService {    
	
    public Message searchListOrder(BusinessFinancingRequest businessFinancingRequest);
	
    public Message selectOrderEnterDetail(Long id);
	
    public Message submitOrder(BusinessVoucherForm businessVoucherForm);
	
    public Message selectOrderSuppDetail(Long id);
	
    public Message selectOrderDetail(Long id);

    Message submitApply(BusinessFinancingForm businessFinancingForm);
}
