package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditExamineResponse;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalForm;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;

import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/7 10:09
 */
public interface ConfirmingStockFirstTrialService {

    Message<AuditExamineResponse> countTask(String name, Integer node);

    Message receiveTask(String name);

    Message<PagedResponse<List<ConfirmingStockInfoListResponse>>> searchPageList(ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest);

    Message submitContent(ConfirmingStockInfoApprovalForm confirmingStockInfoApprovalForm);

    Message<AuditTaskHistoryEntity> getTrialResult(Long pid, Integer node);

    Message<List<AuditTaskHistoryEntity>> getTrialHistoryList(Long pid);

}
