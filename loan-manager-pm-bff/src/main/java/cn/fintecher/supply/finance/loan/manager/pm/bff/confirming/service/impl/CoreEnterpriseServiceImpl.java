package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.AuditAndDetailResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.feign.FCCoreEnterpriseCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.CoreEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: CoreEnterpriseServiceImpl
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/30 0030下午 14:55
 */
@Service
public class CoreEnterpriseServiceImpl implements CoreEnterpriseService {

   @Autowired
   private FCCoreEnterpriseCore fcCoreEnterpriseCore;

   @Override
   public Message searchCoreEnterpriseList(SearchDebitAndCredit searchDebitAndCredit) {
      return fcCoreEnterpriseCore.searchCoreEnterpriseList(searchDebitAndCredit);
   }

   @Override
   public Message<AuditAndDetailResponse> searchCoreEnterpriseDetail(Long pid) {
      return fcCoreEnterpriseCore.searchCoreEnterpriseDetail(pid);
   }

   @Override
   public Message submitCoreEnterpriseInfo(Long pid) {
      return fcCoreEnterpriseCore.submitCoreEnterpriseInfo(pid);
   }

   @Override
   public Message isUpLoadContract(Long pid) {
      return fcCoreEnterpriseCore.isUpLoadContract(pid);
   }

   @Override
   public Message upload(MultipartFile file, String type, String code, Long id) {

      return fcCoreEnterpriseCore.upload(file, type, code, id);
   }

   @Override
   public Message<List<BusinessFileEntity>> searchCoreEnterpriseMoreType(String pid) {
      return fcCoreEnterpriseCore.searchCoreEnterpriseMoreType(pid);
   }


}
