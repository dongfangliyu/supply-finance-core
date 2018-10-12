package cn.fintecher.supply.finance.loan.manager.core.audit.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchMarginDetailForm;
import cn.fintecher.supply.finance.loan.manager.common.response.MarginDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.core.audit.dao.AuditTaskEntryDao;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditTaskEntryService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-21 17:17:15
 */
@Service("auditTaskEntryService")
public class AuditTaskEntryServiceImpl implements AuditTaskEntryService {

   	@Autowired
	private AuditTaskEntryDao audittaskEntryDao;

	@Override
	public Integer insertTaskEntry(AuditTaskEntryEntity auditTaskEntryEntity){
		return audittaskEntryDao.insertTaskEntry(auditTaskEntryEntity);
	}

	@Override
	public List<AuditTaskEntryEntity> selectByTaskEntry(AuditTaskEntryEntity auditTaskEntryEntity) {
		return audittaskEntryDao.selectByTaskEntry(auditTaskEntryEntity);
	}

	@Override
	public Integer updateTaskEntry(AuditTaskEntryEntity auditTaskEntryEntity) {
		// TODO Auto-generated method stub
		return audittaskEntryDao.updateTaskEntry(auditTaskEntryEntity);
	}

	@Override
	public AuditTaskEntryEntity queryTaskEntryByPid(String pid) {
		// TODO Auto-generated method stub
		return audittaskEntryDao.queryTaskEntryByPid(pid);
	}

	@Override
	public Integer queryPaymentCountByOrderInfoId(String orderInfoId) {
		// TODO Auto-generated method stub
		return audittaskEntryDao.queryPaymentCountByOrderInfoId(orderInfoId);
	}

	@Override
	public int searchTaskEntryListCount(AuditEntryForm auditEntryForm) {
		return audittaskEntryDao.searchTaskEntryListCount(auditEntryForm);
	}

	@Override
	public List<AuditTaskEntryEntity> searchTaskEntryList(AuditEntryForm auditEntryForm) {
		return audittaskEntryDao.searchTaskEntryList(auditEntryForm);
	}

	@Override
	public Double searchSumOfRepaymentPrice(Long pid) {
		return audittaskEntryDao.searchSumOfRepaymentPrice(pid);
	}

	@Override
	public List<AuditTaskEntryEntity> searchTaskEntityByOrderId(Long orderInfoId) {
		return audittaskEntryDao.searchTaskEntityByOrderId(orderInfoId);
	}

	@Override
	public Double searchSumOfRepaymentPriceByState(Long orderId) {
		return audittaskEntryDao.searchSumOfRepaymentPriceByState(orderId);
	}
	
	@Override
	public List<MarginDetailResponse> searchMarginDetail(SearchMarginDetailForm searchMarginDetailForm) {
		return audittaskEntryDao.searchMarginDetail(searchMarginDetailForm);
	}

}
