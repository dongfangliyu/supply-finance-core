package cn.fintecher.supply.finance.loan.manager.pm.bff.company.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;

import cn.fintecher.supply.finance.loan.manager.pm.bff.company.feign.CompanyBlacklistCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.company.service.CompanyBlacklistService;

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
        return companyBlacklistCore.selectBlackList(blackListFrom);
    }
}
