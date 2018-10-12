package cn.fintecher.supply.finance.loan.manager.service.confirming.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;

/**
 * @author wuxiaoxing
 * @time 2018/9/13 11:18
 */
public interface ConfirmingStockSecondTrialService {
    Message receiveTask(SysUserAdminEntity sysUser);

    Message submitContent(ConfirmingStockInfoApprovalForm confirmingStockInfoApprovalForm, SysUserAdminEntity sysUser);
}
