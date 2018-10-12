package cn.fintecher.supply.finance.loan.manager.pm.bff.business.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableFrom;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableSubmit;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
public interface BusinessReceivableService {    
	
    public Message searchListReceivable(BusinessReceivableFrom businessReceivableFrom);
	
    public Message submitReceivable(BusinessReceivableSubmit businessReceivableSubmit);
	
    public Message inviteSupplier(Long receivableId,String userName);
	
    public Message selectReceivableDetail(Long receivableId,String userName);
	
    public Message submitUpdateReceivable(BusinessReceivableSubmit businessReceivableSubmit);

	public Message deleteReceivable(String id, String userName);
   

}
