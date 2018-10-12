package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditSigningDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.AuditAndDetailResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.feign.FcDebitAndCreditService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.DebitAndCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: DebitAndCreditServiceImpl
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/28 0028下午 14:59
 */
@Service
public class DebitAndCreditServiceImpl implements DebitAndCreditService {

   @Autowired
   private FcDebitAndCreditService FcDebitAndCreditService;

   @Override
   public Message searchDebitAndCreditList(SearchDebitAndCredit searchDebitAndCredit) {
      return FcDebitAndCreditService.searchDebitAndCreditList(searchDebitAndCredit);
   }

   @Override
   public Message<AuditAndDetailResponse> searchSigningDetail(Long pid) {
      return FcDebitAndCreditService.queryOrderInfoByPid(pid.toString());
   }

   @Override
   public Message submitDebitAndCredit(SubmitDebitAndCredit submitDebitAndCredit) {


      return FcDebitAndCreditService.submitDebitAndCredit(submitDebitAndCredit);
   }

   @Override
   public Message upload(MultipartFile file, String type, String registerCode, Long id) {

      return FcDebitAndCreditService.upload(file, type, registerCode, id);
   }

   @Override
   public Message isUpLoadContract(Long pid) {
      return FcDebitAndCreditService.isUpLoadContract(pid);
   }

   @Override
   public Message<List<BusinessFileEntity>> searchDebitAndCreditMoreType(String pid) {
      return FcDebitAndCreditService.searchAllAuditFileByOwnerId(pid);
   }

   @Override
   public Message searchMaiginAccount(Long pid) {
      return FcDebitAndCreditService.searchMaiginAccount(pid);
   }

   @Override
   public Message submitDebitAndCreditInfo(Long pid) {

      return FcDebitAndCreditService.submitDebitAndCreditInfo(pid);
   }
}
