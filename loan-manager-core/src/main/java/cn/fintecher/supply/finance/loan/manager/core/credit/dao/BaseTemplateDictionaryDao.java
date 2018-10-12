package cn.fintecher.supply.finance.loan.manager.core.credit.dao;

import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Param:
 * @Return:
 * @Author WuTianJuan
 * @Date Created in 19:29 2018/7/2
 */

@Mapper
public interface BaseTemplateDictionaryDao {

    /**
     * 根据code查询所有
     * @param code
     * @return
     */
    BaseTemplateDictionaryEntity downloadDocTemplate(String code);
}
