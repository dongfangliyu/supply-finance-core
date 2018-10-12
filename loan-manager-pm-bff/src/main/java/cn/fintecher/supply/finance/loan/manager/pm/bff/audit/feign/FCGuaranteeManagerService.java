package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditFrontGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditManagerListForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author WuTianJuan
 * @Date Created in 20:13 2018/7/17
 */

@FeignClient(name = "loan-manager-service")
public interface FCGuaranteeManagerService {

    @ResponseBody
    @RequestMapping(value = "/audit/auditGuarantee/searchGuaranteeList", method = RequestMethod.POST)
    Message searchGuaranteeList(AuditGuaranteeListForm auditGuaranteeListForm);

    @ResponseBody
    @RequestMapping(value = "/audit/auditGuarantee/submitResult", method = RequestMethod.POST)
    Message submitResult(AuditManagerListForm auditManagerListForm);

    @ResponseBody
    @RequestMapping(value = "/audit/auditGuarantee/searchFrontGuaranteeList", method = RequestMethod.POST)
    Message searchFrontGuaranteeList(AuditFrontGuaranteeListForm auditFrontGuaranteeListForm);

    @ResponseBody
    @RequestMapping(value = "/audit/auditGuarantee/searchFrontDetail", method = RequestMethod.GET)
    Message searchFrontDetail(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/audit/auditGuarantee/submitFrontResult", method = RequestMethod.POST)
    Message submitFrontResult(AuditManagerListForm auditManagerListForm);

    @ResponseBody
    @RequestMapping(value = "/audit/auditGuarantee/searchAuditCompanyInfo", method = RequestMethod.GET)
    Message searchAuditCompanyInfo(@RequestParam(value = "id") String id);

    @ResponseBody
    @RequestMapping(value = "/audit/auditGuarantee/searchAuditCompanyId", method = RequestMethod.GET)
    AuditCompanyEntity searchAuditCompanyId(@RequestParam(value = "id") String id);

    @ResponseBody
    @RequestMapping(value = "/audit/auditGuarantee/searchDetail", method = RequestMethod.GET)
    Message searchDetail(@RequestParam(value = "pid") Long pid);
}
