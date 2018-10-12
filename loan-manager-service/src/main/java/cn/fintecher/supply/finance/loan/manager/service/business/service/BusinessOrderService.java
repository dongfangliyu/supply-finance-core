package cn.fintecher.supply.finance.loan.manager.service.business.service;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessOrderFrom;
import cn.fintecher.common.utils.basecommon.message.Message;
import java.util.List;

/**
 * 订单
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 14:59:16
 */
public interface BusinessOrderService{

    /**
	 * 新增订单
	 * @param 
	 * @return
	 */
	Message insertOrder(BusinessOrderEntity businessOrderEntity);
	
	/**
	 * 查询订单
	 * @param quotaApply
	 * @return
	 */
	Message<List<BusinessOrderEntity>> selectByOrder(BusinessOrderEntity businessOrderEntity);
	
	/**
	 * 修改订单
	 * @param quotaApply
	 */
	Message updateOrder(BusinessOrderEntity businessOrderEntity);
	
	/**
	 * 根据主键查询订单
	 * @param quotaApply
	 */
	Message<BusinessOrderEntity> queryOrderByPid(String pid);
 
	
    Message searchListOrder(BusinessOrderFrom businessOrderFrom);
	
    Message selectOrderDetail(Long orderId, String userName);
	
    Message submitConfirm(Long orderId, String userName);
   


   

}