package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.feign.FCConfirmingStockCoreService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.ConfirmingStockCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/6 9:28
 */
@Service
public class ConfirmingStockCoreServiceImpl implements ConfirmingStockCoreService {

    @Autowired
    private FCConfirmingStockCoreService fcConfirmingStockCoreService;

    @Override
    public Message<PageResponse<List<ConfirmingStockInfoListResponse>>> confirmingStockPageList(ConfirmingStockInfoResquest confirmingStockInfoResquest) {
        return fcConfirmingStockCoreService.confirmingStockPageList(confirmingStockInfoResquest);
    }

    @Override
    public Message confirmFinancing(Long pid) {
        return fcConfirmingStockCoreService.confirmFinancing(pid);
    }

    @Override
    public Message refuseFinancing(Long pid) {
        return fcConfirmingStockCoreService.refuseFinancing(pid);
    }
}
