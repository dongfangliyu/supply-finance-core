package cn.fintecher.supply.finance.loan.manager.service.pledge.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplyForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplySubmit;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 10:56:11
 */
public interface PledgeApplyService {    
	
    public Message searchApplyList(PledgeApplyForm pledgeApplyForm, CompanyUserEntity user);
	
    public Message searchApplyDetail(Long id, CompanyUserEntity user);
	
    public Message submitApply(PledgeApplySubmit pledgeApplySubmit, CompanyUserEntity user);
   

}
