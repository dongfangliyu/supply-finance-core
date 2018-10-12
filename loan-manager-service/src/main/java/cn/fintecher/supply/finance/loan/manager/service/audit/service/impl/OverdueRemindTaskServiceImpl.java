package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskRemindEntity;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditRemindCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.OverdueRemindTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/8/4 0004下午 2:57
 */
@Service
public class OverdueRemindTaskServiceImpl implements OverdueRemindTaskService {

    @Autowired
    private FCAuditRemindCore fcAuditRemindCore;

    @Override
    public void submitRemindByDate() {
        List<AuditTaskRemindEntity> list = fcAuditRemindCore.selectAllRemind();
        if (list.size() > 0){
            for (AuditTaskRemindEntity audit:list) {
                audit.setState("1");
                fcAuditRemindCore.updateTaskRemind(audit);
            }
        }

    }
}
