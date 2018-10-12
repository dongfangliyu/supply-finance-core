package cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.TableValidateForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.feign.FCEnterpriseFinancialService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service.TableValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WuTianJuan
 * @Date Created in 10:30 2018/6/26
 */
@Service
public class TableValidateServiceImpl implements TableValidateService {

    @Autowired
    private FCEnterpriseFinancialService enterpriseFinancialService;

    public Message fileTableValidate(TableValidateForm tableValidateForm){
        return enterpriseFinancialService.fileTableValidate(tableValidateForm);
    }
}
