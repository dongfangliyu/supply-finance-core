package cn.fintecher.supply.finance.loan.manager.service.confirming.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: DebitAndCreditService
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/28 0028下午 15:33
 */
public interface DebitAndCreditService {


   Message searchDebitAndCreditList(SearchDebitAndCredit searchDebitAndCredit);

   Message queryOrderInfoByPid(String pid);

   Message submitDebitAndCredit(SubmitDebitAndCredit submitDebitAndCredit);

   Message upload(MultipartFile file, String type, String registerCode, Long id);

   Message isUpLoadContract(Long pid);

   Message<List<BusinessFileEntity>> searchAllAuditFileByOwnerId(String pid);

   Message searchMaiginAccount(Long pid);

   Message submitDebitAndCreditInfo(Long pid, String STATUS);
}
