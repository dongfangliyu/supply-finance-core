package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.constant.ReturnMsg;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditFrontGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditManagerListForm;
import cn.fintecher.supply.finance.loan.manager.common.model.*;
import cn.fintecher.supply.finance.loan.manager.common.response.AuditGuaranteeResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.*;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditOrderInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditCompanyCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditRegisterCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCGuaranteeManagerCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditOrderInfoService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditSuppSigningService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.GuaranteeManagerService;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessOrderCore;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessFileService;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessOrderService;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyEnterpriseCore;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseFinancialCore;
import cn.fintecher.supply.finance.loan.manager.service.register.feign.FCRegisterFileCore;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * @Author WuTianJuan
 * @Date Created in 20:23 2018/7/17
 */
@Service
public class GuaranteeManagerServiceImpl implements GuaranteeManagerService {
    @Autowired
    private FCGuaranteeManagerCore fcGuaranteeManagerCore;

    @Autowired
    private FCAuditRegisterCore fcAuditRegisterCore;

    @Autowired
    private AuditSuppSigningService auditSigningService;

    @Autowired
    private FCCompanyEnterpriseCore fcCompanyEnterpriseCore;

    @Autowired
    private BusinessOrderService businessOrderService;

    @Autowired
    private  FCRegisterFileCore fcRegisterFileCore;

    @Autowired
    private FCAuditCompanyCore fcAuditCompanyCore;

    @Autowired
    private BusinessOrderCore businessOrderCore;

    @Autowired
    private AuditOrderInfoCore auditOrderInfoCore;

    @Autowired
    private FCEnterpriseFinancialCore fcEnterpriseFinancialCore;

