package cn.fintecher.supply.finance.loan.manager.core.audit.dao;

import cn.fintecher.supply.finance.loan.manager.common.model.AuditSurveyInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author WuTianJuan
 * @Date Created in 16:20 2018/7/6
 */
@Mapper
public interface AuditSurveyinfoDao {

    Boolean submitSurveyInfo(AuditSurveyInfoEntity entity);

    AuditSurveyInfoEntity searchSurveyInfo(String id);
}
