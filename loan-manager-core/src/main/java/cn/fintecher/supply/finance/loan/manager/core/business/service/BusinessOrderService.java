package cn.fintecher.supply.finance.loan.manager.core.business.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingRequest;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessOrderFrom;

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
	Integer insertOrder(BusinessOrderEntity businessOrderEntity);
	
	/**
	 * 查询订单
	 * @param 
	 * @return
	 */
	List<BusinessOrderEntity> selectByOrder(BusinessOrderEntity businessOrderEntity);
	
	/**
	 * 修改订单
	 * @param 
	 */
	Integer updateOrder(BusinessOrderEntity businessOrderEntity);
	
	/**
	 * 根据主键查询订单
	 * @param 
	 * @return
	 */
	BusinessOrderEntity queryOrderByPid(String pid);

	Integer queryPageCount(BusinessOrderFrom businessOrderFrom);

	List<BusinessOrderEntity> queryPageList(BusinessOrderFrom businessOrderFrom);

	List<BusinessOrderEntity> queryFinancingPageList(BusinessFinancingRequest businessFinancingRequest);

	Integer queryFinancingPageCount(BusinessFinancingRequest businessFinancingRequest);

}
