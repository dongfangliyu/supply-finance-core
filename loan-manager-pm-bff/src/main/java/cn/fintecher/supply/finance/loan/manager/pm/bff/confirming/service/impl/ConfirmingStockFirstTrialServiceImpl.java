package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditExamineResponse;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalForm;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoApprovalResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.feign.FCConfirmingStockFirstTrialService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.ConfirmingStockFirstTrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/7 10:10
 */
@Service
public class ConfirmingStockFirstTrialServiceImpl implements ConfirmingStockFirstTrialService {

    @Autowired
    private FCConfirmingStockFirstTrialService fcConfirmingStockFirstTrialService;

    @Override
    public Message<AuditTaskHistoryEntity> getTrialResult(Long pid, Integer node) {
        return fcConfirmingStockFirstTrialService.getTrialResult(pid,node);
    }

    @Override
    public Message<List<AuditTaskHistoryEntity>> getTrialHistoryList(Long pid) {
        return fcConfirmingStockFirstTrialService.getTrialHistoryList(pid);
    }

    @Override
    public Message<AuditExamineResponse> countTask(String name, Integer node) {
        return fcConfirmingStockFirstTrialService.countTask(name,node);
    }

    @Override
    public Message receiveTask(String name) {
        return fcConfirmingStockFirstTrialService.receiveTask(name);
    }

    @Override
    public Message<PagedResponse<List<ConfirmingStockInfoListResponse>>> searchPageList(ConfirmingStockInfoApprovalResquest confirmingStockInfoApprovalResquest) {
        return fcConfirmingStockFirstTrialService.searchPageList(confirmingStockInfoApprovalResquest);
    }

    @Override
    public Message submitContent(ConfirmingStockInfoApprovalForm confirmingStockInfoApprovalForm) {
        return fcConfirmingStockFirstTrialService.submitContent( confirmingStockInfoApprovalForm) ;
    }
}
