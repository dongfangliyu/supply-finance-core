package cn.fintecher.supply.finance.loan.manager.service.margin.service.impl;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.MarginForm;
import cn.fintecher.supply.finance.loan.manager.common.form.PayMarginForm;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchMarginDetailForm;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditEntryCore;
import cn.fintecher.supply.finance.loan.manager.service.margin.feign.FCMarginCore;
import cn.fintecher.supply.finance.loan.manager.service.margin.service.MarginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lss
 * @date 2018/8/28
 */
@Service
public class MarginServiceImpl implements MarginService {
	
	 @Autowired
	 private FCMarginCore fcMarginCore;
	 @Autowired
	 private FCAuditEntryCore fcAuditEntryCore;
	 

	@Override
	public Message searchListMargin(MarginForm marginForm) {
		Message message = new Message();
        PageResponse<Object> response = new PageResponse<>();
        int total = 0;
        if(marginForm.getPageNo() != 0){
            total = fcMarginCore.searchMarginListCount(marginForm);
        }
        List<ConfirmingStockInfoResponse> productList = fcMarginCore.searchMarginList(marginForm);
        if (null != productList){
            response.setTotal(total);
            response.setData(productList);
            response.setPageNo(marginForm.getPageNo());
            response.setPageSize(marginForm.getPageSize());
            message.setMessage(response);
            message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        }else {
            message.setCode(MsgCodeConstant.ERR_SEARCH_PRO_LIST);
        }
        return message;
    }

	@Override
	public Message payMargin(PayMarginForm payMarginForm) {
		
		AuditTaskEntryEntity auditTaskEntryEntity = new AuditTaskEntryEntity();
		auditTaskEntryEntity.setRepaymentTime(new Date());
		auditTaskEntryEntity.setRepaymentPrice(payMarginForm.getRepaymentPrice());
		auditTaskEntryEntity.setAccount(payMarginForm.getBankCard());
		auditTaskEntryEntity.setAccountType("1");
		auditTaskEntryEntity.setResourceId(payMarginForm.getBussenssFileId());
		auditTaskEntryEntity.setObjectId(payMarginForm.getCompanyDealerId());
		auditTaskEntryEntity.setOrderInfoId(payMarginForm.getMarginId());
		return fcAuditEntryCore.insertTaskEntry(auditTaskEntryEntity);
     
		
	}

	@Override
	public Message searchMarginDetail(SearchMarginDetailForm searchMarginDetailForm) {
		return fcAuditEntryCore.searchMarginDetail(searchMarginDetailForm);
	}

}
