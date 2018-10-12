package cn.fintecher.supply.finance.loan.manager.core.credit.service;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.core.credit.dao.BaseTemplateDictionaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Param:
 * @Return:
 * @Author WuTianJuan
 * @Date Created in 19:41 2018/7/2
 */
@Service
public class BaseTemplateDictionaryService {
    @Autowired
    private BaseTemplateDictionaryDao baseTemplateDictionaryDao;


    public BaseTemplateDictionaryEntity downloadDocTemplate(String code){
        BaseTemplateDictionaryEntity entity = baseTemplateDictionaryDao.downloadDocTemplate(code);
        return entity;
    }
}
