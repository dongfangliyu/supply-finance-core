package cn.fintecher.supply.finance.loan.manager.core.audit.dao;

import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchMarginDetailForm;
import cn.fintecher.supply.finance.loan.manager.common.response.MarginDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-21 17:17:15
 */
@Mapper
public interface AuditTaskEntryDao{
	
	Integer insertTaskEntry(AuditTaskEntryEntity auditTaskEntryEntity);
	
	List<AuditTaskEntryEntity> selectByTaskEntry(AuditTaskEntryEntity auditTaskEntryEntity);
	
	Integer updateTaskEntry(AuditTaskEntryEntity auditTaskEntryEntity);
	
	AuditTaskEntryEntity queryTaskEntryByPid(String pid);

	Integer queryPaymentCountByOrderInfoId(String orderInfoId);

    int searchTaskEntryListCount(AuditEntryForm auditEntryForm);

	List<AuditTaskEntryEntity> searchTaskEntryList(AuditEntryForm auditEntryForm);

    Double searchSumOfRepaymentPrice(Long orderInfoId);

    List<AuditTaskEntryEntity> searchTaskEntityByOrderId(Long orderInfoId);

	Double searchSumOfRepaymentPriceByState(Long orderInfoId);
	
	List<MarginDetailResponse> searchMarginDetail(SearchMarginDetailForm searchMarginDetailForm);
}
