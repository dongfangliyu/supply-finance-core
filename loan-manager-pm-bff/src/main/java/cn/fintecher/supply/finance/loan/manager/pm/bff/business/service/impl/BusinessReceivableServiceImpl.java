package cn.fintecher.supply.finance.loan.manager.pm.bff.business.service.impl;

import org.springframework.stereotype.Service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableFrom;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableSubmit;
import cn.fintecher.supply.finance.loan.manager.pm.bff.business.core.BusinessReceivableCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.business.service.BusinessReceivableService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
@Service("businessReceivableService")
public class BusinessReceivableServiceImpl implements BusinessReceivableService {

	@Autowired
    private BusinessReceivableCore businessReceivableCore;
	
    public Message searchListReceivable(BusinessReceivableFrom businessReceivableFrom){
    	return businessReceivableCore.searchListReceivable(businessReceivableFrom);
    };
	
    public Message submitReceivable(BusinessReceivableSubmit businessReceivableSubmit){
    	return businessReceivableCore.submitReceivable(businessReceivableSubmit);
    };
	
    public Message inviteSupplier(Long receivableId,String userName){
    	return businessReceivableCore.inviteSupplier(receivableId,userName);
    };
	
    public Message selectReceivableDetail(Long receivableId,String userName){
    	return businessReceivableCore.selectReceivableDetail(receivableId,userName);
    };
	
    public Message submitUpdateReceivable(BusinessReceivableSubmit businessReceivableSubmit){
    	return businessReceivableCore.submitUpdateReceivable(businessReceivableSubmit);
    }

	@Override
	public Message deleteReceivable(String id, String userName) {
		// TODO Auto-generated method stub
		return businessReceivableCore.deleteReceivable(id,userName);
	};

}
