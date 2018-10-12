package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;

import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/5 17:43
 */
public interface ConfirmingStockCoreService {
    Message<PageResponse<List<ConfirmingStockInfoListResponse>>> confirmingStockPageList(ConfirmingStockInfoResquest confirmingStockInfoResquest);

    Message confirmFinancing(Long pid);

    Message refuseFinancing(Long pid);
}