    @Override
    public Message searchGuaranteeList(AuditGuaranteeListForm auditGuaranteeListForm) {
        PageResponse<Object> pageResponse = new PageResponse<>();
        Message msg = new Message();
        try {
            if (!Strings.isNullOrEmpty(auditGuaranteeListForm.getStartTime())){
                // 转换日期
                auditGuaranteeListForm.setStartTime(DateUtil.TransformatStartTime(auditGuaranteeListForm.getStartTime()));
            }
            if (!Strings.isNullOrEmpty(auditGuaranteeListForm.getEndTime())){
                // 转换日期
                auditGuaranteeListForm.setEndTime(DateUtil.TransformatEndTime(auditGuaranteeListForm.getEndTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int total = 0;
        if(auditGuaranteeListForm.getPageNo() != 0){
            total = fcGuaranteeManagerCore.searchGuaranteeListCount(auditGuaranteeListForm);
        }
        List<AuditOrderInfoEntity> list = fcGuaranteeManagerCore.searchGuaranteeList(auditGuaranteeListForm);
        if(null!=list){
            pageResponse.setData(list);
            pageResponse.setTotal(total);
            pageResponse.setPageNo(auditGuaranteeListForm.getPageNo());
            pageResponse.setPageSize(auditGuaranteeListForm.getPageSize());
            msg.setMessage(pageResponse);
            msg.setCode(0);
        }else{
            msg.setCode(-1);
        }
        return msg;
    }

    @Override
    public Message submitResult(AuditManagerListForm auditManagerListForm) {
        Message msg = new Message();
        AuditOrderInfoEntity auditOrderInfoEntity = fcGuaranteeManagerCore.searchOrderInfoById(auditManagerListForm.getId());
        AuditResultEntity resultEntity = new AuditResultEntity();
        if(auditManagerListForm.getResult().equals("0")){
            auditOrderInfoEntity.setState("31");//擔保被拒絕
            auditOrderInfoEntity.setGuaranteeTime(new Date());
            fcGuaranteeManagerCore.updateGuaranteeInfo(auditOrderInfoEntity);
            resultEntity.setContent(auditManagerListForm.getContent());
            resultEntity.setObjectId(auditManagerListForm.getId());
            resultEntity.setCreateAt(new Date());
            resultEntity.setStatus(Constants.DATA_STATUS_NOL);
            resultEntity.setResult("0");//拒絕
            resultEntity.setType("9");

            AuditOrderInfoEntity orderInfoEntity = auditOrderInfoCore.queryOrderInfoByPid(auditManagerListForm.getId()).getMessage();
            BusinessOrderEntity businessOrderEntity = businessOrderCore.queryOrderByPid(orderInfoEntity.getOrderId().toString()).getMessage();
            if(null != businessOrderEntity){
                businessOrderEntity.setFinancingStatus("3");//申请不通过
                businessOrderCore.updateOrder(businessOrderEntity);
            }

        }
        if(auditManagerListForm.getResult().equals("1")){
            auditOrderInfoEntity.setState("32");//擔保方同意
            auditOrderInfoEntity.setGuarantee("1");
            auditOrderInfoEntity.setGuaranteeTime(new Date());
            fcGuaranteeManagerCore.updateGuaranteeInfo(auditOrderInfoEntity);
            resultEntity.setObjectId(auditManagerListForm.getId());
            resultEntity.setContent(auditManagerListForm.getContent());
            resultEntity.setCreateAt(new Date());
            resultEntity.setStatus("NOL");
            resultEntity.setResult("1");//同意
            resultEntity.setType("9");

            AuditOrderInfoEntity orderInfoEntity = auditOrderInfoCore.queryOrderInfoByPid(auditManagerListForm.getId()).getMessage();
            BusinessOrderEntity businessOrderEntity = businessOrderCore.queryOrderByPid(orderInfoEntity.getOrderId().toString()).getMessage();
            if(null != businessOrderEntity){
                businessOrderEntity.setFinancingStatus("2");//申请通过
                businessOrderCore.updateOrder(businessOrderEntity);
            }
        }
        fcAuditRegisterCore.saveAuditResultEntity(resultEntity);
        msg.setCode(0);
        msg.setType("audit-guarantee-service");
        return msg;
    }

    @Override
    public Message searchFrontDetail(String pid) {
        return auditSigningService.searchSigningDetail(Long.valueOf(pid));
    }

    @Override
    public Message submitFrontResult(AuditManagerListForm auditManagerListForm) {
        Message msg = new Message();
        AuditOrderInfoEntity auditOrderInfoEntity = fcGuaranteeManagerCore.searchOrderInfoById(auditManagerListForm.getId());
        if(auditManagerListForm.getResult().equals("0")){//拒绝
            auditOrderInfoEntity.setState("33");
            auditOrderInfoEntity.setGuaranteeTime(new Date());
            AuditOrderInfoEntity orderInfoEntity = auditOrderInfoCore.queryOrderInfoByPid(auditManagerListForm.getId()).getMessage();
            BusinessOrderEntity businessOrderEntity = businessOrderCore.queryOrderByPid(orderInfoEntity.getOrderId().toString()).getMessage();
            if(null != businessOrderEntity){
                businessOrderEntity.setFinancingStatus("3");//申请不通过
                businessOrderCore.updateOrder(businessOrderEntity);
            }
        }
        if(auditManagerListForm.getResult().equals("1")){//通过
            auditOrderInfoEntity.setState("40");
            auditOrderInfoEntity.setGuarantee("1");
            auditOrderInfoEntity.setGuaranteeTime(new Date());

            AuditOrderInfoEntity orderInfoEntity = auditOrderInfoCore.queryOrderInfoByPid(auditManagerListForm.getId()).getMessage();
            BusinessOrderEntity businessOrderEntity = businessOrderCore.queryOrderByPid(orderInfoEntity.getOrderId().toString()).getMessage();
            if(null != businessOrderEntity){
                businessOrderEntity.setFinancingStatus("2");//申请通过
                businessOrderCore.updateOrder(businessOrderEntity);
            }
        }
        fcGuaranteeManagerCore.updateGuaranteeInfo(auditOrderInfoEntity);
        msg.setCode(0);
        msg.setType("audit-guarantee-service");
        return msg;
    }

    @Override
    public Message searchAuditCompanyInfo(String id) {
        Message msg = new Message();
        Map result = new HashMap();
        BaseBankInfoEntity baseBankInfoEntity = fcCompanyEnterpriseCore.searchBaseBankInfo(Long.valueOf(id));
        CompanyEnterpriseEntity companyEnterpriseEntity = fcCompanyEnterpriseCore.searchCompanyEnterpriseEntity(Long.valueOf(id));
        CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity =fcCompanyEnterpriseCore.searchCompanyEnterpriseInfoEntity(Long.valueOf(id));
        List<CompanyOperationEntity> list = fcCompanyEnterpriseCore.searchCompanyOperation(Long.valueOf(id));
        List<RegisterFileEntity> registerFileList = fcRegisterFileCore.searchRegisterFileByOwnerId(id);
        List<CompanyFileEntity> companyFileEntities = fcEnterpriseFinancialCore.searchAccountingStatementInfo(Long.valueOf(id));
        if(null != companyFileEntities){
            result.put("companyFileEntities",companyFileEntities);
        }
        result.put("baseBankInfoEntity",baseBankInfoEntity);
        result.put("companyEnterpriseEntity",companyEnterpriseEntity);
        result.put("companyEnterpriseInfoEntity",companyEnterpriseInfoEntity);
        result.put("list",list);
        result.put("registerFileList",registerFileList);
        msg.setCode(0);
        msg.setMessage(result);
        msg.setType("audit-guarantee-service");
        return msg;
    }

    @Override
    public AuditCompanyEntity searchAuditCompanyId(String id) {
        return fcGuaranteeManagerCore.searchAuditCompanyId(id);
    }

    @Override
    public Message searchDetail(Long pid) {
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",ReturnMsg.SSUCCESS_CURRENCY);
        try{
            AuditGuaranteeResponse auditGuaranteeResponse = getAuditGuaranteeResponse(pid);
            if(auditGuaranteeResponse.getFlag()==-1){
                return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
            }
            msg.setMessage(auditGuaranteeResponse);

        }catch (Exception e) {
            e.printStackTrace();
            return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
        }
        return msg;
    }

    private AuditGuaranteeResponse getAuditGuaranteeResponse(Long pid){
        AuditGuaranteeResponse response = new AuditGuaranteeResponse();
        return response;
    }

    @Override
    public Message searchFrontGuaranteeList(AuditFrontGuaranteeListForm auditFrontGuaranteeListForm) {
        PageResponse<Object> pageResponse = new PageResponse<>();
        Message msg = new Message();
        String guarnteeCompany = auditFrontGuaranteeListForm.getGuarnteeCompany();
        String company = "河北融投担保集团中小微企业担保有限公司";
        if (!Strings.isNullOrEmpty(guarnteeCompany)){
            if (!company.contains(guarnteeCompany)){
                pageResponse.setTotal(0);
                pageResponse.setData(new ArrayList<>());
                pageResponse.setPageSize(auditFrontGuaranteeListForm.getPageSize());
                pageResponse.setPageNo(auditFrontGuaranteeListForm.getPageNo());
                msg.setCode(Constants.MSG_STATUS_SUCCESS);
                msg.setMessage(pageResponse);
                return msg;
            }
        }
        int total = 0;
        if(auditFrontGuaranteeListForm.getPageNo() != 0){
            total = fcGuaranteeManagerCore.searchFrontGuaranteeListCount(auditFrontGuaranteeListForm);
        }
        List<AuditOrderInfoEntity> list = fcGuaranteeManagerCore.searchFrontGuaranteeList(auditFrontGuaranteeListForm);
        if(null!=list){
            pageResponse.setData(list);
            pageResponse.setTotal(total);
            pageResponse.setPageSize(auditFrontGuaranteeListForm.getPageSize());
            pageResponse.setPageNo(auditFrontGuaranteeListForm.getPageNo());
            msg.setMessage(pageResponse);
            msg.setCode(0);
        }else{
            msg.setCode(-1);
        }
        return msg;
    }
}
