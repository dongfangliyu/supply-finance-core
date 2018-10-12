package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditRemindResponse;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditOrderInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditEntryCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditEntryService;
import cn.fintecher.supply.finance.loan.manager.service.base.service.BaseBankInfoService;
import com.google.common.base.Strings;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 5:45
 */
@Service
public class AuditEntryServiceImpl implements AuditEntryService {

    @Autowired
    private FCAuditEntryCore fcAuditEntryCore;

    @Autowired
    private AuditOrderInfoCore auditOrderInfoCore;

    @Autowired
    private BaseBankInfoService baseBankInfoService;

    @Override
    public Message searchEntryList(AuditRemindForm auditRemindForm) {
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
            total = auditOrderInfoCore.searchEntryListCount(auditRemindForm);
        }
        List<AuditOrderInfoEntity> list = auditOrderInfoCore.searchEntryList(auditRemindForm);
        if (null != list){
            pageResponse.setPageNo(auditRemindForm.getPageNo());
            pageResponse.setData(list);
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
    public Message searchEntryDetail(String pid) {
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
                    auditRemindResponse.setShouldTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(shouldTime));
                }
                AuditTaskEntryEntity auditTaskEntryEntity = new AuditTaskEntryEntity();
                auditTaskEntryEntity.setOrderInfoId(Long.getLong(pid));
                auditTaskEntryEntity.setState(Constants.ENTRY_STATE_YES);
                Message<List<AuditTaskEntryEntity>> listMessage = fcAuditEntryCore.selectByTaskEntry(auditTaskEntryEntity);
                if (null != listMessage){
                    List<AuditTaskEntryEntity> auditTaskEntryEntities = JSONUtil.toList(listMessage.getMessage(), AuditTaskEntryEntity.class);
                    if (auditTaskEntryEntities.size() == 0){
                        auditRemindResponse.setUnclearPrice(Double.parseDouble(orderInfoEntity.getCertificateAmount()));
                        auditRemindResponse.setClosedPrice(0d);
                    }else {
                        // 查询还款总额
                        Double aDouble = 0d;
                        Double aDouble1 = fcAuditEntryCore.searchSumOfRepaymentPrice(Long.parseLong(pid));
                        Double aDouble2 = fcAuditEntryCore.searchSumOfRepaymentPriceByState(Long.parseLong(pid));
                        if (aDouble1 == null){
                            aDouble = aDouble2;
                        }else {
                            if (aDouble2 == null){
                                aDouble =  aDouble1;
                            }else {
                                aDouble =  aDouble1 + aDouble2;
                            }
                        }
                        if(aDouble==null){
                            aDouble=0d;
                            auditRemindResponse.setClosedPrice(aDouble);
                        }
                        if (!Strings.isNullOrEmpty(orderInfoEntity.getCertificateAmount())){
                            if (aDouble1 != null){
                                auditRemindResponse.setUnReviewPrice(Double.parseDouble(orderInfoEntity.getCertificateAmount()) - aDouble1);
                            }else {
                                auditRemindResponse.setUnReviewPrice(Double.parseDouble(orderInfoEntity.getCertificateAmount()));
                            }
                            auditRemindResponse.setUnclearPrice(Double.parseDouble(orderInfoEntity.getCertificateAmount()) - aDouble);
                        }
                    }
                }

                List<AuditTaskEntryEntity> list = fcAuditEntryCore.searchTaskEntityByOrderId(orderInfoEntity.getPid());
                if (list.size() != 0){
                    auditRemindResponse.setAuditTaskEntryEntity(list.get(0));
                }

