package cn.fintecher.supply.finance.loan.manager.service.pro.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;

/**
 * @author wuxiaoxing
 * @time 2018/8/29 17:14
 */
public interface ConfirmationProProductService {
    Message submitProduct(ProProductEntity proProductEntity);
}
