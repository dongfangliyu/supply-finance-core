package cn.fintecher.supply.finance.loan.manager.core.credit.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchCompanyCreditForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCreditInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.response.CompanyCreditResultResponse;
import cn.fintecher.supply.finance.loan.manager.core.audit.controller.AuditCompanyController;
import cn.fintecher.supply.finance.loan.manager.core.audit.controller.GuaranteeManagerController;
import cn.fintecher.supply.finance.loan.manager.core.credit.service.EnterpriseCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EnterpriseCreditController
 * @author wutianjuan
 * @date 2018-06-15 11:25:23
 */
@RestController
@RequestMapping("/credit/enterprise")
public class EnterpriseCreditController {

    @Autowired
    private EnterpriseCreditService enterpriseCreditService;

    @Autowired
    private GuaranteeManagerController guaranteeManagerController;

    @Autowired
    private AuditCompanyController auditCompanyController;

    @ResponseBody
    @RequestMapping(value = "/searchCompanyCreditStatus", method = RequestMethod.POST)
    public CompanyEnterpriseEntity searchCompanyCreditStatus (@RequestBody SearchCompanyCreditForm searchCompanyCreditForm){
        return enterpriseCreditService.searchCompanyCreditStatus(searchCompanyCreditForm);
    }

    @ResponseBody
    @RequestMapping(value = "/updateCompanyCreditStep", method = RequestMethod.POST)
    public Message updateCompanyCreditStep(@RequestBody CompanyEnterpriseEntity companyEnterpriseEntity){
        return enterpriseCreditService.updateCompanyCreditStep(companyEnterpriseEntity);
    }

    @ResponseBody
    @RequestMapping(value = "/searchCompanyCreditResult", method = RequestMethod.GET)
    public Message searchCompanyCreditResult(@RequestParam(value="pid") Long pid) {
        Message msg = new Message();
        Map result = new HashMap();
        CompanyCreditResultResponse response =enterpriseCreditService.searchCompanyCreditResult(pid);
        AuditCompanyEntity auditCompanyEntity = guaranteeManagerController.searchAuditCompanyId(pid.toString());
        if(null!=auditCompanyEntity && null !=auditCompanyEntity.getPid()) {
            Long auditId = auditCompanyEntity.getPid();//审核id
            List<AuditCreditInfoEntity> list = auditCompanyController.searchCreditInfo(auditId);
            if(list.size()!=0 && !("" .equals(list.get(0).getAmount()))){
              String amount = list.get(0).getAmount();
                String replace = amount.replace(",", "");
                BigDecimal bigDecimal = new BigDecimal(replace);
                double v = bigDecimal.doubleValue();
                response.setCredit_price(v);
            }
            result.put("result", response);
            msg.setMessage(result);
        }else{
            result.put("result", response);
            msg.setMessage(result);
        }
        msg.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return msg;
    }

    @ResponseBody
    @RequestMapping(value = "/searchCompanyInfo", method = RequestMethod.GET)
    public CompanyEnterpriseEntity searchCompanyInfo(Long pid){
        return enterpriseCreditService.searchCompanyInfo(pid);
    }

    @ResponseBody
    @RequestMapping(value ="/applyCompanyCredit", method = RequestMethod.POST)
    public Boolean applyCompanyCredit(@RequestBody AuditCompanyEntity entity){
        return enterpriseCreditService.applyCompanyCredit(entity);
    }

    @ResponseBody
    @RequestMapping(value ="/searchCountOfCredit", method = RequestMethod.GET)
    public Long searchCountOfCredit(){
        return enterpriseCreditService.searchCountOfCredit();
    }

}