                // 银行信息
                BaseBankInfoEntity repayBankBySignId = baseBankInfoService.getRepayBankBySignId(orderInfoEntity.getPid());
                auditRemindResponse.setBaseBankInfoEntity(repayBankBySignId);
                if (null != orderInfoEntity.getReviewAgreeTime()){
                    Date reviewAgreeTime = orderInfoEntity.getReviewAgreeTime();
                    String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(reviewAgreeTime);
                    auditRemindResponse.setReview_agree_time(format);
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
    public Message searchEntry(AuditEntryForm auditEntryForm) {
        Message message = new Message();
        PageResponse<Object> pageResponse = new PageResponse<>();
        int total = 0;
        if(auditEntryForm.getPageNo() != 0){
            total = fcAuditEntryCore.searchTaskEntryListCount(auditEntryForm);
        }
        List<AuditTaskEntryEntity> list = fcAuditEntryCore.searchTaskEntryList(auditEntryForm);
        if (null != list){
            pageResponse.setPageNo(auditEntryForm.getPageNo());
            pageResponse.setData(list);
            pageResponse.setTotal(total);
            pageResponse.setPageSize(auditEntryForm.getPageSize());
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            message.setMessage(pageResponse);
        }else {
            message.setCode(MsgCodeConstant.ERR_SEARCH_AUDIT_REGISTER);
        }
        return message;
    }

    @Override
    public Message submitEntry(AuditEntryForm auditEntryForm) {

        Message<Object> message = new Message<>();
        if (null != auditEntryForm){
            Message<AuditOrderInfoEntity> auditOrderInfoEntityMessage = auditOrderInfoCore.queryOrderInfoByPid(auditEntryForm.getOrderId().toString());
            if (null != auditOrderInfoEntityMessage){
                AuditOrderInfoEntity orderInfoEntity = auditOrderInfoEntityMessage.getMessage();
                if (null != orderInfoEntity){
                    if(orderInfoEntity.getOverdueType().equals("1")){
                        message.setCode(MsgCodeConstant.ERR_MSG_DEFAULT);
                        message.setMessage("已逾期!");
                        return message;
                    }

                    AuditTaskEntryEntity auditTaskEntryEntity = new AuditTaskEntryEntity();
                    String repaymentTime = auditEntryForm.getRepaymentTime();
                    repaymentTime = repaymentTime.replace("Z", " UTC");//注意是空格+UTC
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
                    try {
                        auditTaskEntryEntity.setRepaymentTime(format.parse(repaymentTime));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    auditTaskEntryEntity.setRepaymentPrice(auditEntryForm.getRepaymentPrice());
                    if (auditEntryForm.getAccountType().equals("0")){
                        //  汇款账户查询错误
                        BaseBankInfoEntity repayBankBySignId = baseBankInfoService.getRepayBankBySignId(orderInfoEntity.getPid());
                        if (repayBankBySignId != null){
                            auditTaskEntryEntity.setAccount(repayBankBySignId.getBankCard());
                        }
                    }else {
                        auditTaskEntryEntity.setAccount(auditEntryForm.getAccount());
                    }

                    auditTaskEntryEntity.setAccountType(auditEntryForm.getAccountType());
                    auditTaskEntryEntity.setResourceId(auditEntryForm.getResourceId());
                    auditTaskEntryEntity.setObjectId(auditEntryForm.getOrderId());
                    auditTaskEntryEntity.setState(Constants.ENTRY_STATE_NOSET);
                    auditTaskEntryEntity.setObjectType(Constants.ENTRY_TYPE_NO);
                    auditTaskEntryEntity.setOrderInfoId(auditEntryForm.getOrderId());
                    auditTaskEntryEntity.setCreateAt(new Date());
                    auditTaskEntryEntity.setStatus(Constants.DATA_STATUS_NOL);
                    fcAuditEntryCore.insertTaskEntry(auditTaskEntryEntity);
                    orderInfoEntity.setRepaymentTime(new Date());
                    orderInfoEntity.setRepaymentReview(Constants.ENTRY_STATE_NOSET);
                    // 未复核总数
                    Double aDouble = fcAuditEntryCore.searchSumOfRepaymentPriceByState(auditEntryForm.getOrderId());
                    // 已复核总数
                    Double aDouble1 = fcAuditEntryCore.searchSumOfRepaymentPrice(auditEntryForm.getOrderId());
                    Double i = 0d;
                    double v = Double.parseDouble(orderInfoEntity.getCertificateAmount());
                    if (aDouble1 == null){
                        i = aDouble;
                        if (v - i == 0){
                            orderInfoEntity.setState("80");
                        }else {
                            orderInfoEntity.setState("71");
                        }
                    }else {
                        orderInfoEntity.setState("81");
                    }
                    orderInfoEntity.setRemark("1");
                    orderInfoEntity.setRepaymentTime(new Date());
                    auditOrderInfoCore.updateOrderInfo(orderInfoEntity);
                    message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
                    return message;
                }

            }
        }
        message.setCode(MsgCodeConstant.ERR_SEARCH_CONTRACT_DETAIL);
        return message;
    }
}
