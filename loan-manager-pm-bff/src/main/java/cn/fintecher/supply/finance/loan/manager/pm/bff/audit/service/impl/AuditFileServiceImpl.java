package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign.FCAuditFileService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 11:07 2018/7/11
 */
@Service
public class AuditFileServiceImpl implements AuditFileService {
    @Autowired
    private FCAuditFileService fcAuditFileService;

    @Override
    public Message uploadAuditFile(MultipartFile file, String type, String pid) {
        return fcAuditFileService.uploadAuditFile(file, type, pid);
    }

    @Override
    public ResponseEntity<byte[]> donwloadCompanyCreditDoc(Long pid, String type, String year) {
        return fcAuditFileService.donwloadCompanyCreditDoc(pid, type, year);
    }

    @Override
    public ResponseEntity<byte[]> donwloadAuditFile(Long pid) {
        return fcAuditFileService.donwloadAuditFile(pid);
    }

    @Override
    public Message uploadAuditFileLimit(MultipartFile file, String type, String pid, Integer count) {
        return fcAuditFileService.uploadAuditFileLimit( file,  type,  pid,  count);
    }

    @Override
    public Message deleteAuditFile(Long pid) {
        return fcAuditFileService.deleteAuditFile(pid);
    }

    @Override
    public AuditCompanyEntity searchCompanyById(Long pid) {
        return fcAuditFileService.searchCompanyById(pid);
    }

    @Override
    public Message<List<AuditFileEntity>> searchAllAuditFileByOwnerIdAndCategory(String ownerId, String type) {
        return fcAuditFileService.searchAllAuditFileByOwnerIdAndCategory(ownerId,type);
    }

    @Override
    public AuditFileEntity searchAllFileByOwnerId(Long pid) {
        return fcAuditFileService.searchAllFileByOwnerId(pid);
    }

    @Override
    public Message deleteAuditFileByOwnerId(String ownerId, String type) {
        return fcAuditFileService.deleteAuditFileByOwnerId(ownerId,type);
    }

    @Override
    public Message update(AuditFileEntity auditFileEntity) {
        return fcAuditFileService.update(auditFileEntity);
    }

}
