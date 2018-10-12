package cn.fintecher.supply.finance.loan.manager.pm.bff.business.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingForm;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingRequest;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessVoucherForm;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;

import cn.fintecher.supply.finance.loan.manager.pm.bff.business.core.BusinessFinancingCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.business.service.BusinessFinancingService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 16:59:17
 */
@Service("businessFinancingService")
public class BusinessFinancingServiceImpl implements BusinessFinancingService {

	@Autowired
    private BusinessFinancingCore businessFinancingCore;
	
    public Message searchListOrder(BusinessFinancingRequest businessFinancingRequest){
    	return businessFinancingCore.searchListOrder(businessFinancingRequest);
    }
	
    public Message selectOrderEnterDetail(Long id){
    	return businessFinancingCore.selectOrderEnterDetail(id);
    };
	
    public Message submitOrder(BusinessVoucherForm businessVoucherForm){
    	return businessFinancingCore.submitOrder(businessVoucherForm);
    }
	
    public Message selectOrderSuppDetail(Long id){
    	return businessFinancingCore.selectOrderSuppDetail(id);
    }
	
    public Message selectOrderDetail(Long id){
    	return businessFinancingCore.selectOrderDetail(id);
    }

    @Override
    public Message submitApply(BusinessFinancingForm businessFinancingForm) {
        return businessFinancingCore.submitApply(businessFinancingForm);
    }
}
