package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.AuditAndDetailResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.feign.FCPlatformSigningCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.PlatformSigningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: PlatformSigningServiceImp
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/30 0030下午 15:36
 */
@Service
public class PlatformSigningServiceImp implements PlatformSigningService {

   @Autowired
   private FCPlatformSigningCore fcPlatformSigningCore;

   @Override
   public Message searchPlatformSigningList(SearchDebitAndCredit searchDebitAndCredit) {
      return fcPlatformSigningCore.searchPlatformSigningList(searchDebitAndCredit);
   }

   @Override
   public Message<AuditAndDetailResponse> searchPlatformSigningDetail(Long pid) {
      return fcPlatformSigningCore.searchPlatformSigningDetail(pid);
   }

   @Override
   public Message<SubmitDebitAndCredit> searchMaiginAccount(Long pid) {
      return fcPlatformSigningCore.searchMaiginAccount(pid);
   }

   @Override
   public Message submitPlatformSigningInfo(Long pid) {
      return fcPlatformSigningCore.submitPlatformSigningInfo(pid);
   }

   @Override
   public Message isUpLoadContract(Long pid) {
      return fcPlatformSigningCore.isUpLoadContract(pid);
   }

   @Override
   public Message<List<BusinessFileEntity>> searchDebitAndCreditMoreType(String pid) {
      return fcPlatformSigningCore.searchDebitAndCreditMoreType(pid);
   }


}
