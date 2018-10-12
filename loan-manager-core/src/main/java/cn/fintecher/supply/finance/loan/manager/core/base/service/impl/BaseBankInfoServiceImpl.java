package cn.fintecher.supply.finance.loan.manager.core.base.service.impl;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.core.base.service.BaseBankInfoService;
import cn.fintecher.supply.finance.loan.manager.core.register.dao.BaseBankInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiaoxing
 * @time 2018/7/19 17:03
 */
@Service("BaseBankInfoService")
public class BaseBankInfoServiceImpl implements BaseBankInfoService {

    @Autowired
    private BaseBankInfoDao baseBankInfoDao;

    @Override
    public BaseBankInfoEntity getLoanBankByCompanyId(Long companyId) {
        return baseBankInfoDao.getLoanBankByCompanyId(companyId);
    }

    @Override
    public BaseBankInfoEntity getRepayBankBySignId(Long signId) {
        return baseBankInfoDao.getRepayBankBySignId(signId);
    }

    @Override
    public Message update(BaseBankInfoEntity baseBankInfoEntity) {
        try {
            baseBankInfoDao.update(baseBankInfoEntity);
            return new Message(MessageType.MSG_SUCCESS,"base_core",null);
        }catch (Exception e){
            e.printStackTrace();
           return new Message(MessageType.MSG_ERROR,"base_core",e.getMessage());
        }
    }

    @Override
    public Message insert(BaseBankInfoEntity baseBankInfoEntity) {
        try {
            baseBankInfoDao.saveBaseInfoEntity(baseBankInfoEntity);
            return new Message(MessageType.MSG_SUCCESS,"base_core",null);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"base_core",e.getMessage());
        }
    }
}
