package cn.fintecher.supply.finance.loan.manager.service.credit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.CompanyCreditInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.form.EnterpriseFileForm;
import cn.fintecher.supply.finance.loan.manager.common.form.TableValidateForm;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author WuTianJuan
 * @Date Created in 10:49 2018/6/21
 */
@FeignClient(name = "loan-manager-core")
public interface FCEnterpriseFinancialCore {

    @RequestMapping(value = "/financial/file/insert", method = RequestMethod.POST)
    Message<CompanyFileEntity> insert(@RequestBody CompanyFileEntity companyFileEntity);

    @ResponseBody
    @RequestMapping(value ="/enterprise/financial/submitAccountingStatementInfo", method = RequestMethod.POST)
    Message submitAccountingStatementInfo();

    @ResponseBody
    @RequestMapping(value ="/financial/file/searchAccountingStatementInfo", method = RequestMethod.GET)
    List<CompanyFileEntity> searchAccountingStatementInfo(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/enterprise/financial/selectAllCategory", method = RequestMethod.GET)
    Message selectAllCategory(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/financial/file/searchAllFile", method = RequestMethod.GET)
    List<CompanyFileEntity> searchAllFile(@RequestParam(value ="pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/financial/file/searchAllFileByFid", method = RequestMethod.GET)
    CompanyFileEntity searchAllFileByFid(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/financial/file/deleteCompanyCreditDoc", method = RequestMethod.GET)
    Message deleteCompanyCreditDoc(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/financial/file/searchAllFileInfo", method = RequestMethod.GET)
    CompanyFileEntity searchAllFileInfo(@RequestParam(value = "pid") Long pid,@RequestParam(value="type") String type,@RequestParam(value="year",required = false) String year);

    @ResponseBody
    @RequestMapping(value ="/financial/file/updateCompanyFile", method = RequestMethod.POST)
    void updateCompanyFile(@RequestBody CompanyFileEntity companyFileEntity);

//    @ResponseBody
//    @RequestMapping(value ="/register/file/searchRegisterFileByOwnerId", method = RequestMethod.GET)
//    List<RegisterFileEntity> searchRegisterFile(Long pid);
}
