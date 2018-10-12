package cn.fintecher.supply.finance.loan.manager.service.pro.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.ProContractForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProCategoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProContractEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.response.ProContractResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.common.redis.IRedisService;
import cn.fintecher.supply.finance.loan.manager.service.pro.feign.FCProContractCore;
import cn.fintecher.supply.finance.loan.manager.service.pro.service.ProContractService;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCFileCore;
import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/10 0010下午 12:02
 */
@Service
public class ProContractServiceImpl implements ProContractService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProContractServiceImpl.class);

    @Autowired
    private FCProContractCore fcProContractCore;

    @Autowired
    private FCFileCore fcFileCore;

    @Autowired
    private IRedisService redisService;

    @Override
    public Message searchListContract(ProContractForm proContractForm) {
        Message message = new Message();
        if(StringUtils.isNotBlank(proContractForm.getTimeStart())){
            proContractForm.setTimeStart(proContractForm.getTimeStart() + " 00:00:00");
        }
        if(StringUtils.isNotBlank(proContractForm.getTimeEnd())){
            proContractForm.setTimeEnd(proContractForm.getTimeEnd() + " 23:59:59");
        }
        PageResponse<Object> response = new PageResponse<>();
        int total = 0;
        if(proContractForm.getPageNo() != 0){
            total = fcProContractCore.searchListContractCount(proContractForm);
        }
        List<ProContractEntity> productList = fcProContractCore.searchListContract(proContractForm);
        if (null != productList){
            response.setTotal(total);
            response.setData(productList);
            response.setPageNo(proContractForm.getPageNo());
            response.setPageSize(proContractForm.getPageSize());
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            message.setMessage(response);
        }else {
            message.setCode(MsgCodeConstant.ERR_SEARCH_CONTRACT_LIST);
        }
        return message;
    }

    @Override
    public Message submitContract(ProContractEntity proContractEntity) {
        Message message = new Message();
        if (null == proContractEntity){
            message.setCode(MsgCodeConstant.ERR_ADD_CONTRACT);
            return message;
        }

        if (Strings.isNullOrEmpty(proContractEntity.getResourceCode())){
            message.setCode(MsgCodeConstant.ERR_ADD_CONTRACT);
            message.setMessage("数据异常!");
            return message;
        }

        if (Strings.isNullOrEmpty(proContractEntity.getCategory())){
            message.setCode(MsgCodeConstant.ERR_ADD_CONTRACT);
            message.setMessage("数据异常!");
            return message;
        }

//        ProCategoryEntity proCategoryEntity = fcProContractCore.searchCategoryByCategory(proContractEntity.getCategory());
        ProCategoryEntity proCategoryEntity = fcProContractCore.searchCategoryByPid(proContractEntity.getCategory());
        if (null != proCategoryEntity){
            proContractEntity.setCategoryName(proCategoryEntity.getName());
            proContractEntity.setCategory(proCategoryEntity.getCategory());
        }
        String contractNo = searchContractNo();
        proContractEntity.setContractNo(contractNo);
        proContractEntity.setCreateAt(new Date());
        proContractEntity.setStatus("NOL");
        fcProContractCore.submitContract(proContractEntity);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }

    @Override
    public Message searchContract(String pid) {
        Message message = new Message();
        ProContractResponse response = new ProContractResponse();
        ProContractEntity proContractEntity = fcProContractCore.searchContract(pid);
        if (null == proContractEntity){
            message.setCode(MsgCodeConstant.ERR_SEARCH_CONTRACT_DETAIL);
            return message;
        }
        if (Strings.isNullOrEmpty(proContractEntity.getResourceCode())){
            message.setCode(MsgCodeConstant.ERR_SEARCH_CONTRACT_DETAIL);
            return message;
        }
        List<ProFileEntity> proFileEntities = fcProContractCore.searchProFile(proContractEntity.getResourceCode());
        response.setProContractEntity(proContractEntity);
        response.setProFileEntityList(proFileEntities);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        message.setMessage(response);
        return message;
    }

    @Override
    public Message deleteContract(String pid) {
        Message message = new Message();
        ProContractEntity proContractEntity = fcProContractCore.searchContract(pid);
        if (null == proContractEntity){
            message.setCode(MsgCodeConstant.ERR_DELETE_CONTRACT);
            message.setMessage("数据异常!");
            return message;
        }
        proContractEntity.setStatus("DEL");
        proContractEntity.setUpdateAt(new Date());
        fcProContractCore.updateProContractEntity(proContractEntity);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }

    @Override
    public Message updateContract(ProContractEntity proContractEntity) {
        Message message = new Message();
        message.setCode(MsgCodeConstant.ERR_SEARCH_CONTRACT_DETAIL);
        // 根据id查询合同信息
        if (null == proContractEntity){
            return message;
        }

        if (null != proContractEntity.getPid()){
            ProContractEntity entity = fcProContractCore.searchContract(proContractEntity.getPid().toString());
            if (proContractEntity.getResourceCode().equals(entity.getResourceCode())){

                proContractEntity.setUpdateAt(new Date());
                proContractEntity.setCategory(null);
                fcProContractCore.updateProContractEntity(proContractEntity);
                message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
                return message;
            }
            // 满足切换了registerCode  标记删除合同
            entity.setStatus("DEL");
            fcProContractCore.updateProContractEntity(entity);
            // 新建一条合同数据 用来保存当前提交保存的合同信息
            ProContractEntity contractEntity = new ProContractEntity();
            contractEntity.setPid(proContractEntity.getPid());
            contractEntity.setCreateAt(new Date());
            contractEntity.setStatus("NOL");
            ProCategoryEntity proCategoryEntity = fcProContractCore.searchCategoryByPid(proContractEntity.getCategory());
            if (null != proCategoryEntity){
                contractEntity.setCategoryName(proCategoryEntity.getName());
                contractEntity.setCategory(proCategoryEntity.getCategory());
            }
            String s = searchContractNo();
            contractEntity.setContractNo(s);
            contractEntity.setRemark(proContractEntity.getRemark());
            contractEntity.setResourceCode(proContractEntity.getResourceCode());
            contractEntity.setState(proContractEntity.getState());
            fcProContractCore.updateProContractEntity(contractEntity);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        }

        return message;

    }

    @Override
    public Message disableContract(String pid) {
        Message message = new Message();
        ProContractEntity proContractEntity = fcProContractCore.searchContract(pid);
        if (null == proContractEntity){
            message.setCode(MsgCodeConstant.ERR_DELETE_CONTRACT);
            message.setMessage("数据异常!");
            return message;
        }
        if ("1".equals(proContractEntity.getState())){
            proContractEntity.setState("0");
        }else{
            proContractEntity.setState("1");
        }
        proContractEntity.setUpdateAt(new Date());
        fcProContractCore.updateProContractEntity(proContractEntity);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }

    @Override
    public Message searchProductListContract(String pid) {
        Message message = new Message();
        // 父id
        List<ProCategoryEntity> proCategoryEntities = fcProContractCore.searchCategoryListByParentId(pid);
        if (null == proCategoryEntities){
            message.setCode(MsgCodeConstant.ERR_SEARCH_CONTRACT_LIST);
            message.setMessage("数据异常!");
            return message;
        }
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        message.setMessage(proCategoryEntities);
        return message;
    }

    @Override
    public ResponseEntity<byte[]> downContract(String pid) {
        ProFileEntity proFileEntity = fcProContractCore.searchProFileById(pid);
        if (null != proFileEntity && !Strings.isNullOrEmpty(proFileEntity.getFullPath()) && !Strings.isNullOrEmpty(proFileEntity.getFileName())){
            return fcFileCore.fileDownLoad(proFileEntity.getFullPath(),proFileEntity.getFileName());
        }
        return null;
    }

    @Override
    public Message uploadContractDoc(MultipartFile file, String type, String resourceCode) {

        Message message = new Message();
        if (Strings.isNullOrEmpty(resourceCode)){
            message.setCode(MsgCodeConstant.ERR_UPLOAD_PRO_FILE);
            message.setMessage("数据异常!");
            return message;
        }

        ResponseEntity<Message> entity = fcFileCore.fileUpload(file);
        message = entity.getBody();
        HashMap<String, String> params = (HashMap<String, String>) message.getMessage();

        if (message.getCode() == 0){
            ProFileEntity proFileEntity = new ProFileEntity();
            proFileEntity.setFileName(file.getOriginalFilename());
            proFileEntity.setCategory(type);
//            proFileEntity.setFileSuffix(file.getContentType());
            proFileEntity.setOwnerId(resourceCode);
            proFileEntity.setPath(params.get("path"));
            proFileEntity.setFullPath(params.get("fullPath"));
            proFileEntity.setFileGroup(params.get("group"));
            proFileEntity.setStatus("NOL");
            proFileEntity.setCreateAt(Calendar.getInstance().getTime());
            proFileEntity.setUpdateAt(Calendar.getInstance().getTime());
            try {
                message = fcProContractCore.saveProFile(proFileEntity);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("合同保存失败：" + params);
            }
        }
        return message;
    }

    @Override
    public Message deleteContractFile(String type, String resourceCode) {
        Message message = new Message();
        if (Strings.isNullOrEmpty(resourceCode)){
            message.setCode(MsgCodeConstant.ERR_REGISTER_FILE_UPLOAD);
            message.setMessage("数据异常!");
            return message;
        }

        ProFileEntity proFileEntity = fcProContractCore.searchContractFile(type, resourceCode);
        proFileEntity.setStatus("DEL");
        proFileEntity.setUpdateAt(new Date());
        fcProContractCore.updateContractFile(proFileEntity);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);

        return message;
    }

    @Override
    public Message searchContractListByCategory(String category) {
        Message<Object> message = new Message<>();
        ProContractForm proContractForm = new ProContractForm();
        proContractForm.setCategory(category);
        proContractForm.setState("1");
        List<ProContractEntity> list = fcProContractCore.searchListContract(proContractForm);
        message.setMessage(list);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }


    public String searchContractNo(){
        String contractNo = redisService.get("countContract");
        if (Strings.isNullOrEmpty(contractNo)){
            long aLong = fcProContractCore.searchCountOfContract();
            long newCount = aLong + 1l;
            contractNo = String.valueOf(newCount);
            redisService.set("countContract",contractNo);
        }else {
            long aLong = Long.valueOf(contractNo);
            long newLong = aLong + 1l;
            contractNo = String.valueOf(newLong);
            redisService.set("countContract",contractNo);
        }
        if (contractNo.length() == 1){
            contractNo = "HTWB00000" + contractNo;
        } else if(contractNo.length() == 2){
            contractNo = "HTWB0000" + contractNo;
        } else if(contractNo.length() == 3){
            contractNo = "HTWB000" + contractNo;
        } else if(contractNo.length() == 4){
            contractNo = "HTWB00" + contractNo;
        } else if(contractNo.length() == 5) {
            contractNo = "HTWB0" + contractNo;
        } else if(contractNo.length() == 6) {
            contractNo = "HTWB" + contractNo;
        }
        return contractNo;
    }


    @Override
    public Message searchContractListByCompanyId(Long companyId) {
        List<ProContractEntity> list = fcProContractCore.searchContractListByCompanyId(companyId);
        return new Message(MessageType.MSG_SUCCESS,"contarct_service",list);
    }
}
