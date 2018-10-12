package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditOrderInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditEntryCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditEntryService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditReviewService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 6:36
 */
@Service
public class AuditReviewServiceImpl implements AuditReviewService {

    @Autowired
    private AuditEntryService auditEntryService;

    @Autowired
    private FCAuditEntryCore fcAuditEntryCore;

    @Autowired
    private AuditOrderInfoCore auditOrderInfoCore;

    @Override
    public Message searchReviewList(AuditRemindForm auditRemindForm) {
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
            total = auditOrderInfoCore.searchReviewListCount(auditRemindForm);
        }
        List<AuditOrderInfoEntity> list = auditOrderInfoCore.searchReviewList(auditRemindForm);
        if (null != list){
            pageResponse.setData(list);
            pageResponse.setPageNo(auditRemindForm.getPageNo());
            pageResponse.setTotal(total);
            pageResponse.setPageSize(auditRemindForm.getPageSize());
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            message.setMessage(pageResponse);
        }else {
            message.setCode(MsgCodeConstant.ERR_SEARCH_AUDIT_REGISTER);
        }
        return message;
    }

    @Override
    public Message searchReviewDetail(String pid) {
        return auditEntryService.searchEntryDetail(pid);
    }

    @Override
    public Message submitResult(String pid, String state) {
        Message<Object> message = new Message<>();
        Message<AuditTaskEntryEntity> auditTaskEntryEntityMessage = fcAuditEntryCore.queryTaskEntryByPid(pid);
        if (null != auditTaskEntryEntityMessage){
            AuditTaskEntryEntity taskEntryEntity = auditTaskEntryEntityMessage.getMessage();
            if (null != taskEntryEntity){
                if (null != taskEntryEntity.getOrderInfoId()){
                    Message<AuditOrderInfoEntity> auditOrderInfoEntityMessage = auditOrderInfoCore.queryOrderInfoByPid(taskEntryEntity.getOrderInfoId().toString());
                    if (null != auditOrderInfoEntityMessage){
                        AuditOrderInfoEntity orderInfoEntity = auditOrderInfoEntityMessage.getMessage();
                        if (null != orderInfoEntity){
                            taskEntryEntity.setState(state);
                            taskEntryEntity.setUpdateAt(new Date());
                            fcAuditEntryCore.updateTaskEntry(taskEntryEntity);
                            AuditTaskEntryEntity entity = new AuditTaskEntryEntity();
                            entity.setOrderInfoId(orderInfoEntity.getPid());
                            entity.setState(Constants.ENTRY_STATE_NOSET);
                            entity.setStatus(Constants.DATA_STATUS_NOL);
                            // 查询没有审核的录入信息
                            Message<List<AuditTaskEntryEntity>> listMessage = fcAuditEntryCore.selectByTaskEntry(entity);
                            if (null != listMessage){
                                List<AuditTaskEntryEntity> auditTaskEntryEntities = JSONUtil.toList(listMessage.getMessage(), AuditTaskEntryEntity.class);
                                if (auditTaskEntryEntities.size() > 0){
                                    orderInfoEntity.setRepaymentReview(Constants.ENTRY_STATE_NOSET);
                                }else {
                                    orderInfoEntity.setRepaymentReview(Constants.ENTRY_STATE_YES);
                                }
                            }
                            // 已复核总数
                            Double integer1 = fcAuditEntryCore.searchSumOfRepaymentPrice(taskEntryEntity.getOrderInfoId());
                            double aDouble = Double.parseDouble(orderInfoEntity.getCertificateAmount());
                            if ("1".equals(state)) {
                                if (aDouble - integer1 == 0) {
                                    orderInfoEntity.setState("90");
                                    orderInfoEntity.setSettleTime(new Date());
                                } else {
                                    orderInfoEntity.setState("81");
                                }
                            }

                            if ("2".equals(state)){
                                // 查已经复核的数据
                                AuditTaskEntryEntity auditTaskEntryEntity = new AuditTaskEntryEntity();
                                auditTaskEntryEntity.setOrderInfoId(orderInfoEntity.getPid());
                                auditTaskEntryEntity.setState("1");
                                auditTaskEntryEntity.setStatus(Constants.DATA_STATUS_NOL);
                                Message<List<AuditTaskEntryEntity>> taskEntry = fcAuditEntryCore.selectByTaskEntry(auditTaskEntryEntity);
                                if (null != taskEntry){
                                    List<AuditTaskEntryEntity> auditTaskEntryEntities = JSONUtil.toList(taskEntry.getMessage(), AuditTaskEntryEntity.class);
                                    if (auditTaskEntryEntities.size() > 0){
                                        orderInfoEntity.setState("81");
                                    }else {
                                        auditTaskEntryEntity.setOrderInfoId(orderInfoEntity.getPid());
                                        auditTaskEntryEntity.setState("0");
                                        auditTaskEntryEntity.setStatus(Constants.DATA_STATUS_NOL);
                                        Message<List<AuditTaskEntryEntity>> taskEntry1 = fcAuditEntryCore.selectByTaskEntry(auditTaskEntryEntity);
                                        if (null != taskEntry1) {
                                            List<AuditTaskEntryEntity> auditTaskEntryEntities1 = JSONUtil.toList(taskEntry1.getMessage(), AuditTaskEntryEntity.class);
                                            if (auditTaskEntryEntities1.size() > 0){
                                                orderInfoEntity.setState("71");
                                            }else {
                                                orderInfoEntity.setState("70");
                                            }
                                        }
                                    }

                                }
                            }
                            List<AuditTaskEntryEntity> list = fcAuditEntryCore.searchTaskEntityByOrderId(orderInfoEntity.getPid());
                            if (list.size() > 0){
                                AuditTaskEntryEntity auditTaskEntryEntity = list.get(0);
                                if ("1".equals(auditTaskEntryEntity.getState())){
                                    orderInfoEntity.setRepaymentReview("1");
                                }
                            }
                            orderInfoEntity.setReviewAgreeTime(new Date());
                            auditOrderInfoCore.updateOrderInfo(orderInfoEntity);
                            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
                            return message;
                        }
                    }
                }
            }
        }
        message.setCode(MsgCodeConstant.ERR_SEARCH_CONTRACT_DETAIL);
        return message;
    }
}
