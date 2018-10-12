package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditSigningDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditSigningListForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditOrderInfoCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditFileCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditFinanceLoanCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditFinanceLoanService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditSuppSigningService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 18:37 2018/7/23
 */
@Service
public class AuditFinanceLoanServiceImpl implements AuditFinanceLoanService {
    @Autowired
    private FCAuditFinanceLoanCore fcAuditFinanceLoanCore;

    @Autowired
    private AuditSuppSigningService auditSigningService;

    @Autowired
    private FCAuditFileCore fcAuditFileCore;

    @Autowired
    private AuditOrderInfoCore auditOrderInfoCore;

    @Override
    public Message searchSigningList(AuditSigningListForm auditSigningListForm) {
        Message msg = new Message();
        PageResponse<Object> pageResponse = new PageResponse<>();
        try {
            if (!Strings.isNullOrEmpty(auditSigningListForm.getStartTime())){
                // 转换日期
                auditSigningListForm.setStartTime(DateUtil.TransformatStartTime(auditSigningListForm.getStartTime()));
            }
            if (!Strings.isNullOrEmpty(auditSigningListForm.getEndTime())){
                // 转换日期
                auditSigningListForm.setEndTime(DateUtil.TransformatEndTime(auditSigningListForm.getEndTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int total = 0;
        if(auditSigningListForm.getPageNo() != 0){
            total = fcAuditFinanceLoanCore.searchSignListCount(auditSigningListForm);
        }
        List<AuditOrderInfoEntity> list = fcAuditFinanceLoanCore.searchSigningList(auditSigningListForm);
        if(null!=list) {
            pageResponse.setTotal(total);
            pageResponse.setData(list);
            pageResponse.setPageNo(auditSigningListForm.getPageNo());
            pageResponse.setPageSize(auditSigningListForm.getPageSize());
            msg.setMessage(pageResponse);
            msg.setCode(0);
        } else{
            msg.setCode(-1);
        }
        return msg;
    }

    @Override
    public Message searchSigningDetail(String id) {
        Message<Object> message = new Message<>();
        message = auditSigningService.searchSigningDetail(Long.parseLong(id));
       // AuditOrderInfoEntity auditOrderInfoEntity = auditOrderInfoCore.queryOrderInfoByPid(id).getMessage();
        List<AuditFileEntity> list = fcAuditFileCore.searchAllAuditFileByOwnerIdAndCategory(id, "loanVoucher");
        AuditSigningDetailResponse auditSigningDetailResponse = JSONUtil.toBean(message.getMessage(), AuditSigningDetailResponse.class);
        auditSigningDetailResponse.setAuditFileEntities(list);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        message.setMessage(auditSigningDetailResponse);
        return message;
    }
}
