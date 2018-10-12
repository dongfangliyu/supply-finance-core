package cn.fintecher.supply.finance.loan.manager.pm.bff.business.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessOrderFrom;
import cn.fintecher.supply.finance.loan.manager.pm.bff.business.core.BusinessOrderCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.business.service.BusinessOrderService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 14:59:16
 */
@Service("businessOrderService")
public class BusinessOrderServiceImpl implements BusinessOrderService {

	@Autowired
    private BusinessOrderCore businessOrderCore;
	
    public Message searchListOrder(BusinessOrderFrom businessOrderFrom){
    	return businessOrderCore.searchListOrder(businessOrderFrom);
    };
	
    public Message selectOrderDetail(Long orderId,String userName){
    	return businessOrderCore.selectOrderDetail(orderId,userName);
    };
	
    public Message submitConfirm(Long orderId,String userName){
    	return businessOrderCore.submitConfirm(orderId,userName);
    };

}
