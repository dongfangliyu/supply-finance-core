package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditOrderInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditPlatformReviewService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/7/27 10:28
 */
@Service("AuditPlatformReviewService")
public class AuditPlatformReviewServiceImpl implements AuditPlatformReviewService {

    @Autowired
    private AuditOrderInfoCore auditOrderInfoCore;

    @Override
    public Message searchPlatformReviewList(AuditSuppReviewRequest auditSuppReviewRequest) {
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
            if (!Strings.isNullOrEmpty(auditSuppReviewRequest.getShouldEndTime())){
                // 转换日期
                auditSuppReviewRequest.setShouldEndTime(DateUtil.TransformatEndTime(auditSuppReviewRequest.getShouldEndTime()));
            }
            if (!Strings.isNullOrEmpty(auditSuppReviewRequest.getShouldStartTime())){
                // 转换日期
                auditSuppReviewRequest.setShouldStartTime(DateUtil.TransformatStartTime(auditSuppReviewRequest.getShouldStartTime()));
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

}
