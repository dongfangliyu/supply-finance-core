package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditFileCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCFileCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditFileService;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseFinancialCore;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterFileCore;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 11:19 2018/7/11
 */
@Service
public class AuditFileServiceImpl implements AuditFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditFileServiceImpl.class);

    @Autowired
    private FCAuditFileCore fcAuditFileCore;

    @Autowired
    private FCFileCore fcFileCore;

    @Autowired
    private FCEnterpriseFinancialCore enterpriseFinancialCore;

    @Autowired
    private FCRegisterFileCore fcRegisterFileCore;

    @Override
    public Message uploadAuditFile(MultipartFile file, String type, String pid) {
        List<AuditFileEntity> fileEntity = fcAuditFileCore.searchAllAuditFileByOwnerIdAndCategory(pid,type);
        if(null != fileEntity){
            //  auditFileService.deleteAuditFileByOwnerId(pid,type);
            if (fileEntity.size() > 0){
                for (AuditFileEntity entity: fileEntity) {
                    entity.setStatus("DEL");
                    fcAuditFileCore.update(entity);
                }
            }
        }

        ResponseEntity<Message> entity = fcFileCore.fileUpload(file);
        Message message = entity.getBody();
        Message msg = new Message();
        HashMap<String, String> params = (HashMap<String, String>) message.getMessage();
        if (message.getCode() == 0){
            AuditFileEntity auditFileEntity = new AuditFileEntity();
            auditFileEntity.setCategory(type);
            auditFileEntity.setFileName(file.getOriginalFilename());
            auditFileEntity.setOwnerId(pid);
            auditFileEntity.setPath(params.get("path"));
            auditFileEntity.setFullPath(params.get("fullPath"));
            auditFileEntity.setFileGroup(params.get("group"));
            auditFileEntity.setStatus(Constants.DATA_STATUS_NOL);
            auditFileEntity.setCreateAt(Calendar.getInstance().getTime());
            auditFileEntity.setUpdateAt(Calendar.getInstance().getTime());
            msg = fcAuditFileCore.insert(auditFileEntity);
        }
        return msg;
    }


    @Override
    public ResponseEntity<byte[]> donwloadCompanyCreditDoc(Long pid,String type,String year) {
            ResponseEntity<byte[]> responseEntity =null;
            HttpHeaders headers = new HttpHeaders();
            CompanyFileEntity entity = enterpriseFinancialCore.searchAllFileInfo(pid,type,year);
            String fullPath = entity.getFullPath();
            responseEntity = fileDownLoad(fullPath, "image");
            headers.set(HttpHeaders.CONTENT_TYPE, getContentType(fullPath));
        return new ResponseEntity(responseEntity.getBody(),headers,HttpStatus.OK);
    }


    public ResponseEntity<byte[]> donwloadAuditFile(Long pid) {
         AuditFileEntity entity = searchAllFileByFid(pid);
         String fullPath = entity.getFullPath();
         ResponseEntity<byte[]> responseEntity = fileDownLoad(fullPath, "image");
         HttpHeaders headers = new HttpHeaders();
         headers.set(HttpHeaders.CONTENT_TYPE, getContentType(fullPath));
         return new ResponseEntity(responseEntity.getBody(),headers,HttpStatus.OK);
    }

    public AuditFileEntity searchAllFileByFid(Long pid){
        return fcAuditFileCore.searchAllFileByFid(pid);
    }


    /**
     * 文件下载
     *
     * @param fullPath  文件上传返回的文件保存完整地址
     * @param fileName  文件名称
     * @return
     */
    public ResponseEntity<byte[]> fileDownLoad(String fullPath, String fileName) {
        return fcFileCore.fileDownLoad(fullPath, fileName);
    }

    /**
     获取文件MIME类型
     @param fileName
     @return*/

    public static String getContentType(String fileName){
        String type = null;
        Path path = Paths.get(fileName);
        try {
            type = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return type;
    }

    public AuditFileEntity searchAllFileByOwnerId(Long ownerId){
       return fcAuditFileCore.searchAllFileByOwnerId(ownerId);
    }

    @Override
    public Message uploadAuditLoanFile(MultipartFile file, String type, String pid) {
        ResponseEntity<Message> entity = fcFileCore.fileUpload(file);
        Message message = entity.getBody();
        Message msg = new Message();
        if (!Strings.isNullOrEmpty(pid)){
            List<AuditFileEntity> fileEntity = fcAuditFileCore.searchAllAuditFileByOwnerIdAndCategory(pid,type);
            if (fileEntity.size() != 0){
                msg.setCode(MsgCodeConstant.ERR_UPLOAD_PRO_FILE);
                msg.setMessage("请勿重复上传,请先删除再上传!");
                return msg;
            }
        }

        HashMap<String, String> params = (HashMap<String, String>) message.getMessage();
        if (message.getCode() == 0){
            AuditFileEntity auditFileEntity = new AuditFileEntity();
            auditFileEntity.setCategory(type);
            auditFileEntity.setFileName(file.getOriginalFilename());
            auditFileEntity.setOwnerId(pid);
            auditFileEntity.setPath(params.get("path"));
            auditFileEntity.setFileGroup(params.get("group"));
            auditFileEntity.setFullPath(params.get("fullPath"));
            auditFileEntity.setStatus("NOL");
            auditFileEntity.setCreateAt(Calendar.getInstance().getTime());
            auditFileEntity.setUpdateAt(Calendar.getInstance().getTime());
            msg = fcAuditFileCore.insert(auditFileEntity);
        }
        return msg;
    }

    @Override
    public Message uploadAuditFileLimit(MultipartFile file, String type, String pid, Integer count) {
        if(count!=null && count > 0){
            List<AuditFileEntity> auditFileEntityList = fcAuditFileCore.searchAllAuditFileByOwnerIdAndCategory(pid,type);
            if(auditFileEntityList!=null && auditFileEntityList.size()>=count){
                return new Message(MessageType.MSG_ERROR,"audit_service","文件上传达上限！");
            }
        }
        ResponseEntity<Message> entity = fcFileCore.fileUpload(file);
        Message message = entity.getBody();
        if(message.getCode() == 0){
            HashMap<String, String> params = (HashMap<String, String>) message.getMessage();
            AuditFileEntity auditFileEntity = new AuditFileEntity();
            auditFileEntity.setCategory(type);
            auditFileEntity.setFileName(file.getOriginalFilename());
            auditFileEntity.setOwnerId(pid);
            auditFileEntity.setPath(params.get("path"));
            auditFileEntity.setFileGroup(params.get("group"));
            auditFileEntity.setFullPath(params.get("fullPath"));
            auditFileEntity.setStatus("NOL");
            auditFileEntity.setCreateAt(Calendar.getInstance().getTime());
            auditFileEntity.setUpdateAt(Calendar.getInstance().getTime());
            return fcAuditFileCore.insert(auditFileEntity);
        }else{
            return message;
        }
    }

    @Override
    public Message<List<AuditFileEntity>> searchAllAuditFileByOwnerIdAndCategory(String ownerId, String type) {
        return new Message<List<AuditFileEntity>>(MessageType.MSG_SUCCESS,"audit_service",fcAuditFileCore.searchAllAuditFileByOwnerIdAndCategory(ownerId,type));
    }

    @Override
    public Message deleteAuditFile(Long pid) {
        AuditFileEntity auditFileEntity = fcAuditFileCore.searchAllFileByFid(pid);
        auditFileEntity.setStatus("DEL");
        return fcAuditFileCore.update(auditFileEntity);
    }

    @Override
    public AuditCompanyEntity searchCompanyById(Long pid) {
        return fcAuditFileCore.searchCompanyById(pid);
    }

    @Override
    public Message<List<AuditFileEntity>> searchAllAuditFileByOwnerId(String pid) {
        return new Message<List<AuditFileEntity>>(MessageType.MSG_SUCCESS,"audit_service",fcAuditFileCore.searchAllFileByOwnerId(pid));
    }

    @Override
    public Message deleteAuditFileByOwnerId(String ownerId, String type) {
         Message msg = new Message();
         List<AuditFileEntity> list = fcAuditFileCore.searchAllAuditFileByOwnerIdAndCategory(ownerId,type);

         Boolean flag =false;
         if(list.size()!=0){
           flag =  fcAuditFileCore.deleteAuditFileByOwnerId(ownerId,type);
         }
         if(flag){
             msg.setCode(0);
             msg.setType("audit_service");
         } else {
             msg.setCode(-1);
             msg.setType("audit_service");
         }
         return msg;
    }

    @Override
    public Message update(AuditFileEntity auditFileEntity){
         return fcAuditFileCore.update(auditFileEntity);
    }
}
