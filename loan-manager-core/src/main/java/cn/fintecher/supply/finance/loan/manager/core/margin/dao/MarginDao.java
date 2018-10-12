package cn.fintecher.supply.finance.loan.manager.core.margin.dao;

import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoResponse;
import org.apache.ibatis.annotations.Mapper;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.MarginForm;

/**
 * @author lss
 * @date 2018/8/28
 */
@Mapper
public interface MarginDao {

	int searchMarginListCount(MarginForm marginForm);

	List<ConfirmingStockInfoResponse> searchMarginList(MarginForm marginForm);

	void saveAuditTaskEntryEntity(AuditTaskEntryEntity auditTaskEntryEntity);
	
}
