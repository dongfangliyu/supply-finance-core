package cn.fintecher.supply.finance.loan.manager.service.audit.feign;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditFrontGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 20:24 2018/7/17
 */
@FeignClient(name = "loan-manager-core")
public interface FCGuaranteeManagerCore {

    @ResponseBody
    @RequestMapping(value ="/audit/guarantee/searchGuaranteeList", method = RequestMethod.POST)
    List<AuditOrderInfoEntity> searchGuaranteeList(@RequestBody AuditGuaranteeListForm auditGuaranteeListForm);

    @ResponseBody
    @RequestMapping(value ="/audit/guarantee/searchGuaranteeListCount", method = RequestMethod.POST)
    int searchGuaranteeListCount(@RequestBody AuditGuaranteeListForm auditGuaranteeListForm);

    @ResponseBody
    @RequestMapping(value ="/audit/guarantee/searchOrderInfoById", method = RequestMethod.GET)
    AuditOrderInfoEntity searchOrderInfoById(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value ="/audit/guarantee/updateGuaranteeInfo", method = RequestMethod.POST)
    Integer updateGuaranteeInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity);

    @ResponseBody
    @RequestMapping(value ="/audit/guarantee/searchFrontGuaranteeList", method = RequestMethod.POST)
    List<AuditOrderInfoEntity> searchFrontGuaranteeList(@RequestBody AuditFrontGuaranteeListForm auditFrontGuaranteeListForm);

    @ResponseBody
    @RequestMapping(value ="/audit/guarantee/searchFrontGuaranteeListCount", method = RequestMethod.POST)
    int searchFrontGuaranteeListCount(@RequestBody AuditFrontGuaranteeListForm auditFrontGuaranteeListForm);

    @ResponseBody
    @RequestMapping(value ="/audit/guarantee/searchAuditCompanyId", method = RequestMethod.GET)
    AuditCompanyEntity searchAuditCompanyId(@RequestParam(value = "id") String id);

    @ResponseBody
    @RequestMapping(value ="/audit/guarantee/searchBusinessOrederByOwnerId", method = RequestMethod.GET)
    BusinessOrderEntity searchBusinessOrederByOwnerId(@RequestParam(value = "ownerId") String ownerId);
}
