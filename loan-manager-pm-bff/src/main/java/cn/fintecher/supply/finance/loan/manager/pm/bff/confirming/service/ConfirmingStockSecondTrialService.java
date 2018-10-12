package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalForm;

/**
 * @author wuxiaoxing
 * @time 2018/9/12 15:18
 */
public interface ConfirmingStockSecondTrialService {
    Message receiveTask(String userName);

    Message submitContent(ConfirmingStockInfoApprovalForm confirmingStockInfoApprovalForm);
}
