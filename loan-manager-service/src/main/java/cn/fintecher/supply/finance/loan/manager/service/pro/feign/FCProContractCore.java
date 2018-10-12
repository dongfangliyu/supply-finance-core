package cn.fintecher.supply.finance.loan.manager.service.pro.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProContractForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProCategoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProContractEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProFileEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/10 0010下午 6:06
 */
@FeignClient(name = "loan-manager-core")
public interface FCProContractCore {

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/searchListContractCount", method = RequestMethod.POST)
    int searchListContractCount(@RequestBody ProContractForm proContractForm);

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/searchListContract", method = RequestMethod.POST)
    List<ProContractEntity> searchListContract(@RequestBody ProContractForm proContractForm);

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/submitContract", method = RequestMethod.POST)
    void submitContract(@RequestBody ProContractEntity proContractEntity);

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/searchContract", method = RequestMethod.GET)
    ProContractEntity searchContract(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/searchProFile", method = RequestMethod.GET)
    List<ProFileEntity> searchProFile(@RequestParam(value = "resourceCode") String resourceCode);

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/updateProContractEntity", method = RequestMethod.POST)
    void updateProContractEntity(@RequestBody ProContractEntity proContractEntity);

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/searchCategoryListByParentId", method = RequestMethod.GET)
    List<ProCategoryEntity> searchCategoryListByParentId(@RequestParam(value = "parentId") String parentId);

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/searchProFileById", method = RequestMethod.GET)
    ProFileEntity searchProFileById(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/saveProFile", method = RequestMethod.POST)
    Message saveProFile(@RequestBody ProFileEntity proFileEntity);

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/searchCountOfContract", method = RequestMethod.GET)
    long searchCountOfContract();

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/searchCategoryByPid", method = RequestMethod.GET)
    ProCategoryEntity searchCategoryByPid(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/searchContractFile", method = RequestMethod.GET)
    ProFileEntity searchContractFile(@RequestParam(value = "type") String type, @RequestParam(value = "resourceCode") String resourceCode);

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/updateContractFile", method = RequestMethod.POST)
    void updateContractFile(@RequestBody ProFileEntity proFileEntity);

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/searchCategoryByCategory", method = RequestMethod.GET)
    ProCategoryEntity searchCategoryByCategory(@RequestParam("category") String category);

    @ResponseBody
    @RequestMapping(value ="/pro/contractCore/searchContractListByCompanyId", method = RequestMethod.GET)
    List<ProContractEntity> searchContractListByCompanyId(@RequestParam("companyId")Long companyId);
}
