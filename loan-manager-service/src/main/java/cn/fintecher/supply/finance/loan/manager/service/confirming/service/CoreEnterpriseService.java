package cn.fintecher.supply.finance.loan.manager.service.confirming.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;

/**
 * @author ZhangYaJun
 * @Title: CoreEnterpriseService
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/30 0030下午 15:03
 */
public interface CoreEnterpriseService {

   Message searchCoreEnterpriseList(SearchDebitAndCredit searchDebitAndCredit);

   Message isUpLoadContract(Long pid);

   Message submitDebitAndCreditInfo(Long pid, String status);
}
