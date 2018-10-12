package cn.fintecher.supply.finance.loan.manager.core.credit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.CompanyCreditInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.core.credit.service.EnterpriseFinancialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 13:18 2018/6/21
 */
@RestController
@RequestMapping("/financial/file")
public class EnterpriseFinancialController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnterpriseFinancialController.class);

    @Autowired
    private EnterpriseFinancialService enterpriseFinancialService;

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Message<CompanyFileEntity> insert(@RequestBody CompanyFileEntity companyFileEntity) {
        return enterpriseFinancialService.insert(companyFileEntity);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCompanyCreditDoc", method = RequestMethod.GET)
    public Message deleteCompanyCreditDoc(@RequestParam(value = "pid") Long pid) {
        Message msg = new Message();
        msg.setMessage(enterpriseFinancialService.deleteCompanyCreditDoc(pid));
        msg.setCode(0);
        return msg;
    }

    @ResponseBody
    @RequestMapping(value ="/searchAccountingStatementInfo", method = RequestMethod.GET)
    public List<CompanyFileEntity> searchAccountingStatementInfo(@RequestParam(value = "pid") Long pid){
        return enterpriseFinancialService.searchAccountingStatementInfo(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/searchAllFileByFid", method = RequestMethod.GET)
    public CompanyFileEntity searchAllFileByFid(@RequestParam(value = "pid") Long pid){
        return enterpriseFinancialService.searchAllFileByFid(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/searchAllFileInfo", method = RequestMethod.GET)
    public CompanyFileEntity searchAllFileInfo(@RequestParam(value = "pid") Long pid,@RequestParam(value = "type") String type,@RequestParam(value="year",required = false) String year){
        return enterpriseFinancialService.searchAllFileInfo(pid,type,year);
    }

    @ResponseBody
    @RequestMapping(value ="/updateCompanyFile", method = RequestMethod.POST)
    public void updateCompanyFile(@RequestBody CompanyFileEntity companyFileEntity){
        enterpriseFinancialService.updateCompanyFile(companyFileEntity);
    }
}
