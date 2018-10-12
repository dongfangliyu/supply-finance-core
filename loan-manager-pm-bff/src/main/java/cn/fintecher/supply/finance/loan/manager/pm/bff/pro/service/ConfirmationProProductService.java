package cn.fintecher.supply.finance.loan.manager.pm.bff.pro.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;

/**
 * @author wuxiaoxing
 * @time 2018/8/29 16:05
 */
public interface ConfirmationProProductService {

    Message submitProduct(ProProductEntity proProductEntity);
}
