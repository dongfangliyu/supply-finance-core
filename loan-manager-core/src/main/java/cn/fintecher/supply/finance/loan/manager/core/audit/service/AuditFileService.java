package cn.fintecher.supply.finance.loan.manager.core.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 12:20 2018/7/11
 */
public interface AuditFileService {
    Message uploadAuditFile(AuditFileEntity auditFileEntity);

    AuditFileEntity searchAllFileByFid(Long pid);

    List<AuditFileEntity> searchAllFileByOwnerId(Long pid);

    List<AuditFileEntity> searchAllAuditFileByOwnerIdAndCategory(@Param("ownerId") String ownerId,@Param("category")  String category);

    Message update(AuditFileEntity auditFileEntity);

    Boolean deleteAuditFileByOwnerId(@Param("ownerId") String ownerId,@Param("type") String type);
}
