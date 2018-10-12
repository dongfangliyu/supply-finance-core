package cn.fintecher.supply.finance.loan.manager.core.business.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.core.business.dao.BusinessOrderDao;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessOrderFrom;
import cn.fintecher.supply.finance.loan.manager.core.business.service.BusinessOrderService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 14:59:16
 */
@Service("businessOrderService")
public class BusinessOrderServiceImpl implements BusinessOrderService {

   	@Autowired
	private BusinessOrderDao businessorderDao;
	
	@Override
	public Integer insertOrder(BusinessOrderEntity businessOrderEntity){
		return businessorderDao.insertOrder(businessOrderEntity);
	}
	
	@Override
	public List<BusinessOrderEntity> selectByOrder(BusinessOrderEntity businessOrderEntity) {
		return businessorderDao.selectByOrder(businessOrderEntity);
	}
	
	@Override
	public Integer updateOrder(BusinessOrderEntity businessOrderEntity) {
		// TODO Auto-generated method stub
		return businessorderDao.updateOrder(businessOrderEntity);
	}
	
	@Override
	public BusinessOrderEntity queryOrderByPid(String pid) {
		// TODO Auto-generated method stub
		return businessorderDao.queryOrderByPid(pid);
	}

	@Override
	public Integer queryPageCount(BusinessOrderFrom businessOrderFrom) {
		// TODO Auto-generated method stub
		return businessorderDao.queryPageCount(businessOrderFrom);
	}

	@Override
	public List<BusinessOrderEntity> queryPageList(BusinessOrderFrom businessOrderFrom) {
		// TODO Auto-generated method stub
		return businessorderDao.queryPageList(businessOrderFrom);
	}

	@Override
	public List<BusinessOrderEntity> queryFinancingPageList(BusinessFinancingRequest businessFinancingRequest) {
		return businessorderDao.queryFinancingPageList(businessFinancingRequest);
	}

	@Override
	public Integer queryFinancingPageCount(BusinessFinancingRequest businessFinancingRequest) {
		return businessorderDao.queryFinancingPageCount(businessFinancingRequest);
	}
}
