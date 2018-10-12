package cn.fintecher.supply.finance.loan.manager.core.audit.service;

import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchMarginDetailForm;
import cn.fintecher.supply.finance.loan.manager.common.response.MarginDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import java.util.List;

/**
 * 
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-21 17:17:15
 */
public interface AuditTaskEntryService{

    /**
	 * 新增
	 * @param 
	 * @return
	 */
	Integer insertTaskEntry(AuditTaskEntryEntity auditTaskEntryEntity);
	
	/**
	 * 查询
	 * @param 
	 * @return
	 */
	List<AuditTaskEntryEntity> selectByTaskEntry(AuditTaskEntryEntity auditTaskEntryEntity);
	
	/**
	 * 修改
	 * @param 
	 */
	Integer updateTaskEntry(AuditTaskEntryEntity auditTaskEntryEntity);
	
	/**
	 * 根据主键查询
	 * @param 
	 * @return
	 */
	AuditTaskEntryEntity queryTaskEntryByPid(String pid);

	/**
     * 根据orderInfo id查询已付款总额
     * @return
     */
	Integer queryPaymentCountByOrderInfoId(String orderInfoId);

    int searchTaskEntryListCount(AuditEntryForm auditEntryForm);

	List<AuditTaskEntryEntity> searchTaskEntryList(AuditEntryForm auditEntryForm);

    Double searchSumOfRepaymentPrice(Long pid);

    List<AuditTaskEntryEntity> searchTaskEntityByOrderId(Long orderInfoId);

    Double searchSumOfRepaymentPriceByState(Long orderId);
    
    List<MarginDetailResponse> searchMarginDetail(SearchMarginDetailForm searchMarginDetailForm);
}
