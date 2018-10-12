package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 11:01 2018/7/11
 */
public interface AuditFileService {
    Message uploadAuditFile(MultipartFile file, String type,String pid);

    /**
     * 下载授信文檔
     */
    ResponseEntity<byte[]> donwloadCompanyCreditDoc(Long pid,String type,String year);

    ResponseEntity<byte[]> donwloadAuditFile(Long pid);


    Message uploadAuditFileLimit(MultipartFile file, String type, String pid, Integer count);

    Message deleteAuditFile(Long pid);

    AuditCompanyEntity searchCompanyById(Long pid);

    Message<List<AuditFileEntity>> searchAllAuditFileByOwnerIdAndCategory(String ownerId,String type);

    AuditFileEntity searchAllFileByOwnerId(Long pid);

    Message deleteAuditFileByOwnerId(String ownerId,String type);

    Message update(AuditFileEntity auditFileEntity);
}

