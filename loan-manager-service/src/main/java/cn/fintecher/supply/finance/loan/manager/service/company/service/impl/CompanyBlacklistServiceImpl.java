package cn.fintecher.supply.finance.loan.manager.service.company.service.impl;

import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.service.company.feign.CompanyBlacklistCore;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyBlacklistService;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyBlacklistEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-20 11:42:47
 */
@Service("companyBlacklistService")
public class CompanyBlacklistServiceImpl implements CompanyBlacklistService {

	@Autowired
    private CompanyBlacklistCore companyBlacklistCore;


	@Override
	public Message selectBlackList(BlackListFrom blackListFrom) {
		List<CompanyBlacklistEntity> list = companyBlacklistCore.selectBlackList(blackListFrom);
		int total = companyBlacklistCore.selectBlackListCount(blackListFrom);
		PageResponse pageResponse = new PageResponse();
		pageResponse.setData(list);
		pageResponse.setTotal(total);
		pageResponse.setPageNo(blackListFrom.getPageNo());
		pageResponse.setPageSize(blackListFrom.getPageSize());
		return new Message(MessageType.MSG_SUCCESS,"blacklist",pageResponse);
	}
}

