package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditOrderInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditPlatformSigningService;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessOrderCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/7/23 14:17
 */
@Service("AuditPlatformSigningService")
public class AuditPlatformSigningServiceImpl implements AuditPlatformSigningService {
    @Autowired
    private AuditOrderInfoCore auditOrderInfoCore;

    @Autowired
    private BusinessOrderCore businessOrderCore;

    @Override
    public Message searchPlatformSigningList(AuditSigningRequest auditSigningRequest) {
        PagedResponse response = new PagedResponse();
        Message<List<AuditOrderInfoEntity>> listMessage =auditOrderInfoCore.searchPlatformSigningList(auditSigningRequest);
        if(MessageType.MSG_SUCCESS.equals(listMessage.getCode())){
            response.setData(listMessage.getMessage());
        }
        Message<Integer> countMessage = auditOrderInfoCore.searchPlatformSigningListCount(auditSigningRequest);
        if(MessageType.MSG_SUCCESS.equals(countMessage.getCode())){
            response.setTotal(Integer.parseInt(countMessage.getMessage().toString()));
        }
        response.setPageNo(auditSigningRequest.getPageNo());
        response.setPageSize(auditSigningRequest.getPageSize());
        return new Message(MessageType.MSG_SUCCESS, "audit_service", response);
    }

    @Override
    public Message submitSigning(Long pid, String name) {
        try {
            Message<AuditOrderInfoEntity> infoEntityMessage =  auditOrderInfoCore.queryOrderInfoByPid(pid.toString());
            if(MessageType.MSG_SUCCESS.equals(infoEntityMessage.getCode())){
                AuditOrderInfoEntity auditOrderInfoEntity =infoEntityMessage.getMessage();
                BusinessOrderEntity businessOrderEntity = new BusinessOrderEntity();
                Message<BusinessOrderEntity> message = businessOrderCore.queryOrderByPid(auditOrderInfoEntity.getOrderId().toString());
                if(MessageType.MSG_SUCCESS.equals(message.getCode())){
                    businessOrderEntity = message.getMessage();
                    businessOrderEntity.setProcessStatus("50");
                    businessOrderEntity.setUpdateAt(new Date());
                    businessOrderEntity.setUpdateBy(name);
                    Message result = businessOrderCore.updateOrder(businessOrderEntity);
                    if(MessageType.MSG_SUCCESS.equals(result.getCode())){
                        auditOrderInfoEntity.setState("50");
                        auditOrderInfoEntity.setUpdateBy(name);
                        auditOrderInfoEntity.setUpdateAt(new Date());
                        auditOrderInfoEntity.setSigningTime(new Date());
                        return auditOrderInfoCore.updateOrderInfo(auditOrderInfoEntity);
                    }else{
                        return result;
                    }
                }else{
                    return message;
                }

            }else{
                return infoEntityMessage;
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"audit_service",e.getMessage());
        }
    }
}
