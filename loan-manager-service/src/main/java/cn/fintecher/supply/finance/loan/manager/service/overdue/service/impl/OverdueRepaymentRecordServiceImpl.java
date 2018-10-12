package cn.fintecher.supply.finance.loan.manager.service.overdue.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.service.overdue.core.OverdueRepaymentRecordCore;
import cn.fintecher.supply.finance.loan.manager.service.overdue.service.OverdueRepaymentRecordService;
import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueRepaymentRecordEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 13:24:15
 */
@Service("overdueRepaymentRecordService")
public class OverdueRepaymentRecordServiceImpl implements OverdueRepaymentRecordService {

	@Autowired
    private OverdueRepaymentRecordCore overdueRepaymentRecordCore;
    
    @Override
	public Message insertRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity){
		return overdueRepaymentRecordCore.insertRepaymentRecord(overdueRepaymentRecordEntity);
	}
	
	@Override
	public Message<List<OverdueRepaymentRecordEntity>> selectByRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity) {
		return overdueRepaymentRecordCore.selectByRepaymentRecord(overdueRepaymentRecordEntity);
	}
	
	@Override
	public Message updateRepaymentRecord(OverdueRepaymentRecordEntity overdueRepaymentRecordEntity) {
		return overdueRepaymentRecordCore.updateRepaymentRecord(overdueRepaymentRecordEntity);
	}
    
	@Override
	public Message<OverdueRepaymentRecordEntity> queryRepaymentRecordByPid(String pid) {
		return overdueRepaymentRecordCore.queryRepaymentRecordByPid(pid);
	}

}

