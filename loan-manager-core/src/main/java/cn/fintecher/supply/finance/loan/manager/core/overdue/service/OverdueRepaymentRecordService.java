package cn.fintecher.supply.finance.loan.manager.core.overdue.service;

import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueRepaymentRecordEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRecordForm;

/**
 * 逾期还款记录表
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 13:24:15
 */
public interface OverdueRepaymentRecordService{

    /**
	 * 新增逾期还款记录表
	 * @param 
	 * @return
	 */
	Integer insertRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity);
	
	/**
	 * 查询逾期还款记录表
	 * @param 
	 * @return
	 */
	List<OverdueRepaymentRecordEntity> selectByRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity);
	
	/**
	 * 修改逾期还款记录表
	 * @param 
	 */
	Integer updateRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity);
	
	/**
	 * 根据主键查询逾期还款记录表
	 * @param 
	 * @return
	 */
	OverdueRepaymentRecordEntity queryRepaymentRecordByPid(String pid);

	Integer queryCountByRecordForm(OverdueOrderRecordForm overdueOrderForm);

	List<OverdueRepaymentRecordEntity> queryListByRecordForm(OverdueOrderRecordForm overdueOrderForm);
}
