package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditSigningListForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author WuTianJuan
 * @Date Created in 18:33 2018/7/23
        */
@FeignClient(name = "loan-manager-service")
public interface FCAuditFinanceLoanService {

    @ResponseBody
    @RequestMapping(value ="/audit/auditOrder/searchSigningList", method = RequestMethod.POST)
    Message searchSigningList(@RequestBody AuditSigningListForm auditSigningListForm);

    @ResponseBody
    @RequestMapping(value ="/audit/auditOrder/searchSigningDetail", method = RequestMethod.GET)
    Message searchSigningDetail(@RequestParam(value="id") String id);
}
