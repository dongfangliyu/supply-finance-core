package cn.fintecher.supply.finance.loan.manager.core.audit.dao;

import cn.fintecher.supply.finance.loan.manager.common.model.AuditWebsiteInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author WuTianJuan
 * @Date Created in 10:09 2018/7/7
 */
@Mapper
public interface AuditWebsiteInfoDao {

    Boolean submitWebsiteInfo(AuditWebsiteInfoEntity entity);

    AuditWebsiteInfoEntity searchWebsiteInfo(String id);

}
