package cn.fintecher.supply.finance.loan.manager.core.credit.dao;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseDictionaryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 18:44 2018/6/21
 */
@Mapper
public interface EnterpriseDictionaryDao {
    Message selectCode();

    /**
     * 查询财务报表期间
     *
     * @param year
     * @return
     */
    List<String> searchAccountingStatementTime(String year);


    List<BaseDictionaryEntity> searchAllDictionary(String code);
}
