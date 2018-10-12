package cn.fintecher.supply.finance.loan.manager.service.credit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.CompanyCreditInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.form.TableValidateForm;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseCreditCore;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseFinancialCore;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCFinancialCore;
import cn.fintecher.supply.finance.loan.manager.service.credit.service.EnterpriseFinancialService;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterFileCore;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author WuTianJuan
 * @Date Created in 10:49 2018/6/21
 */
@Service
public class EnterpriseFinancialServiceImpl implements EnterpriseFinancialService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnterpriseFinancialServiceImpl.class);

    @Autowired
    private FCEnterpriseFinancialCore enterpriseFinancialCore;

    @Autowired
    private FCFinancialCore financialCore;

    @Autowired
    private TableValidateServiceImpl tableValidateService;

    @Autowired
    private EnterpriseCreditServiceImpl enterpriseCreditService;

    @Autowired
    private FCRegisterFileCore fcRegisterFileCore;

    @Autowired
    private FCEnterpriseCreditCore fcEnterpriseCreditCore;

    public Message uploadCompanyCreditDoc(MultipartFile file, String type,String year,Long pid) {
        CompanyCreditInfoForm form = new CompanyCreditInfoForm();
        form.setPid(pid);
        form.setType(type);
        form.setYear(year);
        CompanyFileEntity fileEntity =  enterpriseFinancialCore.searchAllFileInfo(pid,type,year);
        if(fileEntity != null){
            Message msg = new Message();
            msg.setCode(0);
            msg.setMessage("文件请删除后在重新上传");
            return msg;
        }
       ResponseEntity<Message> entity = financialCore.fileUpload(file);
       Map resultParams = new HashMap();
       Message message = entity.getBody();
       HashMap<String, String> params = (HashMap<String, String>) message.getMessage();
       if (message.getCode() == 0){
            CompanyFileEntity companyFileEntity = new CompanyFileEntity();
            companyFileEntity.setOwnerId(form.getPid()+"");
            companyFileEntity.setFileName(file.getOriginalFilename());
            companyFileEntity.setCategory(type);
            companyFileEntity.setFileSuffix(file.getContentType());
            companyFileEntity.setPath(params.get("path"));
            companyFileEntity.setFullPath(params.get("fullPath"));
            companyFileEntity.setGroup(params.get("group"));
            companyFileEntity.setStatus(Constants.DATA_STATUS_NOL);
            companyFileEntity.setCreateAt(Calendar.getInstance().getTime());
            companyFileEntity.setUpdateAt(Calendar.getInstance().getTime());
            companyFileEntity.setYear(year);
            try {
                Message messageFileInsert = enterpriseFinancialCore.insert(companyFileEntity);
                if(messageFileInsert.getCode() == 0){
                    String fileName = companyFileEntity.getFileName();
                    resultParams.put("fileName",fileName);
                    message.setMessage(resultParams);
                }
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("文件信息保存失败：" + params);
            }
        }
       return message;
    }

    public ResponseEntity<byte[]> donwloadCompanyCreditDoc(Long pid,String type,String year) {
        HttpHeaders headers = new HttpHeaders();
        CompanyFileEntity entity = enterpriseFinancialCore.searchAllFileInfo(pid,type,year);
        String fullPath = entity.getFullPath();
        ResponseEntity<byte[]> responseEntity = fileDownLoad(fullPath, "image");
        headers.set(HttpHeaders.CONTENT_TYPE, getContentType(fullPath));
        return new ResponseEntity(responseEntity.getBody(),headers,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> donwloadCompanyCreditDocByFid(Long pid) {
        HttpHeaders headers = new HttpHeaders();
        CompanyFileEntity entity = enterpriseFinancialCore.searchAllFileByFid(pid);
        String fullPath = entity.getFullPath();
        ResponseEntity<byte[]> responseEntity = fileDownLoad(fullPath, "image");
        headers.set(HttpHeaders.CONTENT_TYPE, getContentType(fullPath));
        return new ResponseEntity(responseEntity.getBody(),headers,HttpStatus.OK);
    }

    /**
     * 文件下载
     * @param fullPath  文件上传返回的文件保存完整地址
     * @param fileName  文件名称
     * @return
     */
    public ResponseEntity<byte[]> fileDownLoad(String fullPath, String fileName) {
        return financialCore.fileDownLoad(fullPath, fileName);
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


    public Message submitAccountingStatementInfo(Long pid, String code) {
        Message msg = new Message();
        Message message = enterpriseCreditService.searchAccountingStatementTime();
        List<String> years = (List)message.getMessage();
        TableValidateForm validateForm = new TableValidateForm();
        validateForm.setPid(pid);
        validateForm.setYears(years);
        Message messag = tableValidateService.fileTable(validateForm);
         if(messag.getCode()==-1){
             return messag;
         }

        if(messag.getCode()==0){
            CompanyEnterpriseEntity entity = fcEnterpriseCreditCore.searchCompanyInfo(pid);
            String status = entity.getCreditStatus().toString();
            if(!(status.equals("30")) && !(status.equals("20"))){
                enterpriseCreditService.startCompanyCredit(pid,code);
                msg.setCode(0);
                msg.setType("enterprise-financial-service");
            }
            msg.setCode(0);
        }
        return msg;
    }

    public Message submitCompanyCreditImageInfo(Long pid,String code) {
        Message msg = new Message();
        Message message = enterpriseCreditService.searchAccountingStatementTime();
        List<String> years = (List) message.getMessage();
        TableValidateForm validateForm = new TableValidateForm();
        validateForm.setPid(pid);
        validateForm.setYears(years);
        Message messag = tableValidateService.fileTableValidate(validateForm);
        if(messag.getCode()==-1){
            return messag;
        }
        if(messag.getCode()==0){
            CompanyEnterpriseEntity entity = fcEnterpriseCreditCore.searchCompanyInfo(pid);
            String status = entity.getCreditStatus().toString();
            if(!(status.equals("30"))){
                enterpriseCreditService.startCompanyCredit(pid,code);
                msg.setType("enterprise-financial-service");
                msg.setCode(0);
            }
            msg.setCode(0);
        }
        return msg;
    }

    public List<CompanyFileEntity> searchAccountingStatementInfo(Long pid) {
        return  enterpriseFinancialCore.searchAccountingStatementInfo(pid);
    }

    public Message deleteCompanyCreditDoc(Long pid){
        return enterpriseFinancialCore.deleteCompanyCreditDoc(pid);
    }

    @Override
    public List<RegisterFileEntity> searchRegisterFile(Long pid) {
        return fcRegisterFileCore.searchRegisterFileByOwnerId(pid+"");
    }

    /***
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 线下为仓单质押企业文件接口
     */

    @Override
    public Message deletePledgeEnterpriseFile(Long pid) {
        Message<Object> message = new Message<>();
        CompanyFileEntity companyFileEntity = enterpriseFinancialCore.searchAllFileByFid(pid);
        if (ChkUtil.isEmpty(companyFileEntity)){
            message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
            message.setMessage("数据异常!");
            return message;
        }
        companyFileEntity.setStatus(Constants.DATA_STATUS_DEL);
        enterpriseFinancialCore.updateCompanyFile(companyFileEntity);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }

    @Override
    public Message uploadPledgeEnterpriseFile(MultipartFile file, String type, Long enterpriseId) {
        Message message = new Message();
        if (enterpriseId == null || enterpriseId < 0){
            return new Message(MsgCodeConstant.ERR_MSG_DEFAULT,"","登陆失效,请重新登陆");
        }
        // 查询是否有同类型的数据
        CompanyFileEntity companyFileEntity1 = enterpriseFinancialCore.searchAllFileInfo(enterpriseId, type, null);
        if(!ChkUtil.isEmpty(companyFileEntity1)){
            companyFileEntity1.setStatus(Constants.DATA_STATUS_DEL);
            enterpriseFinancialCore.updateCompanyFile(companyFileEntity1);
        }
        ResponseEntity<Message> entity = financialCore.fileUpload(file);
        Map resultParams = new HashMap();
        message = entity.getBody();
        HashMap<String, String> params = (HashMap<String, String>) message.getMessage();
        if (message.getCode() == 0){
            // 判断是否存在
            CompanyFileEntity companyFileEntity = new CompanyFileEntity();
            companyFileEntity.setOwnerId(enterpriseId.toString());
            companyFileEntity.setFileName(file.getOriginalFilename());
            companyFileEntity.setCategory(type);
            companyFileEntity.setFileSuffix(file.getContentType());
            companyFileEntity.setPath(params.get("path"));
            companyFileEntity.setFullPath(params.get("fullPath"));
            companyFileEntity.setGroup(params.get("group"));
            companyFileEntity.setStatus(Constants.DATA_STATUS_NOL);
            companyFileEntity.setCreateAt(Calendar.getInstance().getTime());
            companyFileEntity.setUpdateAt(Calendar.getInstance().getTime());
            try {
                Message<CompanyFileEntity> result = enterpriseFinancialCore.insert(companyFileEntity);
                if(result.getCode() == 0){
                    String fileName = companyFileEntity.getFileName();
                    CompanyFileEntity fileEntity = result.getMessage();
                    if (!ChkUtil.isEmpty(fileEntity)){
                        resultParams.put("pid",fileEntity.getPid());
                    }
                    resultParams.put("fileName",fileName);
                    message.setMessage(resultParams);
                }else {
                    message.setMessage("上传失败,请重新上传!");
                }
                message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("文件信息保存失败：" + params);
            }
        }
        return message;
    }
}
