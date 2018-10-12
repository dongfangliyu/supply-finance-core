package cn.fintecher.supply.finance.loan.manager.service.confirming.service;


import cn.fintecher.common.utils.basecommon.message.Message;

/**
 * @author wuxiaoxing
 * @time 2018/9/6 9:50
 */
public interface ConfirmingStockCoreService {

    Message confirmFinancing(Long pid);

    Message refuseFinancing(Long pid);
}
