package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditOrderInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditFileCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditEnterSigningService;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessOrderCore;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/7/23 13:32
 */
@Service("AuditEnterSigningService")
public class AuditEnterSigningServiceImpl implements AuditEnterSigningService {

    @Autowired
    private AuditOrderInfoCore auditOrderInfoCore;

    @Autowired
    private FCAuditFileCore fcAuditFileCore;

    @Autowired
    private BusinessOrderCore businessOrderCore;

    @Override
    public Message searchEnterSigningList(AuditSigningRequest auditSigningRequest) {
        try{
            if (StringUtils.isNotBlank(auditSigningRequest.getStartTime())){
                auditSigningRequest.setStartTime(DateUtil.TransformatStartTime(auditSigningRequest.getStartTime()));
            }
            if (StringUtils.isNotBlank(auditSigningRequest.getEndTime())){
                auditSigningRequest.setEndTime(DateUtil.TransformatEndTime(auditSigningRequest.getEndTime()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        PagedResponse response = new PagedResponse();
        Message<List<AuditOrderInfoEntity>> listMessage =auditOrderInfoCore.searchEnterSigningList(auditSigningRequest);
        if(MessageType.MSG_SUCCESS.equals(listMessage.getCode())){
            response.setData(listMessage.getMessage());
        }
        Message<Integer> countMessage = auditOrderInfoCore.searchEnterSigningListCount(auditSigningRequest);
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
                    businessOrderEntity.setProcessStatus("42");
                    businessOrderEntity.setUpdateAt(new Date());
                    businessOrderEntity.setUpdateBy(name);
                    Message result = businessOrderCore.updateOrder(businessOrderEntity);
                    if(MessageType.MSG_SUCCESS.equals(result.getCode())){
                        auditOrderInfoEntity.setState("42");
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


    @Override
    public Message isUpLoadContract(Long pid) {
        List<AuditFileEntity> list =  fcAuditFileCore.searchAllFileByOwnerId(pid.toString());
        if(list!=null && list.size()>=4){
            int type1 = 0;
            int type2 = 0;
            for(AuditFileEntity auditFileEntity : list){
                if("enterTransferAccountsReceivable".equals(auditFileEntity.getCategory())){
                    type1=1;
                }
                if("enterConfirmationAccountsReceivable".equals(auditFileEntity.getCategory())){
                    type2=1;
                }
            }
            if(type1==1&&type2==1){
                return new Message(MessageType.MSG_SUCCESS,"audit_service",null);
            }else{
                return new Message(MessageType.MSG_ERROR,"audit_service","请先上传纸质合同！");
            }
        }else{
            return new Message(MessageType.MSG_ERROR,"audit_service","请先上传纸质合同！");
        }
    }
}
