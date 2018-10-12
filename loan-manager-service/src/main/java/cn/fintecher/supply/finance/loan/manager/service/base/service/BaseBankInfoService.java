package cn.fintecher.supply.finance.loan.manager.service.base.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;

/**
 * @author wuxiaoxing
 * @time 2018/7/19 17:03
 */
public interface BaseBankInfoService {
    BaseBankInfoEntity getLoanBankByCompanyId(Long companyId);

    BaseBankInfoEntity getRepayBankBySignId(Long signId);

    Message update(BaseBankInfoEntity baseBankInfoEntity);

    Message insert(BaseBankInfoEntity baseBankInfoEntity);
}
