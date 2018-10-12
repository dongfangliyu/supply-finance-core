package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditEnterEntryService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditEntryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gonghebin
 * @date 2018/7/24 0024下午 4:02
 */
@Service
public class AuditEnterEntryServiceImpl implements AuditEnterEntryService {

    @Autowired
    private AuditEntryService auditEntryService;


    @Override
    public Message searchEntryList(AuditRemindForm auditRemindForm) {
        try{
            if (StringUtils.isNotBlank(auditRemindForm.getStartTime())){
                auditRemindForm.setStartTime(DateUtil.TransformatStartTime(auditRemindForm.getStartTime()));
            }
            if (StringUtils.isNotBlank(auditRemindForm.getEndTime())){
                auditRemindForm.setEndTime(DateUtil.TransformatEndTime(auditRemindForm.getEndTime()));
            }
            if (StringUtils.isNotBlank(auditRemindForm.getShouldStartTime())){
                auditRemindForm.setShouldStartTime(DateUtil.TransformatStartTime(auditRemindForm.getShouldStartTime()));
            }
            if (StringUtils.isNotBlank(auditRemindForm.getShouldEndTime())){
                auditRemindForm.setShouldEndTime(DateUtil.TransformatEndTime(auditRemindForm.getShouldEndTime()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return auditEntryService.searchEntryList(auditRemindForm);
    }

    @Override
    public Message searchEntryDetail(String pid) {
        return auditEntryService.searchEntryDetail(pid);
    }

    @Override
    public Message submitEntry(AuditEntryForm auditEntryForm) {
        return auditEntryService.submitEntry(auditEntryForm);
    }
}
