package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskRemindEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditTaskRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditRemindResponse;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditOrderInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditRemindCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditRemindService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/23 0023上午 10:37
 */
@Service
public class AuditRemindServiceImpl implements AuditRemindService{

    @Autowired
    private FCAuditRemindCore fcAuditRemindCore;

    @Autowired
    private AuditOrderInfoCore auditOrderInfoCore;


    @Override
    public Message searchRemindList(AuditRemindForm auditRemindForm) {
        Message message = new Message();
        PageResponse<Object> pageResponse = new PageResponse<>();
        try {
            if (!Strings.isNullOrEmpty(auditRemindForm.getStartTime())){
                // 转换日期
                auditRemindForm.setStartTime(DateUtil.TransformatStartTime(auditRemindForm.getStartTime()));
            }
            if (!Strings.isNullOrEmpty(auditRemindForm.getEndTime())){
                // 转换日期
                auditRemindForm.setEndTime(DateUtil.TransformatEndTime(auditRemindForm.getEndTime()));
            }
            if (!Strings.isNullOrEmpty(auditRemindForm.getShouldStartTime())){
                // 转换日期
                auditRemindForm.setShouldStartTime(DateUtil.TransformatStartTime(auditRemindForm.getShouldStartTime()));
            }
            if (!Strings.isNullOrEmpty(auditRemindForm.getShouldEndTime())){
                // 转换日期
                auditRemindForm.setShouldEndTime(DateUtil.TransformatEndTime(auditRemindForm.getShouldEndTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int total = 0;
        if(auditRemindForm.getPageNo() != 0){
            total = auditOrderInfoCore.searchRemindListCount(auditRemindForm);
        }
        List<AuditOrderInfoEntity> list = auditOrderInfoCore.searchRemindList(auditRemindForm);
        if (null != list){
            pageResponse.setData(list);
            pageResponse.setPageNo(auditRemindForm.getPageNo());
            pageResponse.setPageSize(auditRemindForm.getPageSize());
            pageResponse.setTotal(total);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            message.setMessage(pageResponse);
        }else {
            message.setCode(MsgCodeConstant.ERR_SEARCH_AUDIT_REGISTER);
        }
        return message;
    }

    @Override
    public Message searchRemindDetail(String pid) {
        Message<AuditRemindResponse> message = new Message<>();
        Message<AuditOrderInfoEntity> auditOrderInfoEntityMessage = auditOrderInfoCore.queryOrderInfoByPid(pid);
        AuditRemindResponse auditRemindResponse = new AuditRemindResponse();
        if (null != auditOrderInfoEntityMessage){
            AuditOrderInfoEntity orderInfoEntity = auditOrderInfoEntityMessage.getMessage();
            if (null != orderInfoEntity){
                auditRemindResponse.setPid(orderInfoEntity.getPid());
                auditRemindResponse.setEnterpriseName(orderInfoEntity.getEnterpriseName());
                auditRemindResponse.setSupplierName(orderInfoEntity.getSupplierName());
                auditRemindResponse.setCertificateAmount(orderInfoEntity.getCertificateAmount());
                // 利息计算  :  利息=放款金额*利率/360*期限
                Double approvalAmount = Double.parseDouble(orderInfoEntity.getApprovalAmount());
                Integer interestRate = orderInfoEntity.getInterestRate();
                Integer approvalTerm = orderInfoEntity.getApprovalTerm();
                Integer serviceFee = orderInfoEntity.getServiceFee();
                Date loanTime = orderInfoEntity.getLoanTime();
                Date shouldTime = orderInfoEntity.getShouldTime();
                if(approvalAmount>0&&interestRate>0&&approvalTerm>0){
                    String result = new BigDecimal(approvalAmount)
                            .multiply(new BigDecimal(interestRate))
                            .divide(new BigDecimal(36000),20,BigDecimal.ROUND_HALF_UP)
                            .multiply(new BigDecimal(approvalTerm)).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
                    auditRemindResponse.setInterestRatePrice(result);
                }
                // 平台服务费计算  :  平台服务费=放款金额*服务费率/360*期限
                if (approvalAmount>0&&serviceFee>0&&approvalTerm>0){
                    String result1 = new BigDecimal(approvalAmount)
                            .multiply(new BigDecimal(serviceFee))
                            .divide(new BigDecimal(36000),20,BigDecimal.ROUND_HALF_UP)
                            .multiply(new BigDecimal(approvalTerm)).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
                    auditRemindResponse.setServiceFeePrice(result1);
                }
                if (null != loanTime && null != shouldTime){
                    String loan = new SimpleDateFormat("yyyy-MM-dd").format(loanTime);
                    String should = new SimpleDateFormat("yyyy-MM-dd").format(shouldTime);
                    auditRemindResponse.setLoanCycle(loan+"-"+should);
                }

                auditRemindResponse.setApprovalAmount(approvalAmount+"");
                auditRemindResponse.setApprovalTerm(approvalTerm);
                auditRemindResponse.setInterestRate(interestRate);
                auditRemindResponse.setServiceFee(serviceFee);
            }
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            message.setMessage(auditRemindResponse);
            return message;
        }
        message.setCode(MsgCodeConstant.ERR_SEARCH_CONTRACT_DETAIL);
        return message;
    }

    @Override
    public Message searchRemind(AuditTaskRemindForm auditTaskRemindForm) {
        Message<Object> message = new Message<>();
        PageResponse<Object> pageResponse = new PageResponse<>();
        int total = 0;
        if(auditTaskRemindForm.getPageNo() != 0){
            total = fcAuditRemindCore.searchRemindCount(auditTaskRemindForm);
        }
        List<AuditTaskRemindEntity> list = fcAuditRemindCore.searchRemind(auditTaskRemindForm);
        if (null != list){
            pageResponse.setData(list);
            pageResponse.setPageSize(auditTaskRemindForm.getPageSize());
            pageResponse.setTotal(total);
            pageResponse.setPageNo(auditTaskRemindForm.getPageNo());
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            message.setMessage(pageResponse);
        }else {
            message.setCode(MsgCodeConstant.ERR_SEARCH_AUDIT_REGISTER);
        }
        return message;
    }

    @Override
    public Message submitRemind(AuditTaskRemindForm auditTaskRemindForm) {
        Message<Object> message = new Message<>();
        if (null != auditTaskRemindForm && auditTaskRemindForm.getOrderInfoId() > 0){
            Message<AuditOrderInfoEntity> auditOrderInfoEntityMessage = auditOrderInfoCore.queryOrderInfoByPid(auditTaskRemindForm.getOrderInfoId().toString());
            if (null != auditOrderInfoEntityMessage){
                AuditOrderInfoEntity orderInfoEntity = auditOrderInfoEntityMessage.getMessage();
                if (auditTaskRemindForm.getAdvanceDay() > orderInfoEntity.getApprovalTerm()){
                    message.setCode(MsgCodeConstant.ERR_SEARCH_CONTRACT_DETAIL);
                    message.setMessage("提醒日期大于放款周期,请重新输入!");
                    return message;
                }
                if (null != orderInfoEntity){
                    Date shouldTime = orderInfoEntity.getShouldTime();
                    if (null != shouldTime){
                        AuditTaskRemindEntity auditTaskRemindEntity = new AuditTaskRemindEntity();
                        if(auditTaskRemindForm.getPid() != null){
                            Message<AuditTaskRemindEntity> auditTaskRemindEntityMessage = fcAuditRemindCore.queryTaskRemindByPid(auditTaskRemindForm.getPid().toString());
                            if (null != auditTaskRemindEntityMessage){
                                auditTaskRemindEntity = auditTaskRemindEntityMessage.getMessage();
                            }
                        }
                        auditTaskRemindEntity.setAdvanceDay(auditTaskRemindForm.getAdvanceDay());
                        auditTaskRemindEntity.setStatus(Constants.DATA_STATUS_NOL);
                        auditTaskRemindEntity.setState("0");
                        String before = DateUtil.getBefore(shouldTime, auditTaskRemindForm.getAdvanceDay().intValue(), null);
                        try {
                            Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(before);
                            auditTaskRemindEntity.setRemindDate(parse);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        auditTaskRemindEntity.setRemindTime(Constants.REMIND_TIME);
                        auditTaskRemindEntity.setRemindForm(Constants.REMIND_FORM);
                        auditTaskRemindEntity.setCreateAt(new Date());
                        auditTaskRemindEntity.setOrderInfoId(auditTaskRemindForm.getOrderInfoId());
                        if(null != auditTaskRemindForm.getPid()){
                            fcAuditRemindCore.updateTaskRemind(auditTaskRemindEntity);
                        }else {
                            fcAuditRemindCore.insertTaskRemind(auditTaskRemindEntity);
                        }
                        orderInfoEntity.setRemind(Constants.REMIND_SET);
                        orderInfoEntity.setSetReminderTime(new Date());
                        auditOrderInfoCore.updateOrderInfo(orderInfoEntity);
                        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
                        return message;
                    }else {
                        message.setCode(MsgCodeConstant.ERR_SEARCH_CONTRACT_DETAIL);
                        message.setMessage("数据异常!");
                        return message;
                    }
                }
            }
        }
        message.setCode(MsgCodeConstant.ERR_SEARCH_CONTRACT_DETAIL);
        return message;
    }

    @Override
    public Message deleteRemind(String pid) {
        Message<Object> message = new Message<>();
        Message<AuditTaskRemindEntity> auditTaskRemindEntityMessage = fcAuditRemindCore.queryTaskRemindByPid(pid);
        if (null != auditTaskRemindEntityMessage){
            AuditTaskRemindEntity auditTaskRemindEntity = auditTaskRemindEntityMessage.getMessage();
            if (null != auditTaskRemindEntity){
                auditTaskRemindEntity.setStatus(Constants.DATA_STATUS_DEL);
                fcAuditRemindCore.updateTaskRemind(auditTaskRemindEntity);
                message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
                return message;
            }
        }
        message.setCode(MsgCodeConstant.ERR_SEARCH_CONTRACT_DETAIL);
        return message;
    }
}
