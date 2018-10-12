package cn.fintecher.supply.finance.loan.manager.service.confirming.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditExamineResponse;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalForm;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;

import java.text.ParseException;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/7 15:41
 */
public interface ConfirmingStockFirstTrialService {
    Message<AuditExamineResponse> countTask(Integer userId, Integer node);

    Message receiveTask(SysUserAdminEntity sysUser);

    Message<PagedResponse<List<ConfirmingStockInfoListResponse>>> searchPageList(ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest) throws ParseException;

    Message submitContent(ConfirmingStockInfoApprovalForm confirmingStockInfoApprovalForm, SysUserAdminEntity sysUser);

    Message<AuditTaskHistoryEntity> getTrialResult(Long pid, Integer node);

    Message<List<AuditTaskHistoryEntity>> getTrialHistoryList(Long pid);
}
