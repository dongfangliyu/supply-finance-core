package cn.fintecher.supply.finance.loan.manager.pm.bff.register.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/6/19 0019下午 1:41
 */
public interface AutoRegisterLoginService {
    Message addOrUpdateUser();
}
