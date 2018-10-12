package cn.fintecher.supply.finance.loan.manager.service.audit.service;

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
 * @Date Created in 11:18 2018/7/11
 */
public interface AuditFileService {

    Message uploadAuditFile(MultipartFile file, String type, String pid);


    /**
     * 下载授信文档
     */
    ResponseEntity<byte[]> donwloadCompanyCreditDoc(@RequestParam(value = "pid") Long pid,@RequestParam(value="type") String type,@RequestParam(value="year") String year);


    ResponseEntity<byte[]> donwloadAuditFile(@RequestParam(value = "pid") Long pid);

    AuditFileEntity searchAllFileByFid(@RequestParam(value = "pid") Long pid);

    AuditFileEntity searchAllFileByOwnerId(@RequestParam(value = "ownerId") Long ownerId);

    Message uploadAuditLoanFile(MultipartFile file, String type, String pid);

    Message uploadAuditFileLimit(MultipartFile file, String type, String pid, Integer count);

    Message<List<AuditFileEntity>> searchAllAuditFileByOwnerIdAndCategory(String pid, String type);

    Message deleteAuditFile(Long pid);

    AuditCompanyEntity searchCompanyById(Long pid);

    Message<List<AuditFileEntity>> searchAllAuditFileByOwnerId(String pid);

    Message deleteAuditFileByOwnerId(String ownerId,String type);

    Message update(@RequestBody AuditFileEntity auditFileEntity);
}
