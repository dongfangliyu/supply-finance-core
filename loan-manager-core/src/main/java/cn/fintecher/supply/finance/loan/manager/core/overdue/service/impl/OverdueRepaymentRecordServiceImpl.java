package cn.fintecher.supply.finance.loan.manager.core.overdue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueRepaymentRecordEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRecordForm;
import cn.fintecher.supply.finance.loan.manager.core.overdue.dao.OverdueRepaymentRecordDao;
import cn.fintecher.supply.finance.loan.manager.core.overdue.service.OverdueRepaymentRecordService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 13:24:15
 */
@Service("overdueRepaymentRecordService")
public class OverdueRepaymentRecordServiceImpl implements OverdueRepaymentRecordService {

   	@Autowired
	private OverdueRepaymentRecordDao overduerepaymentRecordDao;
	
	@Override
	public Integer insertRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity){
		return overduerepaymentRecordDao.insertRepaymentRecord(overdueRepaymentRecordEntity);
	}
	
	@Override
	public List<OverdueRepaymentRecordEntity> selectByRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity) {
		return overduerepaymentRecordDao.selectByRepaymentRecord(overdueRepaymentRecordEntity);
	}
	
	@Override
	public Integer updateRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity) {
		// TODO Auto-generated method stub
		return overduerepaymentRecordDao.updateRepaymentRecord(overdueRepaymentRecordEntity);
	}
	
	@Override
	public OverdueRepaymentRecordEntity queryRepaymentRecordByPid(String pid) {
		// TODO Auto-generated method stub
		return overduerepaymentRecordDao.queryRepaymentRecordByPid(pid);
	}

	@Override
	public Integer queryCountByRecordForm(OverdueOrderRecordForm overdueOrderForm) {
		// TODO Auto-generated method stub
		return overduerepaymentRecordDao.queryCountByRecordForm(overdueOrderForm);
	}

	@Override
	public List<OverdueRepaymentRecordEntity> queryListByRecordForm(OverdueOrderRecordForm overdueOrderForm) {
		// TODO Auto-generated method stub
		return overduerepaymentRecordDao.queryListByRecordForm(overdueOrderForm);
	}

}
