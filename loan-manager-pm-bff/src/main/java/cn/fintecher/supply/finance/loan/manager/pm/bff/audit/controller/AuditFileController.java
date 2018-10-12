package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service.AuditFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 10:58 2018/7/11
 */
@RestController
@RequestMapping("/audit/auditFile")
@Api(tags = "审批文件相关接口")
public class AuditFileController {
    @Autowired
    private AuditFileService auditFileService;

    /**
     * 删除文件
     * */
    @ApiOperation(value = "删除文件", notes = "删除文件")
    @RequestMapping(value = "/deleteAuditFile", method = RequestMethod.GET)
    public Message deleteAuditFile(@RequestParam(value="pid") Long pid){
        return auditFileService.deleteAuditFile(pid);
    }

    /**
     * 上传审批文件
     */
    @ApiOperation(value = "上传审批文件", notes = "上传审批文件")
    @RequestMapping(value = "/uploadAuditFile", method = RequestMethod.POST)
    public Message uploadAuditFile(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value="pid") String pid) {
//        Message<List<AuditFileEntity>> message = auditFileService.searchAllAuditFileByOwnerIdAndCategory(pid,type);
//        if(null != message){
//          //  auditFileService.deleteAuditFileByOwnerId(pid,type);
//            List<AuditFileEntity> auditFileEntities = JSONUtil.toList(message.getMessage(), AuditFileEntity.class);
//            if (auditFileEntities.size() > 0){
//                for (AuditFileEntity fileEntity: auditFileEntities) {
//                    fileEntity.setStatus("DEL");
//                    auditFileService.update(fileEntity);
//                }
//            }
//        }
        return auditFileService.uploadAuditFile(file,type,pid);
    }

    /**
     * 刪除審批文件
     */
    @ApiOperation(value = "刪除審批文件", notes = "刪除審批文件")
    @RequestMapping(value = "/deleteAuditFileByOwnerId", method = RequestMethod.GET)
    public Message deleteAuditFileByOwnerId(@RequestParam(value="pid") String pid,@RequestParam(value = "type") String type) {
       // String type = "creditAgreement";
        return auditFileService.deleteAuditFileByOwnerId(pid,type);
    }

    /**
     * 下载授信文档
     */
    @ApiOperation(value = "下载授信文档", notes = "下载授信文档",produces = MediaType.IMAGE_PNG_VALUE)
    @RequestMapping(value = "/donwloadCompanyCreditDoc", method = RequestMethod.GET)
    public ResponseEntity<byte[]> donwloadCompanyCreditDoc(@RequestParam(value="pid") Long pid,@RequestParam(value="type") String type,@RequestParam(value="year",required = false) String year) {
        AuditCompanyEntity entity = auditFileService.searchCompanyById(pid);
        Long enterpriseId = Long.valueOf(entity.getEnterpriseId());
        return auditFileService.donwloadCompanyCreditDoc(enterpriseId,type,year);
    }

    /**
     *
     */
    @ApiOperation(value = "下载审批文件", notes = "下载审批文件",produces = MediaType.IMAGE_PNG_VALUE)
    @RequestMapping(value = "/donwloadAuditFile", method = RequestMethod.GET)
    public ResponseEntity<byte[]> donwloadAuditFile(@RequestParam(value="pid") Long pid){
        return auditFileService.donwloadAuditFile(pid);
    }

    /**
     * 上传文件并限定数量
     * */
    @ApiOperation(value = "上传限定数量文件", notes = "上传限定数量文件")
    @RequestMapping(value = "/uploadAuditFileLimit", method = RequestMethod.POST)
    public Message uploadAuditFileLimit(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value="pid") String pid,@RequestParam(value="count") Integer count) {
        try {
            return auditFileService.uploadAuditFileLimit(file, type,pid,count);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_bff",e.getMessage());
        }
    }

    /**
     * 上传文件gonghebin
     */
    @ApiOperation(value = "上传文件", notes = "上传文件")
    @RequestMapping(value = "/uploadAuditFileGong", method = RequestMethod.POST)
    public Message uploadAuditFileGong(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value="pid") String pid) {
        return auditFileService.uploadAuditFile(file,type,pid);
    }

}
