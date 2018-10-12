package cn.fintecher.supply.finance.loan.manager.pm.bff.register.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.pm.bff.register.feign.FCRegisterCompanyService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.register.service.AutoRegisterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/6/19 0019下午 1:42
 */
@Service
public class AutoRegisterLoginServiceImpl implements AutoRegisterLoginService {

    @Autowired
    private FCRegisterCompanyService fcRegisterCompanyService;


    @Override
    public Message addOrUpdateUser() {
        Message message = fcRegisterCompanyService.addOrUpdateUser();
        return message;
    }
}
