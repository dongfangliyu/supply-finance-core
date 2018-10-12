package cn.fintecher.supply.finance.loan.manager.core.audit.controller;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditFrontGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.form.guarantee.AuditGuaranteeListForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.GuaranteeManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 20:30 2018/7/17
 */
@RestController
@RequestMapping("/audit/guarantee")
public class GuaranteeManagerController {
    @Autowired
    private GuaranteeManagerService guaranteeManagerService;

    @ResponseBody
    @RequestMapping(value = "/searchGuaranteeList", method = RequestMethod.POST)
    public List<AuditOrderInfoEntity> searchGuaranteeList(@RequestBody AuditGuaranteeListForm auditGuaranteeListForm){
        return guaranteeManagerService.searchGuaranteeList(auditGuaranteeListForm);
    }

    @ResponseBody
    @RequestMapping(value ="/searchGuaranteeListCount", method = RequestMethod.POST)
    public int searchGuaranteeListCount(@RequestBody AuditGuaranteeListForm auditGuaranteeListForm){
        return guaranteeManagerService.searchGuaranteeListCount(auditGuaranteeListForm);
    }

//    @ResponseBody
//    @RequestMapping(value ="/submitResult", method = RequestMethod.POST)
//    public Boolean submitResult(@RequestBody AuditManagerListForm auditManagerListForm){
//        return guaranteeManagerService.submitResult(auditManagerListForm);
//    }

    @ResponseBody
    @RequestMapping(value ="/searchOrderInfoById", method = RequestMethod.GET)
    public AuditOrderInfoEntity searchOrderInfoById(@RequestParam(value="pid") String pid){
        return guaranteeManagerService.searchOrderInfoById(pid);
    }

    @ResponseBody
    @RequestMapping(value ="/updateGuaranteeInfo", method = RequestMethod.POST)
    public Integer updateGuaranteeInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity){
        return guaranteeManagerService.updateGuaranteeInfo(auditOrderInfoEntity);
    }

    @ResponseBody
    @RequestMapping(value ="/searchFrontGuaranteeList", method = RequestMethod.POST)
    public List<AuditOrderInfoEntity> searchFrontGuaranteeList(@RequestBody AuditFrontGuaranteeListForm auditFrontGuaranteeListForm){
        return guaranteeManagerService.searchFrontGuaranteeList(auditFrontGuaranteeListForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchFrontGuaranteeListCount", method = RequestMethod.POST)
    public int searchFrontGuaranteeListCount(@RequestBody AuditFrontGuaranteeListForm auditFrontGuaranteeListForm){
        return guaranteeManagerService.searchFrontGuaranteeListCount(auditFrontGuaranteeListForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchAuditCompanyId", method = RequestMethod.GET)
    public AuditCompanyEntity searchAuditCompanyId(@RequestParam(value = "id") String id){
        return guaranteeManagerService.searchAuditCompanyId(id);
    }

    @ResponseBody
    @RequestMapping(value = "/searchBusinessOrederByOwnerId", method = RequestMethod.GET)
    public BusinessOrderEntity searchBusinessOrederByOwnerId(@RequestParam(value = "ownerId") String ownerId){
        return guaranteeManagerService.searchBusinessOrederByOwnerId(ownerId);
    }
}
