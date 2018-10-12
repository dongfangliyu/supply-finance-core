package cn.fintecher.supply.finance.loan.manager.service.credit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.TableValidateForm;

/**
 * @Author WuTianJuan
 * @Date Created in 10:45 2018/6/26
 */
public interface TableValidateService {
    Message fileTableValidate(TableValidateForm tableValidateForm);
}
