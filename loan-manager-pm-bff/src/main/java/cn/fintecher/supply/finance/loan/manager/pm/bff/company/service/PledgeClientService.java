package cn.fintecher.supply.finance.loan.manager.pm.bff.company.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.PledgeClientForm;

/**
 * @author gonghebin
 * @date 2018/8/18 0018下午 5:18
 */
public interface PledgeClientService {

    Message selectPledgeEnterprise(String name);

    Message updatePledgeEnterprise(PledgeClientForm pledgeClientForm);
}
