package cn.fintecher.supply.finance.loan.manager.service.company.service;

import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyBlacklistEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;

import java.util.List;


public interface CompanyBlacklistService{

	/**
	 * 查询黑名单记录
	 * @param blackListFrom
	 * @return
	 */
    Message  selectBlackList(BlackListFrom blackListFrom);

   

}