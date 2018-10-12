package cn.fintecher.supply.finance.loan.manager.core.overdue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.response.OverdueOrderResponse;
import cn.fintecher.supply.finance.loan.manager.core.overdue.dao.OverdueOrderDao;
import cn.fintecher.supply.finance.loan.manager.core.overdue.service.OverdueOrderService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 17:36:53
 */
@Service("overdueOrderService")
public class OverdueOrderServiceImpl implements OverdueOrderService {

   	@Autowired
	private OverdueOrderDao overdueorderDao;
	
	@Override
	public Integer insertOrder(OverdueOrderEntity overdueOrderEntity){
		return overdueorderDao.insertOrder(overdueOrderEntity);
	}
	
	@Override
	public List<OverdueOrderEntity> selectByOrder(OverdueOrderEntity overdueOrderEntity) {
		return overdueorderDao.selectByOrder(overdueOrderEntity);
	}
	
	@Override
	public Integer updateOrder(OverdueOrderEntity overdueOrderEntity) {
		// TODO Auto-generated method stub
		return overdueorderDao.updateOrder(overdueOrderEntity);
	}
	
	@Override
	public OverdueOrderEntity queryOrderByPid(String pid) {
		// TODO Auto-generated method stub
		return overdueorderDao.queryOrderByPid(pid);
	}

	@Override
	public List<OverdueOrderResponse> queryListByOrderForm(OverdueOrderForm overdueOrderForm) {
		// TODO Auto-generated method stub
		return overdueorderDao.queryListByOrderForm(overdueOrderForm);
	}

	@Override
	public Integer queryCountByOrderForm(OverdueOrderForm overdueOrderForm) {
		// TODO Auto-generated method stub
		return overdueorderDao.queryCountByOrderForm(overdueOrderForm);
	}

	@Override
	public List<OverdueOrderResponse> queryDownListByOrderForm(OverdueOrderForm overdueOrderForm) {
		// TODO Auto-generated method stub
		return overdueorderDao.queryDownListByOrderForm(overdueOrderForm);
	}

}
