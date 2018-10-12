package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.AuditAndDetailResponse;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: PlatformSigningService
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/30 0030下午 15:35
 */
public interface PlatformSigningService {
   Message searchPlatformSigningList(SearchDebitAndCredit searchDebitAndCredit);


   Message<AuditAndDetailResponse> searchPlatformSigningDetail(Long pid);

   Message<SubmitDebitAndCredit> searchMaiginAccount(Long pid);

   Message submitPlatformSigningInfo(Long pid);

   Message isUpLoadContract(Long pid);

   Message<List<BusinessFileEntity>> searchDebitAndCreditMoreType(String pid);
}
