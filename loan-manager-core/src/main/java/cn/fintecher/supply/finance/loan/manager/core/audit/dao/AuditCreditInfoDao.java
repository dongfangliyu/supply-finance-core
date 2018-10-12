package cn.fintecher.supply.finance.loan.manager.core.audit.dao;

import cn.fintecher.supply.finance.loan.manager.common.model.AuditCreditInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018-6-19 10:36:28 
 */
@Mapper
public interface AuditCreditInfoDao {

    Boolean submitCreditReview(AuditCreditInfoEntity entity);

    List<AuditCreditInfoEntity> searchCreditInfo(Long id);

    Boolean updateCreditReview(AuditCreditInfoEntity entity);
    
    /**
     * 根据核心企业id，查询授信信息
     * 胡进宝
     * @param id
     * @return
     */
    AuditCreditInfoEntity getEntityByEnterpriseId(Long id);

    int searchCreditInfoCount(String pid);
}
