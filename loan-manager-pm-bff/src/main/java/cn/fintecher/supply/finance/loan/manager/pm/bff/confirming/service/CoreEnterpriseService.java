package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.AuditAndDetailResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: CoreEnterpriseService
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/30 0030下午 14:54
 */
public interface CoreEnterpriseService {
   Message searchCoreEnterpriseList(SearchDebitAndCredit searchDebitAndCredit);

   Message<AuditAndDetailResponse> searchCoreEnterpriseDetail(Long pid);

   Message submitCoreEnterpriseInfo(Long pid);

   Message isUpLoadContract(Long pid);

   Message upload(MultipartFile file, String type, String code, Long id);

   Message<List<BusinessFileEntity>> searchCoreEnterpriseMoreType(String pid);
}
