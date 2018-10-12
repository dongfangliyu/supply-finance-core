package cn.fintecher.supply.finance.loan.manager.core.confirming.service;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.ConfirmingStockList;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;

import java.util.List;

/**
 * 保兑仓表
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-28 16:02:16
 */
public interface ConfirmingStockInfoService {

   /**
    * 新增保兑仓表
    *
    * @param
    * @return
    */
   Integer insertStockInfo(ConfirmingStockInfoEntity confirmingStockInfoEntity);

   /**
    * 查询保兑仓表
    *
    * @param
    * @return
    */
   List<ConfirmingStockInfoEntity> selectByStockInfo(ConfirmingStockInfoEntity confirmingStockInfoEntity);

   /**
    * 修改保兑仓表
    *
    * @param
    */
   Integer updateStockInfo(ConfirmingStockInfoEntity confirmingStockInfoEntity);

   /**
    * 根据主键查询保兑仓表
    *
    * @param
    * @return
    */
   ConfirmingStockInfoEntity queryStockInfoByPid(String pid);

   Message<List<ConfirmingStockList>> searchDebitAndCreditList(SearchDebitAndCredit searchDebitAndCredit);

   Message<Integer> qureyPageCount(SearchDebitAndCredit searchDebitAndCredit);

   Message<ConfirmingStockInfoEntity> queryOrderInfoByPid(String pid);

   BaseBankInfoEntity queryConfirmingBankInfo(Long companyDealerId, String objectId);

   List<BusinessFileEntity> searchAllFileByOwnerId(Long pid);

   Message<SubmitDebitAndCredit> searchMaiginAccount(Long companyDealerId, String objectType);

   List<ConfirmingStockInfoListResponse> loanConfirmingStockInfoList(ConfirmingStockInfoResquest confirmingStockInfoResquest);

   Message<Integer> countLoanConfirmingStockInfoList(ConfirmingStockInfoResquest confirmingStockInfoResquest);

   ConfirmingStockInfoEntity getConfirmingStockInfoById(Long pid);

   Message<ConfirmingStockInfoEntity> saveOrUpdateConfirmingStockInfo(ConfirmingStockInfoEntity confirmingStockInfoEntity);
}
