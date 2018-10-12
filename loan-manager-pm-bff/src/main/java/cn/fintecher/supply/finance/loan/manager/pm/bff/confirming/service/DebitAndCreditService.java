package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditSigningDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.AuditAndDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: DebitAndCreditService
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/28 0028下午 14:57
 */
public interface DebitAndCreditService {

   Message searchDebitAndCreditList(SearchDebitAndCredit searchDebitAndCredit);

   Message<AuditAndDetailResponse> searchSigningDetail(Long pid);

   Message submitDebitAndCredit(SubmitDebitAndCredit submitDebitAndCredit);

   Message upload(MultipartFile file, String type, String code, Long id);

   Message isUpLoadContract(Long pid);

   Message<List<BusinessFileEntity>> searchDebitAndCreditMoreType(String pid);

   Message searchMaiginAccount(Long pid);

   Message submitDebitAndCreditInfo(Long pid);
}
