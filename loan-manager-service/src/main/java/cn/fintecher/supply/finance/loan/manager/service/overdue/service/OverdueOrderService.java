package cn.fintecher.supply.finance.loan.manager.service.overdue.service;

import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRecordForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRepaymentForm;
import cn.fintecher.common.utils.basecommon.message.Message;
import java.util.List;

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
	Message insertOrder(OverdueOrderEntity overdueOrderEntity);
	
	/**
	 * 查询逾期订单
	 * @param quotaApply
	 * @return
	 */
	Message<List<OverdueOrderEntity>> selectByOrder(OverdueOrderEntity overdueOrderEntity);
	
	/**
	 * 修改逾期订单
	 * @param quotaApply
	 */
	Message updateOrder(OverdueOrderEntity overdueOrderEntity);
	
	/**
	 * 根据主键查询逾期订单
	 * @param quotaApply
	 */
	Message<OverdueOrderEntity> queryOrderByPid(String pid);
 
	
    Message searchOrderList(OverdueOrderForm overdueOrderForm);
	
    Message searchOrderDetail(Long pid);
	
    Message submitRepaymentRecord(OverdueOrderRepaymentForm overdueOrderRepaymentForm);
	
    Message searchRepaymenList(OverdueOrderRecordForm overdueOrderRecordForm);
	
    Message downLoadOrder(OverdueOrderForm overdueOrderForm);
   


   

}