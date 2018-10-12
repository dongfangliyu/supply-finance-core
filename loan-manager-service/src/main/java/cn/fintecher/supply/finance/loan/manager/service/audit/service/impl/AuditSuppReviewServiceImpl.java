package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditSuppReviewResponse;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueRepaymentRecordEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditOrderInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditEntryCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCBaseBankInfoService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditSuppReviewService;
import cn.fintecher.supply.finance.loan.manager.service.overdue.core.OverdueRepaymentRecordCore;
import cn.fintecher.supply.finance.loan.manager.service.overdue.service.OverdueOrderService;
import com.google.common.base.Strings;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/7/24 17:10
 */
@Service("AuditSuppReviewService")
public class AuditSuppReviewServiceImpl implements AuditSuppReviewService {

    @Autowired
    private AuditOrderInfoCore auditOrderInfoCore;

    @Autowired
    private FCBaseBankInfoService fcBaseBankInfoService;

    @Autowired
    private OverdueRepaymentRecordCore repaymentRecordCore;

    @Autowired
    private OverdueOrderService overdueOrderService;

    @Autowired
    private FCAuditEntryCore fcAuditEntryCore;

    @Override
    public Message searchSuppReviewList(AuditSuppReviewRequest auditSuppReviewRequest) {
        PagedResponse response = new PagedResponse();
        try {
            if (!Strings.isNullOrEmpty(auditSuppReviewRequest.getStartTime())){
                // 转换日期
                auditSuppReviewRequest.setStartTime(DateUtil.TransformatStartTime(auditSuppReviewRequest.getStartTime()));
            }
            if (!Strings.isNullOrEmpty(auditSuppReviewRequest.getEndTime())){
                // 转换日期
                auditSuppReviewRequest.setEndTime(DateUtil.TransformatEndTime(auditSuppReviewRequest.getEndTime()));
            }
            if (!Strings.isNullOrEmpty(auditSuppReviewRequest.getShouldStartTime())){
                // 转换日期
                auditSuppReviewRequest.setShouldStartTime(DateUtil.TransformatStartTime(auditSuppReviewRequest.getShouldStartTime()));
            }
            if (!Strings.isNullOrEmpty(auditSuppReviewRequest.getShouldEndTime())){
                // 转换日期
                auditSuppReviewRequest.setShouldEndTime(DateUtil.TransformatEndTime(auditSuppReviewRequest.getShouldEndTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Message<List<AuditOrderInfoEntity>> listMessage = auditOrderInfoCore.searchSuppReviewList(auditSuppReviewRequest);
        if(MessageType.MSG_SUCCESS.equals(listMessage.getCode())){
            response.setData(listMessage.getMessage());
        }
        Message<Integer> countMessage = auditOrderInfoCore.searchSuppReviewListCount(auditSuppReviewRequest);
        if(MessageType.MSG_SUCCESS.equals(countMessage.getCode())){
            response.setTotal(Integer.parseInt(countMessage.getMessage().toString()));
        }
        response.setPageNo(auditSuppReviewRequest.getPageNo());
        response.setPageSize(auditSuppReviewRequest.getPageSize());
        return new Message(MessageType.MSG_SUCCESS, "audit_service", response);
    }

    @Override
    public Message searchSuppReviewDetail(Long pid) {
        AuditSuppReviewResponse auditSuppReviewResponse =new AuditSuppReviewResponse();
        Message<AuditOrderInfoEntity> orderInfoMessage = auditOrderInfoCore.queryOrderInfoByPid(pid.toString());
        if(MessageType.MSG_SUCCESS.equals(orderInfoMessage.getCode())){
            AuditOrderInfoEntity auditOrderInfoEntity = orderInfoMessage.getMessage();

            BaseBankInfoEntity baseBankInfoEntity = fcBaseBankInfoService.getRepayBankBySignId(orderInfoMessage.getMessage().getPid());
            if(baseBankInfoEntity!=null && baseBankInfoEntity.getPid()>0){
                auditSuppReviewResponse.setBaseBankInfoEntity(baseBankInfoEntity);
            }

            //计算服务费
            if(auditOrderInfoEntity.getServiceFee() > 0 && Double.parseDouble(auditOrderInfoEntity.getApprovalAmount()) > 0 && auditOrderInfoEntity.getApprovalTerm()>0) {
                Integer a = auditOrderInfoEntity.getServiceFee();
                Double b = Double.parseDouble(auditOrderInfoEntity.getApprovalAmount());
                Integer C = auditOrderInfoEntity.getApprovalTerm();
                String serviceMoney = new BigDecimal(a)
                        .multiply(new BigDecimal(b))
                        .divide(new BigDecimal(36000), 20, BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal(C))
                        .setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                auditSuppReviewResponse.setServiceMoney(serviceMoney);
            }

            //计算利息
            if(auditOrderInfoEntity.getInterestRate() > 0 && Double.parseDouble(auditOrderInfoEntity.getApprovalAmount()) > 0 && auditOrderInfoEntity.getApprovalTerm()>0){
            	Double a = Double.parseDouble(auditOrderInfoEntity.getApprovalAmount());
                Integer b = auditOrderInfoEntity.getApprovalTerm();
                Integer c = auditOrderInfoEntity.getInterestRate();
                String interest = new BigDecimal(a)
                        .multiply(new BigDecimal(c))
                        .divide(new BigDecimal(36000),20,BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal(b))
                        .setScale(2,BigDecimal.ROUND_HALF_UP).toString();
                auditOrderInfoEntity.setInterest(interest);
            }else{
                auditOrderInfoEntity.setInterest(auditOrderInfoEntity.getInterest());
            }
            auditSuppReviewResponse.setAuditOrderInfoEntity(auditOrderInfoEntity);

            //还款复核金额
            Double repay1 =  fcAuditEntryCore.searchSumOfRepaymentPrice(orderInfoMessage.getMessage().getPid());
            Double repay2 =  fcAuditEntryCore.searchSumOfRepaymentPriceByState(orderInfoMessage.getMessage().getPid());
            auditSuppReviewResponse.setReviewed(repay1==null?"0":repay1.toString());
            auditSuppReviewResponse.setUnReviewed(repay2==null?"0":repay2.toString());

            //未结清金额
            if("1".equals(orderInfoMessage.getMessage().getOverdueType())){
                OverdueOrderEntity overdueOrderEntity = new OverdueOrderEntity();
                overdueOrderEntity.setOrderInfoId(orderInfoMessage.getMessage().getPid());
                overdueOrderEntity.setStatus(Constants.DATA_STATUS_NOL);
                Message<List<OverdueOrderEntity>> msg = overdueOrderService.selectByOrder(overdueOrderEntity);
                OverdueOrderEntity orderEntity = msg.getMessage().get(0);
                OverdueRepaymentRecordEntity overdueRepaymentRecordEntity = new OverdueRepaymentRecordEntity();
                overdueRepaymentRecordEntity.setOrderId(orderEntity.getPid());
                overdueRepaymentRecordEntity.setStatus(Constants.DATA_STATUS_NOL);
                Message<List<OverdueRepaymentRecordEntity>> recordMsg = repaymentRecordCore.selectByRepaymentRecord(overdueRepaymentRecordEntity);
                Integer days = 0;
                Long unpaidPenaltyHistory = 0L;
                try {
                    if (ChkUtil.isEmpty(recordMsg.getMessage())) {
                        days = DateUtil.diffDays(orderEntity.getLoanTime(),new Date())-orderEntity.getApprovalTerm();
                    }else{
                        unpaidPenaltyHistory += recordMsg.getMessage().get(0).getUnpaidPenalty();
                        days = DateUtil.diffDays(recordMsg.getMessage().get(0).getRepaymentTime(),new Date());
                    }
                }catch (Exception e){
                    return new Message(MessageType.MSG_ERROR,"audit_service",e.getMessage());
                }
                Long unpaidPenalty = orderEntity.getUnpaidPrincipal()*days*orderEntity.getPenaltyFee()/10000+unpaidPenaltyHistory;
                Long uncleared = unpaidPenalty+orderEntity.getUnpaidPrincipal()+orderEntity.getUnpaidInterest()+orderEntity.getUnpaidService();
                if(uncleared!=null && uncleared > 0){
                    uncleared = uncleared/100;
                }else{
                    uncleared = 0L;
                }
                auditSuppReviewResponse.setUncleared(uncleared.toString());
            }else{
                if(orderInfoMessage.getMessage().getApprovalAmount()!=null && Double.parseDouble(orderInfoMessage.getMessage().getApprovalAmount())>0){
                    auditSuppReviewResponse.setUncleared(String.valueOf(Double.parseDouble(orderInfoMessage.getMessage().getCertificateAmount())-(repay1==null?0:repay1)));
                }else{
                    auditSuppReviewResponse.setUncleared("0");
                }
            }
        }
        return new Message(MessageType.MSG_SUCCESS,"audit_service",auditSuppReviewResponse);
    }
}
