package cn.fintecher.supply.finance.loan.manager.service.confirming.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;

/**
 * @author ZhangYaJun
 * @Title: PlatformSigningService
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/30 0030下午 15:43
 */
public interface PlatformSigningService {
   Message searchPlatformSigningList(SearchDebitAndCredit searchDebitAndCredit);

   Message searchPlatformSigningDetail(Long pid);

   Message isUpLoadContract(Long pid);
}
