package cn.fintecher.supply.finance.loan.manager.core.overdue.dao;

import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.response.OverdueOrderResponse;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 逾期订单
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 17:36:53
 */
@Mapper
public interface OverdueOrderDao{
	
	Integer insertOrder(OverdueOrderEntity overdueOrderEntity);
	
	List<OverdueOrderEntity> selectByOrder(OverdueOrderEntity overdueOrderEntity);
	
	Integer updateOrder(OverdueOrderEntity overdueOrderEntity);
	
	OverdueOrderEntity queryOrderByPid(String pid);

	List<OverdueOrderResponse> queryListByOrderForm(OverdueOrderForm overdueOrderForm);

	Integer queryCountByOrderForm(OverdueOrderForm overdueOrderForm);

	List<OverdueOrderResponse> queryDownListByOrderForm(OverdueOrderForm overdueOrderForm);
}
