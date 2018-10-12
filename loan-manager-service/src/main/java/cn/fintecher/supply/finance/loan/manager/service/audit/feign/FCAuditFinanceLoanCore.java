package cn.fintecher.supply.finance.loan.manager.service.audit.feign;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditSigningListForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 18:38 2018/7/23
 */
@FeignClient(name = "loan-manager-core")
public interface FCAuditFinanceLoanCore {

    @ResponseBody
    @RequestMapping(value ="/audit/auditOrder/searchSigningList", method = RequestMethod.POST)
    List<AuditOrderInfoEntity> searchSigningList(@RequestBody AuditSigningListForm auditSigningListForm);

    @ResponseBody
    @RequestMapping(value ="/audit/auditOrder/searchSignListCount", method = RequestMethod.POST)
    int searchSignListCount(@RequestBody  AuditSigningListForm auditSigningListForm);


}
