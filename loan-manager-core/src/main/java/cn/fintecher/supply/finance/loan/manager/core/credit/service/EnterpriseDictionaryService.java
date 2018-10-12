package cn.fintecher.supply.finance.loan.manager.core.credit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.common.response.AccountStatementTimeResponse;
import cn.fintecher.supply.finance.loan.manager.core.credit.dao.EnterpriseDictionaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author WuTianJuan
 * @Date Created in 18:43 2018/6/21
 */
@Service
public class EnterpriseDictionaryService {
    @Autowired
    private EnterpriseDictionaryDao enterpriseDictionaryDao;

    public Message searchAccountingStatementTime(String year){
        Message msg = new Message();
        List<String> li = enterpriseDictionaryDao.searchAccountingStatementTime(year);
        msg.setMessage(li);
        return msg;
    }

    public List<BaseDictionaryEntity> searchAllDictionary(String code){
       return enterpriseDictionaryDao.searchAllDictionary(code);
    }
}
