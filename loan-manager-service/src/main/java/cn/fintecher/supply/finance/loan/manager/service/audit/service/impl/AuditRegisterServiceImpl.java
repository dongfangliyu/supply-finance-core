package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterForm;
import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterResultForm;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.common.response.AuditCreditResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.AuditRegisterCompanyInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.CompanyPrimaryInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditCompanyCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditRegisterCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditRegisterService;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterCompanyCore;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterFileCore;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 9:02
 */
@Service
public class AuditRegisterServiceImpl implements AuditRegisterService {


    @Autowired
    private FCAuditRegisterCore fcAuditRegisterCore;

    @Autowired
    private FCRegisterFileCore fcRegisterFileCore;

    @Autowired
    private FCRegisterCompanyCore fcRegisterCompanyCore;

    @Autowired
    private FCAuditCompanyCore fcAuditCompanyCore;


    @Override
    public Message searchListAuditRegister(AuditRegisterForm auditRegisterForm) {
        Message message = new Message();
        PageResponse<Object> pageResponse = new PageResponse<>();
        try {
            if (!Strings.isNullOrEmpty(auditRegisterForm.getSubmitStartTime())){
                // 转换日期
                auditRegisterForm.setSubmitStartTime(DateUtil.TransformatStartTime(auditRegisterForm.getSubmitStartTime()));
            }
            if (!Strings.isNullOrEmpty(auditRegisterForm.getSubmitEndTime())){
                // 转换日期
                auditRegisterForm.setSubmitEndTime(DateUtil.TransformatEndTime(auditRegisterForm.getSubmitEndTime()));
            }
            if (!Strings.isNullOrEmpty(auditRegisterForm.getSuccessStartTime())){
                // 转换日期
                auditRegisterForm.setSuccessStartTime(DateUtil.TransformatStartTime(auditRegisterForm.getSuccessStartTime()));
            }
            if (!Strings.isNullOrEmpty(auditRegisterForm.getSuccessEndTime())){
                // 转换日期
                auditRegisterForm.setSuccessEndTime(DateUtil.TransformatEndTime(auditRegisterForm.getSuccessEndTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int total = 0;
        if(auditRegisterForm.getPageNo() != 0){
            total = fcAuditRegisterCore.searchAuditRegisterListCount(auditRegisterForm);
        }
        List<AuditRegisterEntity> list = fcAuditRegisterCore.searchListAuditRegister(auditRegisterForm);
        if (null != list){
            pageResponse.setData(list);
            pageResponse.setTotal(total);
            pageResponse.setPageNo(auditRegisterForm.getPageNo());
            pageResponse.setPageSize(auditRegisterForm.getPageSize());
            message.setMessage(pageResponse);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        }else {
            message.setCode(MsgCodeConstant.ERR_SEARCH_AUDIT_REGISTER);
        }
        return message;
    }

    @Override
    public Message searchAuditRegisterCompanyInfo(String pid) {
        Message message = new Message();
        message.setCode(MsgCodeConstant.ERR_SEARCH_AUDIT_REGISTER);
        AuditRegisterCompanyInfoResponse response = new AuditRegisterCompanyInfoResponse();
        AuditRegisterEntity auditRegisterEntity = fcAuditRegisterCore.searchAuditRegisterByPid(pid);
        if (null != auditRegisterEntity){
            RegisterUserInfoEntity userInfoEntity = fcRegisterCompanyCore.searchRegisterUserInfoByPid(auditRegisterEntity.getRegisterId());
            if(null != userInfoEntity && !Strings.isNullOrEmpty(userInfoEntity.getRegisterCode())){
                RegisterUserEntity registerUserEntity = fcRegisterCompanyCore.searchRegisteCompanyUserByCode(userInfoEntity.getRegisterCode());

                List<RegisterFileEntity> registerFileEntities = fcRegisterFileCore.searchRegisterFileByOwnerId(registerUserEntity.getPid().toString());

                if (!"0".equals(auditRegisterEntity.getState())){
                    AuditResultEntity auditResultEntity = fcAuditRegisterCore.searchAuditResultByObjectId(pid,Constants.OBJECT_REGISTER_NO);
                    response.setAuditResultEntity(auditResultEntity);
                }
                if ("10".equals(auditRegisterEntity.getState())){
                    String format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(auditRegisterEntity.getSuccessTime());
                    response.setSuccessTime(format1);
                }
                if ("20".equals(auditRegisterEntity.getState()) || "-10".equals(auditRegisterEntity.getState()) ){
                    String format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(auditRegisterEntity.getSuccessTime());
                    response.setSuccessTime(format1);
                    String format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(auditRegisterEntity.getSubmitTime());
                    response.setSubmitTime(format2);
                }
                response.setState(auditRegisterEntity.getState());
                response.setRegisterFileEntities(registerFileEntities);
                response.setRegisterUserInfoEntity(userInfoEntity);
            }
            message.setMessage(response);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        }
        return message;
    }

    @Override
    public Message submitAuditRegisterResult(AuditRegisterResultForm auditRegisterResultForm) {
        Message message = new Message();
        message.setCode(MsgCodeConstant.ERR_ADD_AUDIT_REGISTER_RESULT);
        AuditRegisterEntity auditRegisterEntity = fcAuditRegisterCore.searchAuditRegisterByPid(auditRegisterResultForm.getPid());
        AuditResultEntity resultEntity = fcAuditRegisterCore.searchAuditResultByObjectId(auditRegisterEntity.getPid().toString(),Constants.OBJECT_REGISTER_NO);
        if (auditRegisterEntity.getState().equals("-10") || auditRegisterEntity.getState().equals("20")){
            message.setMessage("操作异常!");
            return message;
        }


        if (null != resultEntity){
            resultEntity.setStatus("DEL");
            resultEntity.setUpdateAt(new Date());
            fcAuditRegisterCore.updateAuditResult(resultEntity);
        }

        if (null != auditRegisterEntity){
            auditRegisterEntity.setState("10");
            auditRegisterEntity.setSuccessTime(new Date());
            fcAuditRegisterCore.updateAuditRegister(auditRegisterEntity);
            AuditResultEntity auditResultEntity = new AuditResultEntity();
            auditResultEntity.setResult(auditRegisterResultForm.getResult());
            auditResultEntity.setContent(auditRegisterResultForm.getRemark());
            auditResultEntity.setObjectId(auditRegisterEntity.getPid().toString());
            auditResultEntity.setType("0");
            auditResultEntity.setObjectType("0");
            auditResultEntity.setCreateAt(new Date());
            auditResultEntity.setStatus("NOL");
            fcAuditRegisterCore.saveAuditResultEntity(auditResultEntity);
            RegisterUserInfoEntity userInfoEntity = fcRegisterCompanyCore.searchRegisterUserInfoByPid(auditRegisterEntity.getRegisterId());
            if (userInfoEntity.getAccountAmount() == null){
                int i = new Random().nextInt(9)+1;
                int j = new Random().nextInt(9)+1;
                int num = Integer.parseInt(i+""+j);
                userInfoEntity.setAccountAmount(new Long((long)num));
                userInfoEntity.setUpdateAt(new Date());
                fcRegisterCompanyCore.updateRegisteCompanyUserInfo(userInfoEntity);
            }
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        }

        return message;
    }

    @Override
    public Message searchCompanyInfo(String pid) {
        Message message = new Message();
        message.setCode(MsgCodeConstant.ERR_SEARCH_AUDIT_REGISTER);
        AuditRegisterEntity auditRegisterEntity = fcAuditRegisterCore.searchAuditRegisterByPid(pid);
        if (null != auditRegisterEntity){
            RegisterUserInfoEntity userInfoEntity = fcRegisterCompanyCore.searchRegisterUserInfoByPid(auditRegisterEntity.getRegisterId());
            if (null != userInfoEntity){
                CompanyPrimaryInfoResponse response = fcRegisterCompanyCore.searchRegisteCompanyPrimaryInfo(userInfoEntity.getRegisterCode());
                message.setMessage(response);
            }
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        }
        return message;
    }

    @Override
    public Message submitSendLink(String pid) {
        Message message = new Message();
        message.setCode(MsgCodeConstant.ERR_SUBMIT_SAND_LINK);
        AuditRegisterEntity auditRegisterEntity = fcAuditRegisterCore.searchAuditRegisterByPid(pid);
        AuditResultEntity auditResultEntity = fcAuditRegisterCore.searchAuditResultByObjectId(auditRegisterEntity.getPid().toString(), Constants.OBJECT_REGISTER_NO);
        if (null != auditRegisterEntity && null != auditResultEntity){
            RegisterUserInfoEntity userInfoEntity = fcRegisterCompanyCore.searchRegisterUserInfoByPid(auditRegisterEntity.getRegisterId());
            if (auditResultEntity.getResult().equals("0")){
                userInfoEntity.setState("-1");
            }else {
                userInfoEntity.setState("2");
            }
            userInfoEntity.setUpdateAt(new Date());
            userInfoEntity.setPaymentTime(new Date());
            fcRegisterCompanyCore.updateRegisteCompanyUserInfo(userInfoEntity);

            if (auditResultEntity.getResult().equals("0")){
                auditRegisterEntity.setState("-10");
            }else {
                auditRegisterEntity.setState("20");
            }
            auditRegisterEntity.setSubmitTime(new Date());
            auditRegisterEntity.setUpdateAt(new Date());
            fcAuditRegisterCore.updateAuditRegister(auditRegisterEntity);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        }
        return message;
    }

    @Override
    public Message searchAuditCreditStatus() {
        Message message = new Message();
        AuditCreditResponse response = new AuditCreditResponse();
        String state = "0";
        Long auditNum = fcAuditRegisterCore.searchListAuditRegisterCount(state);
        String auditStatus = "0";
        Integer i =(Integer) fcAuditCompanyCore.searchAuditCreditCount(auditStatus);
        response.setCreditNumber(Long.valueOf(i));
        response.setCertifiedNumber(auditNum);
        message.setMessage(response);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }
}
