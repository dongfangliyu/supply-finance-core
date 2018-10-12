package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditSigningDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditResultEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditOrderInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditFinanceCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditRegisterCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditFinanceService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditSuppSigningService;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessOrderCore;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/18 0018下午 3:30
 */
@Service
public class AuditFinanceServiceImpl implements AuditFinanceService {

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

    @Override
    public Message searchFinanceList(AuditFinanceForm auditFinanceForm) {
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
            total = fcAuditFinanceCore.searchFinanceListCount(auditFinanceForm);
        }
        List<AuditOrderInfoEntity> list = fcAuditFinanceCore.searchFinanceList(auditFinanceForm);
        if (null != list){
            pageResponse.setData(list);
            pageResponse.setTotal(total);
            pageResponse.setPageNo(auditFinanceForm.getPageNo());
            pageResponse.setPageSize(auditFinanceForm.getPageSize());
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
            message.setMessage(pageResponse);
        }else {
            message.setCode(MsgCodeConstant.ERR_SEARCH_AUDIT_REGISTER);
        }
        return message;
    }

    @Override
    public Message searchFinanceDetail(String pid) {

        Message<Object> message = new Message<>();
        message = auditSigningService.searchSigningDetail(Long.parseLong(pid));
        AuditSigningDetailResponse auditSigningDetailResponse = JSONUtil.toBean(message.getMessage(), AuditSigningDetailResponse.class);
        return message;
    }

    @Override
    public Message submitFinancePass(String pid,String userName) {

        Message<Object> message = new Message<>();
        Message<AuditOrderInfoEntity> infoEntityMessage = auditOrderInfoCore.queryOrderInfoByPid(pid);
        if (infoEntityMessage.getCode() == MsgCodeConstant.ERR_MSG_SUCCESS){
            AuditOrderInfoEntity auditOrderInfoEntity = infoEntityMessage.getMessage();
            if (null != auditOrderInfoEntity){
                Message<BusinessOrderEntity> businessOrderEntityMessage = businessOrderCore.queryOrderByPid(auditOrderInfoEntity.getOrderId().toString());
                if (businessOrderEntityMessage.getCode() == MsgCodeConstant.ERR_MSG_SUCCESS){
                    BusinessOrderEntity businessOrderEntity = businessOrderEntityMessage.getMessage();
                    if (null != businessOrderEntity){

                        AuditResultEntity auditResultEntity = new AuditResultEntity();
                        auditResultEntity.setStatus(Constants.DATA_STATUS_NOL);
                        auditResultEntity.setResult(Constants.ISDELETE_YES);
                        auditResultEntity.setObjectId(pid);
                        auditResultEntity.setObjectType("20");
                        auditResultEntity.setType("20");
                        auditResultEntity.setCreateAt(new Date());
                        auditResultEntity.setCreateBy(userName);
                        fcAuditRegisterCore.saveAuditResultEntity(auditResultEntity);

                        auditOrderInfoEntity.setState("60");
                        auditOrderInfoEntity.setFinanceFirstTime(new Date());
                        auditOrderInfoEntity.setUpdateAt(new Date());
                        auditOrderInfoCore.updateOrderInfo(auditOrderInfoEntity);
                        businessOrderEntity.setProcessStatus("60");
                        businessOrderEntity.setUpdateAt(new Date());
                        businessOrderCore.updateOrder(businessOrderEntity);


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
    public Message submitFinanceFail(String pid,String userName) {

        Message<Object> message = new Message<>();
        Message<AuditOrderInfoEntity> infoEntityMessage = auditOrderInfoCore.queryOrderInfoByPid(pid);
        if (infoEntityMessage.getCode() == MsgCodeConstant.ERR_MSG_SUCCESS){
            AuditOrderInfoEntity auditOrderInfoEntity = infoEntityMessage.getMessage();
            if (null != auditOrderInfoEntity){
                Message<BusinessOrderEntity> businessOrderEntityMessage = businessOrderCore.queryOrderByPid(auditOrderInfoEntity.getOrderId().toString());
                if (businessOrderEntityMessage.getCode() == MsgCodeConstant.ERR_MSG_SUCCESS){
                    BusinessOrderEntity businessOrderEntity = businessOrderEntityMessage.getMessage();
                    if (null != businessOrderEntity){
                        AuditResultEntity auditResultEntity = new AuditResultEntity();
                        auditResultEntity.setStatus(Constants.DATA_STATUS_NOL);
                        auditResultEntity.setResult(Constants.ISDELETE_NO);
                        auditResultEntity.setObjectId(pid);
                        auditResultEntity.setObjectType("20");
                        auditResultEntity.setType("20");
                        auditResultEntity.setCreateAt(new Date());
                        auditResultEntity.setCreateBy(userName);
                        fcAuditRegisterCore.saveAuditResultEntity(auditResultEntity);
                        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);

                        auditOrderInfoEntity.setState("51");
                        auditOrderInfoEntity.setFinanceFirstTime(new Date());
                        auditOrderInfoEntity.setUpdateAt(new Date());
                        auditOrderInfoCore.updateOrderInfo(auditOrderInfoEntity);
                        businessOrderEntity.setProcessStatus("51");
                        businessOrderEntity.setFinancingStatus("4");
                        businessOrderEntity.setUpdateAt(new Date());
                        businessOrderCore.updateOrder(businessOrderEntity);
                        return message;
                    }
                }
            }
        }
        message.setCode(MsgCodeConstant.ERR_AUDIT_ORDER_INFO);
        return message;
    }
}
