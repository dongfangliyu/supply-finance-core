package cn.fintecher.supply.finance.loan.manager.service.register.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCFileCore;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterCompanyCore;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterFileCore;
import cn.fintecher.supply.finance.loan.manager.service.register.service.RegisterFileService;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gonghebin
 * @date 2018/6/27 0027下午 1:26
 */
@Service
public class RegisterFileServiceImpl implements RegisterFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterFileServiceImpl.class);

    @Autowired
    private FCFileCore fcFileCore;

    @Autowired
    private FCRegisterFileCore fcRegisterFileCore;

    @Autowired
    private FCRegisterCompanyCore fcRegisterCompanyCore;

    @Override
    public Message uploadIdentityFile(MultipartFile file, String type, String registerCode) {

        Message message = new Message();
        if (Strings.isNullOrEmpty(registerCode)){
            message.setCode(MsgCodeConstant.ERR_REGISTER_FILE_UPLOAD);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }

        RegisterUserEntity userEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCode);

        if (null == userEntity){
            message.setCode(MsgCodeConstant.ERR_REGISTER_FILE_UPLOAD);
            message.setMessage("注册CODE不存在,请重新注册!");
            return message;
        }
        RegisterFileEntity fileEntity = fcRegisterFileCore.searchRegisterFile(type, userEntity.getPid().toString());
        if (null != fileEntity){
            fileEntity.setStatus(Constants.DATA_STATUS_DEL);
            fileEntity.setUpdateAt(new Date());
            fcRegisterFileCore.updateRegisterFile(fileEntity);
        }

        ResponseEntity<Message> entity = fcFileCore.fileUpload(file);
        message = entity.getBody();
        HashMap<String, String> params = (HashMap<String, String>) message.getMessage();

        if (message.getCode() == 0){
            RegisterFileEntity registerFileEntity = new RegisterFileEntity();
            registerFileEntity.setFileName(file.getOriginalFilename());
            registerFileEntity.setCategory(type);
            registerFileEntity.setFileSuffix(file.getContentType());
            registerFileEntity.setOwnerId(userEntity.getPid().toString());
            registerFileEntity.setPath(params.get("path"));
            registerFileEntity.setFullPath(params.get("fullPath"));
            registerFileEntity.setFileGroup(params.get("group"));
            registerFileEntity.setStatus("NOL");
            registerFileEntity.setCreateAt(Calendar.getInstance().getTime());
            registerFileEntity.setUpdateAt(Calendar.getInstance().getTime());
            try {
                message = fcRegisterFileCore.saveRegisterFile(registerFileEntity);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("文件信息保存失败：" + params);
            }
        }
        return message;
    }

    @Override
    public Message searchRegisterFile(String type, String registerCode) {
        Message message = new Message();
        if (Strings.isNullOrEmpty(registerCode)){
            message.setCode(MsgCodeConstant.ERR_REGISTER_FILE_UPLOAD);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }

        RegisterUserEntity userEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCode);

        if (null == userEntity){
            message.setCode(MsgCodeConstant.ERR_REGISTER_FILE_UPLOAD);
            message.setMessage("注册CODE不存在,请重新注册!");
            return message;
        }
        String ownerId = userEntity.getPid().toString();

        Map result = new HashMap();
        RegisterFileEntity entity = fcRegisterFileCore.searchRegisterFile(type,ownerId);
        if (null != entity){
            Long fid = entity.getPid();
            String fileName = entity.getFileName();
            String fullPath = entity.getFullPath();
            result.put("fullPath",fullPath);
            fullPath=fullPath.substring(0, fullPath.lastIndexOf(".")).concat(".100x100.png");
            result.put("fid",fid);
            result.put("fileName",fileName);
            result.put("maxFullPath",fullPath);
            message.setMessage(result);
        }
        return message;
    }

    @Override
    public Message deleteRegisterFile(String type, String registerCode) {
        Message message = new Message();
        if (Strings.isNullOrEmpty(registerCode)){
            message.setCode(MsgCodeConstant.ERR_REGISTER_FILE_UPLOAD);
            message.setMessage("注册帐号不存在,请重新注册!");
            return message;
        }

        RegisterUserEntity userEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(registerCode);

        if (null == userEntity){
            message.setCode(MsgCodeConstant.ERR_REGISTER_FILE_UPLOAD);
            message.setMessage("注册CODE不存在,请重新注册!");
            return message;
        }

        String ownerId = userEntity.getPid().toString();


        RegisterFileEntity registerFileEntity = fcRegisterFileCore.searchRegisterFile(type, ownerId);
        if (ChkUtil.isEmpty(registerFileEntity)){
            message.setCode(MsgCodeConstant.ERR_REGISTER_FILE_UPLOAD);
            message.setMessage("数据异常");
            return message;
        }
        registerFileEntity.setStatus("DEL");
        fcRegisterFileCore.updateRegisterFile(registerFileEntity);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);

        return message;
    }

    @Override
    public ResponseEntity<byte[]> donwloadEntrustment(String category) {
        BaseTemplateDictionaryEntity entity = fcRegisterFileCore.searchBaseTemplateDictionaryByCode(category);
        String fullPath = entity.getUrl();
        String fileName = entity.getName();
        return fcFileCore.fileDownLoad(fullPath,fileName);
    }

    @Override
    public ResponseEntity<byte[]> donwloadRegisterIDCard(Long pid) {
        RegisterFileEntity entity = fcRegisterFileCore.searchRegisterFileByPid(pid);
        String fullPath = entity.getFullPath();
        ResponseEntity<byte[]> responseEntity = fileDownLoad(fullPath, "image");
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, getContentType(fullPath));
        return new ResponseEntity(responseEntity.getBody(),headers,HttpStatus.OK);
    }

    @Override
    public BaseTemplateDictionaryEntity searchBaseTemplateDictionaryByCode(String category) {
        return fcRegisterFileCore.searchBaseTemplateDictionaryByCode(category);
    }

    /**
     * 文件下载
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

}
