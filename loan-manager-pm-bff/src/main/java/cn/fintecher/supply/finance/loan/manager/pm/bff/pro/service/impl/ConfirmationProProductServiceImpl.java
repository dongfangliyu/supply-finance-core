package cn.fintecher.supply.finance.loan.manager.pm.bff.pro.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pro.feign.FCConfirmationProProductService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pro.service.ConfirmationProProductService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoxing
 * @time 2018/8/29 16:56
 */
@Service
public class ConfirmationProProductServiceImpl implements ConfirmationProProductService {

    @Autowired
    private FCConfirmationProProductService fcConfirmationProProductService;

    @Override
    public Message submitProduct(ProProductEntity proProductEntity) {
        return fcConfirmationProProductService.submitProduct(proProductEntity);
    }
}
