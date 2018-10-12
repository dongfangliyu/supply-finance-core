package cn.fintecher.supply.finance.loan.manager.core.confirming.dao;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.ConfirmingStockList;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 保兑仓表
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-28 16:02:16
 */
@Mapper
public interface ConfirmingStockInfoDao {

   Integer insertStockInfo(ConfirmingStockInfoEntity confirmingStockInfoEntity);

   List<ConfirmingStockInfoEntity> selectByStockInfo(ConfirmingStockInfoEntity confirmingStockInfoEntity);

   Integer updateStockInfo(ConfirmingStockInfoEntity confirmingStockInfoEntity);

   ConfirmingStockInfoEntity queryStockInfoByPid(String pid);


   List<ConfirmingStockList> searchDebitAndCreditList(SearchDebitAndCredit searchDebitAndCredit);

   Integer qureyPageCount(SearchDebitAndCredit searchDebitAndCredit);

   BaseBankInfoEntity queryCongirmingBankInfo(@Param("companyDealerId") Long companyDealerId, @Param("objectId") String objectId);

   List<BusinessFileEntity> searchAllFileByOwnerId(Long pid);

   List<SubmitDebitAndCredit> searchMaiginAccount(@Param("companyDealerId") Long companyDealerId, @Param("objectType") String objectType);

   List<ConfirmingStockInfoListResponse> loanConfirmingStockInfoList(ConfirmingStockInfoResquest confirmingStockInfoResquest);

   Integer countLoanConfirmingStockInfoList(ConfirmingStockInfoResquest confirmingStockInfoResquest);
}
