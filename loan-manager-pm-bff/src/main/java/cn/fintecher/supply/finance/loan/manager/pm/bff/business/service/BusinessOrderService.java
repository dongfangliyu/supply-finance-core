package cn.fintecher.supply.finance.loan.manager.pm.bff.business.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessOrderFrom;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 14:59:16
 */
public interface BusinessOrderService {    
	
    public Message searchListOrder(BusinessOrderFrom businessOrderFrom);
	
    public Message selectOrderDetail(Long orderId,String userName);
	
    public Message submitConfirm(Long orderId,String userName);
   

}
