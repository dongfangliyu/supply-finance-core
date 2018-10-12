package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.feign.FCConfirmingStockSecondTrialService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.ConfirmingStockSecondTrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoxing
 * @time 2018/9/12 15:23
 */
@Service
public class ConfirmingStockSecondTrialServiceImpl implements ConfirmingStockSecondTrialService {

    @Autowired
    private FCConfirmingStockSecondTrialService fcConfirmingStockSecondTrialService;

    @Override
    public Message receiveTask(String userName) {
        return fcConfirmingStockSecondTrialService.receiveTask(userName);
    }

    @Override
    public Message submitContent(ConfirmingStockInfoApprovalForm confirmingStockInfoApprovalForm) {
        return fcConfirmingStockSecondTrialService.submitContent( confirmingStockInfoApprovalForm);
    }
}
