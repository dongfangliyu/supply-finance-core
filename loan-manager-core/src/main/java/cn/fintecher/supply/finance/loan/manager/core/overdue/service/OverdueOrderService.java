package cn.fintecher.supply.finance.loan.manager.core.overdue.service;

import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.response.OverdueOrderResponse;

/**
 * 逾期订单
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 17:36:53
 */
public interface OverdueOrderService{

    /**
	 * 新增逾期订单
	 * @param 
	 * @return
	 */
	Integer insertOrder(OverdueOrderEntity overdueOrderEntity);
	
	/**
	 * 查询逾期订单
	 * @param 
	 * @return
	 */
	List<OverdueOrderEntity> selectByOrder(OverdueOrderEntity overdueOrderEntity);
	
	/**
	 * 修改逾期订单
	 * @param 
	 */
	Integer updateOrder(OverdueOrderEntity overdueOrderEntity);
	
	/**
	 * 根据主键查询逾期订单
	 * @param 
	 * @return
	 */
	OverdueOrderEntity queryOrderByPid(String pid);

	List<OverdueOrderResponse> queryListByOrderForm(OverdueOrderForm overdueOrderForm);

	Integer queryCountByOrderForm(OverdueOrderForm overdueOrderForm);

	List<OverdueOrderResponse> queryDownListByOrderForm(OverdueOrderForm overdueOrderForm);
}
