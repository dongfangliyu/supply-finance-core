package cn.fintecher.supply.finance.loan.manager.core.overdue.dao;

import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueRepaymentRecordEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRecordForm;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 逾期还款记录表
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 13:24:15
 */
@Mapper
public interface OverdueRepaymentRecordDao{
	
	Integer insertRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity);
	
	List<OverdueRepaymentRecordEntity> selectByRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity);
	
	Integer updateRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity);
	
	OverdueRepaymentRecordEntity queryRepaymentRecordByPid(String pid);

	Integer queryCountByRecordForm(OverdueOrderRecordForm overdueOrderForm);

	List<OverdueRepaymentRecordEntity> queryListByRecordForm(OverdueOrderRecordForm overdueOrderForm);
}
