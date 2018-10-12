package cn.fintecher.supply.finance.loan.manager.core.audit.dao;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 12:20 2018/7/11
 */
@Mapper
public interface AuditFileDao {
    Boolean  uploadAuditFile(AuditFileEntity auditFileEntity);

    AuditFileEntity searchAllFileByFid(Long pid);

    List<AuditFileEntity> searchAllFileByOwnerId(String pid);

    List<AuditFileEntity> searchAllAuditFileByOwnerIdAndCategory(@Param("ownerId") String ownerId, @Param("category") String category);

    void update(AuditFileEntity auditFileEntity);

    Boolean deleteAuditFileByOwnerId(@Param("ownerId")String ownerId, @Param("type") String type);
}
