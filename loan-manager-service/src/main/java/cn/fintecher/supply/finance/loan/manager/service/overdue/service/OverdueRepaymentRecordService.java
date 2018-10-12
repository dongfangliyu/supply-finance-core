package cn.fintecher.supply.finance.loan.manager.service.overdue.service;

import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueRepaymentRecordEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import java.util.List;

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
	Message insertRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity);
	
	/**
	 * 查询逾期还款记录表
	 * @param quotaApply
	 * @return
	 */
	Message<List<OverdueRepaymentRecordEntity>> selectByRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity);
	
	/**
	 * 修改逾期还款记录表
	 * @param quotaApply
	 */
	Message updateRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity);
	
	/**
	 * 根据主键查询逾期还款记录表
	 * @param quotaApply
	 */
	Message<OverdueRepaymentRecordEntity> queryRepaymentRecordByPid(String pid);
 
   


   

}