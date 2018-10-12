package cn.fintecher.supply.finance.loan.manager.service.base.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.service.base.feign.FCBaseBankInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.base.service.BaseBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoxing
 * @time 2018/7/19 17:03
 */
@Service("BaseBankInfoService")
public class BaseBankInfoServiceImpl implements BaseBankInfoService {

    @Autowired
    private FCBaseBankInfoCore fcBaseBankInfoCore;

    @Override
    public BaseBankInfoEntity getLoanBankByCompanyId(Long companyId) {
        return fcBaseBankInfoCore.getLoanBankByCompanyId(companyId);
    }

    @Override
    public BaseBankInfoEntity getRepayBankBySignId(Long signId) {
        return fcBaseBankInfoCore.getRepayBankBySignId(signId);
    }

    @Override
    public Message update(BaseBankInfoEntity baseBankInfoEntity) {
        return fcBaseBankInfoCore.update(baseBankInfoEntity);
    }

    @Override
    public Message insert(BaseBankInfoEntity baseBankInfoEntity) {
        return fcBaseBankInfoCore.insert(baseBankInfoEntity);
    }
}
