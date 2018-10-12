package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 11:15 2018/7/11
 */
@RestController
@RequestMapping("/audit/companyFile")
public class AuditFileController {

    @Autowired
    private AuditFileService auditFileService;

    @ResponseBody
    @RequestMapping(value = "/uploadAuditFile", method = RequestMethod.POST)
    public Message uploadAuditFile(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "pid") String pid) {
        return auditFileService.uploadAuditFile(file, type, pid);
    }

    /**
     * 下载授信文档
     */
    @RequestMapping(value = "/donwloadCompanyCreditDoc", method = RequestMethod.GET)
    public ResponseEntity<byte[]> donwloadCompanyCreditDoc(@RequestParam(value = "pid") Long pid, @RequestParam(value = "type") String type, @RequestParam(value = "year", required = false) String year) {
        return auditFileService.donwloadCompanyCreditDoc(pid, type, year);
    }

    /**
     *
     */
    @RequestMapping(value = "/donwloadAuditFile", method = RequestMethod.GET)
    public ResponseEntity<byte[]> donwloadAuditFile(@RequestParam(value = "pid") Long pid) {
        return auditFileService.donwloadAuditFile(pid);
    }

    @RequestMapping(value = "/searchAllFileByFid", method = RequestMethod.GET)
    public AuditFileEntity searchAllFileByFid(@RequestParam(value = "pid") Long pid) {
        return auditFileService.searchAllFileByFid(pid);
    }

    @RequestMapping(value = "/searchAllFileByOwnerId", method = RequestMethod.GET)
    public AuditFileEntity searchAllFileByOwnerId(@RequestParam(value = "pid") Long pid) {
        return auditFileService.searchAllFileByOwnerId(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/uploadAuditLoanFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Message uploadAuditLoanFile(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "pid") String pid) {
        return auditFileService.uploadAuditLoanFile(file, type, pid);
    }

    @ResponseBody
    @RequestMapping(value = "/uploadAuditFileLimit", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Message uploadAuditFileLimit(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "pid") String pid, @RequestParam(value = "count") Integer count) {
        try {
            return auditFileService.uploadAuditFileLimit(file, type, pid, count);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR, "audit_service", e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/searchAllAuditFileByOwnerIdAndCategory", method = RequestMethod.GET)
    public Message<List<AuditFileEntity>> searchAllAuditFileByOwnerIdAndCategory(@RequestParam(value = "pid", required = false) String pid, @RequestParam(value = "type") String type) {
        try {
            return auditFileService.searchAllAuditFileByOwnerIdAndCategory(pid, type);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR, "audit_service", e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/searchAllAuditFileByOwnerId", method = RequestMethod.GET)
    public Message<List<AuditFileEntity>> searchAllAuditFileByOwnerId(@RequestParam(value = "pid") String pid) {
        try {
            return auditFileService.searchAllAuditFileByOwnerId(pid);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR, "audit_service", e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/deleteAuditFile", method = RequestMethod.GET)
    public Message deleteAuditFile(@RequestParam(value = "pid") Long pid) {
        return auditFileService.deleteAuditFile(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/searchCompanyById", method = RequestMethod.GET)
    public AuditCompanyEntity searchCompanyById(@RequestParam(value = "pid") Long pid) {
        return auditFileService.searchCompanyById(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAuditFileByOwnerId", method = RequestMethod.GET)
    public Message deleteAuditFileByOwnerId(@RequestParam(value = "ownerId") String ownerId, @RequestParam(value = "type") String type) {
        return auditFileService.deleteAuditFileByOwnerId(ownerId, type);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Message update(@RequestBody  AuditFileEntity auditFileEntity){
        return auditFileService.update(auditFileEntity);
    }

}