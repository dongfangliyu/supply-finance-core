package cn.fintecher.supply.finance.loan.manager.service.business.service;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableFrom;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableSubmit;
import cn.fintecher.common.utils.basecommon.message.Message;

/**
 * 应收账款
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
public interface BusinessReceivableService{

    /**
	 * 新增应收账款
	 * @param 
	 * @return
	 */
	Message insertReceivable(BusinessReceivableEntity businessReceivableEntity);
	
	/**
	 * 查询应收账款
	 * @param quotaApply
	 * @return
	 */
	Message selectByReceivable(BusinessReceivableEntity businessReceivableEntity);
	
	/**
	 * 修改应收账款
	 * @param quotaApply
	 */
	Message updateReceivable(BusinessReceivableEntity businessReceivableEntity);
	
	/**
	 * 根据主键查询应收账款
	 * @param quotaApply
	 */
	Message queryReceivableByPid(String pid);
 
	
    Message searchListReceivable(BusinessReceivableFrom businessReceivableFrom);
	
    Message submitReceivable(BusinessReceivableSubmit businessReceivableSubmit);
	
    Message inviteSupplier(Long receivableId,String userName);
	
    Message selectReceivableDetail(Long receivableId,String userName);
	
    Message submitUpdateReceivable(BusinessReceivableSubmit businessReceivableSubmit);

	Message deleteReceivable(String id, String userName);
   


   

}