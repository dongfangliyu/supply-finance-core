package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditSigningDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditResultEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditOrderInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditFileCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditFinanceCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditRegisterCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditLoanService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditSuppSigningService;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessOrderCore;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/19 0019上午 10:55
 */
@Service
public class AuditLoanServiceImpl implements AuditLoanService {

    @Autowired
    private FCAuditFinanceCore fcAuditFinanceCore;

    @Autowired
    private AuditOrderInfoCore auditOrderInfoCore;

    @Autowired
    private BusinessOrderCore businessOrderCore;

    @Autowired
    private FCAuditRegisterCore fcAuditRegisterCore;

    @Autowired
    private AuditSuppSigningService auditSigningService;

    @Autowired
    private FCAuditFileCore fcAuditFileCore;

    @Override
    public Message searchLoanList(AuditFinanceForm auditFinanceForm) {
        Message message = new Message();
        PageResponse<Object> pageResponse = new PageResponse<>();
        try {
            if (!Strings.isNullOrEmpty(auditFinanceForm.getStartTime())){
                // 转换日期
                auditFinanceForm.setStartTime(DateUtil.TransformatStartTime(auditFinanceForm.getStartTime()));
            }
            if (!Strings.isNullOrEmpty(auditFinanceForm.getEndTime())){
                // 转换日期
                auditFinanceForm.setEndTime(DateUtil.TransformatEndTime(auditFinanceForm.getEndTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int total = 0;
        if(auditFinanceForm.getPageNo() != 0){
            total = fcAuditFinanceCore.searchLoanListCount(auditFinanceForm);
        }
        List<AuditOrderInfoEntity> list = fcAuditFinanceCore.searchLoanList(auditFinanceForm);
        if (null != list){
            pageResponse.setData(list);
            pageResponse.setTotal(total);
            pageResponse.setPageSize(auditFinanceForm.getPageSize());
            pageResponse.setPageNo(auditFinanceForm.getPageNo());
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            message.setMessage(pageResponse);
        }else {
            message.setCode(MsgCodeConstant.ERR_SEARCH_AUDIT_REGISTER);
        }
        return message;
    }

    @Override
    public Message searchLoanDetail(String pid) {
        Message<Object> message = new Message<>();
        message = auditSigningService.searchSigningDetail(Long.parseLong(pid));
        AuditSigningDetailResponse auditSigningDetailResponse = JSONUtil.toBean(message.getMessage(), AuditSigningDetailResponse.class);
        List<AuditFileEntity> list = fcAuditFileCore.searchAllAuditFileByOwnerIdAndCategory(pid, "loanVoucher");
        if (list.size() != 0){
            AuditFileEntity auditFileEntity = list.get(0);
            auditSigningDetailResponse.setAuditFileEntity(auditFileEntity);
        }
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        message.setMessage(auditSigningDetailResponse);
        return message;
    }

    @Override
    public Message submitLoanPass(String pid, String loanTime, String userName) {

        Message<Object> message = new Message<>();
        Message<AuditOrderInfoEntity> infoEntityMessage = auditOrderInfoCore.queryOrderInfoByPid(pid);
        if (infoEntityMessage.getCode() == MsgCodeConstant.ERR_MSG_SUCCESS){
            AuditOrderInfoEntity auditOrderInfoEntity = infoEntityMessage.getMessage();
            if (null != auditOrderInfoEntity){
                Message<BusinessOrderEntity> businessOrderEntityMessage = businessOrderCore.queryOrderByPid(auditOrderInfoEntity.getOrderId().toString());
                if (businessOrderEntityMessage.getCode() == MsgCodeConstant.ERR_MSG_SUCCESS){
                    BusinessOrderEntity businessOrderEntity = businessOrderEntityMessage.getMessage();
                    if (null != businessOrderEntity){
                        auditOrderInfoEntity.setUpdateAt(new Date());
                        auditOrderInfoEntity.setState("70");
                        auditOrderInfoEntity.setFinancialReviewTime(new Date());
                        auditOrderInfoEntity.setReviewAgreeTime(new Date());
                        auditOrderInfoEntity.setOverdueType(Constants.OVERDUE_SETTLE_NO);

                        loanTime = loanTime.replace("Z", " UTC");//注意是空格+UTC
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
                        try {
                            auditOrderInfoEntity.setLoanTime(format.parse(loanTime));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        auditOrderInfoEntity.setRemind(Constants.REMIND_NOSET);
                        Integer approvalTerm = auditOrderInfoEntity.getApprovalTerm();
                        if (null != loanTime && approvalTerm>0){
                            try {
                                String formatLoanTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(format.parse(loanTime));
                                String lastTime = DateUtil.getLastTime(formatLoanTime, 1, approvalTerm, null);
                                Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(lastTime);
                                auditOrderInfoEntity.setShouldTime(parse);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        auditOrderInfoCore.updateOrderInfo(auditOrderInfoEntity);
                        businessOrderEntity.setProcessStatus("70");
                        businessOrderEntity.setUpdateAt(new Date());
                        businessOrderCore.updateOrder(businessOrderEntity);

                        AuditResultEntity auditResultEntity = new AuditResultEntity();
                        auditResultEntity.setStatus(Constants.DATA_STATUS_NOL);
                        auditResultEntity.setResult(Constants.ISDELETE_YES);
                        auditResultEntity.setObjectId(pid);
                        auditResultEntity.setObjectType("21");
                        auditResultEntity.setType("21");
                        auditResultEntity.setCreateAt(new Date());
                        auditResultEntity.setCreateBy(userName);
                        fcAuditRegisterCore.saveAuditResultEntity(auditResultEntity);
                        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
                        return message;
                    }
                }
            }
        }
        message.setCode(MsgCodeConstant.ERR_AUDIT_ORDER_INFO);
        return message;
    }

    @Override
    public Message submitLoanFail(String pid, String userName) {

        Message<Object> message = new Message<>();
        Message<AuditOrderInfoEntity> infoEntityMessage = auditOrderInfoCore.queryOrderInfoByPid(pid);
        if (infoEntityMessage.getCode() == MsgCodeConstant.ERR_MSG_SUCCESS){
            AuditOrderInfoEntity auditOrderInfoEntity = infoEntityMessage.getMessage();
            if (null != auditOrderInfoEntity){
                Message<BusinessOrderEntity> businessOrderEntityMessage = businessOrderCore.queryOrderByPid(auditOrderInfoEntity.getOrderId().toString());
                if (businessOrderEntityMessage.getCode() == MsgCodeConstant.ERR_MSG_SUCCESS){
                    BusinessOrderEntity businessOrderEntity = businessOrderEntityMessage.getMessage();
                    if (null != businessOrderEntity){
                        auditOrderInfoEntity.setState("61");
                        auditOrderInfoEntity.setLoanTime(new Date());
                        auditOrderInfoEntity.setFinancialReviewTime(new Date());
                        auditOrderInfoEntity.setUpdateAt(new Date());
                        auditOrderInfoCore.updateOrderInfo(auditOrderInfoEntity);
                        businessOrderEntity.setProcessStatus("61");
                        businessOrderEntity.setFinancingStatus("4");
                        businessOrderEntity.setUpdateAt(new Date());
                        businessOrderCore.updateOrder(businessOrderEntity);

                        AuditResultEntity auditResultEntity = new AuditResultEntity();
                        auditResultEntity.setStatus(Constants.DATA_STATUS_NOL);
                        auditResultEntity.setResult(Constants.ISDELETE_YES);
                        auditResultEntity.setObjectId(pid);
                        auditResultEntity.setObjectType("21");
                        auditResultEntity.setType("21");
                        auditResultEntity.setCreateAt(new Date());
                        auditResultEntity.setCreateBy(userName);
                        fcAuditRegisterCore.saveAuditResultEntity(auditResultEntity);
                        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
                        return message;
                    }
                }
            }
        }
        message.setCode(MsgCodeConstant.ERR_AUDIT_ORDER_INFO);
        return message;
    }
}
