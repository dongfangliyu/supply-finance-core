package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockFrom;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.feign.PledgeSigningCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.PledgeSigningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pledgeSigningService")
public class PledgeSigningServiceImpl implements PledgeSigningService {

    @Autowired
    private PledgeSigningCore pledgeSigningCore;

    @Override
    public Message selectAdminSigningList(PledgeStockFrom pledgeStockFrom) {
        return pledgeSigningCore.selectAdminSigningList(pledgeStockFrom);
    }

    @Override
    public Message selectWebSigningList(PledgeStockFrom pledgeStockFrom) {
        return pledgeSigningCore.selectWebSigningList(pledgeStockFrom);
    }

    @Override
    public Message selectAdminSigningDetail(Long id) {
        return pledgeSigningCore.selectAdminSigningDetail(id);
    }

    @Override
    public Message selectWebSigningDetail(Long id) {
        return pledgeSigningCore.selectWebSigningDetail(id);
    }

    @Override
    public Message submitAdminSigning(Long id) {
        return pledgeSigningCore.submitAdminSigning(id);
    }

    @Override
    public Message submitWebSigning(Long id) {
        return pledgeSigningCore.submitWebSigning(id);
    }

    @Override
    public Message selectPaperContract(Long id) {
        return pledgeSigningCore.selectPaperContract(id);
    }

    @Override
    public Message selectContractMoreType(Long id) {
        return pledgeSigningCore.selectContractMoreType(id);
    }
}
