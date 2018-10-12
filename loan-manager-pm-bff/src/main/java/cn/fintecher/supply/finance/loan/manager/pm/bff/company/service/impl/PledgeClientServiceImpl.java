package cn.fintecher.supply.finance.loan.manager.pm.bff.company.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.PledgeClientForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.company.feign.FCPledgeClientService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.company.service.PledgeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/8/18 0018下午 5:18
 */
@Service
public class PledgeClientServiceImpl implements PledgeClientService {

    @Autowired
    private FCPledgeClientService fcPledgeClientService;

    @Override
    public Message selectPledgeEnterprise(String name) {
        return fcPledgeClientService.selectPledgeEnterprise(name);
    }

    @Override
    public Message updatePledgeEnterprise(PledgeClientForm pledgeClientForm) {
        return fcPledgeClientService.updatePledgeEnterprise(pledgeClientForm);
    }
}
