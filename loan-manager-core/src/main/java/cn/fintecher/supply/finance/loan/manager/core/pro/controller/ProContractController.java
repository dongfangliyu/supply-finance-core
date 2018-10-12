package cn.fintecher.supply.finance.loan.manager.core.pro.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProContractForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProCategoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProContractEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProFileEntity;
import cn.fintecher.supply.finance.loan.manager.core.pro.service.ProContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/10 0010上午 11:25
 */
@RestController
@RequestMapping("/pro/contractCore")
public class ProContractController {

    @Autowired
    private ProContractService proContractService;

    @ResponseBody
    @RequestMapping(value ="/searchListContractCount", method = RequestMethod.POST)
    public int searchListContractCount(@RequestBody ProContractForm proContractForm){
        return proContractService.searchListContractCount(proContractForm);
    }

    @ResponseBody
    @RequestMapping(value ="/searchListContract", method = RequestMethod.POST)
    public List<ProContractEntity> searchListContract(@RequestBody ProContractForm proContractForm){
        return proContractService.searchListContract(proContractForm);
    }

    @ResponseBody
    @RequestMapping(value ="/submitContract", method = RequestMethod.POST)
    public void submitContract(@RequestBody ProContractEntity proContractEntity){
        proContractService.submitContract(proContractEntity);
    }

    @ResponseBody
    @RequestMapping(value ="/searchContract", method = RequestMethod.GET)
    public ProContractEntity searchContract(@RequestParam(value = "pid") String pid){
        return proContractService.searchContract(pid);
    }

    @ResponseBody
    @RequestMapping(value ="/searchProFile", method = RequestMethod.GET)
    public List<ProFileEntity> searchProFile(@RequestParam(value = "resourceCode") String resourceCode){
        return proContractService.searchProFile(resourceCode);
    }

    @ResponseBody
    @RequestMapping(value ="/updateProContractEntity", method = RequestMethod.POST)
    public void updateProContractEntity(@RequestBody ProContractEntity proContractEntity){
        proContractService.updateProContractEntity(proContractEntity);
    }

    @ResponseBody
    @RequestMapping(value ="/searchCategoryListByParentId", method = RequestMethod.GET)
    public List<ProCategoryEntity> searchCategoryListByParentId(@RequestParam(value = "parentId") String parentId){
        return proContractService.searchCategoryListByParentId(parentId);
    }

    @ResponseBody
    @RequestMapping(value ="/searchProFileById", method = RequestMethod.GET)
    public ProFileEntity searchProFileById(@RequestParam(value = "pid") String pid){
        return proContractService.searchProFileById(pid);
    }

    @ResponseBody
    @RequestMapping(value ="/saveProFile", method = RequestMethod.POST)
    public Message saveProFile(@RequestBody ProFileEntity proFileEntity){
        return proContractService.saveProFile(proFileEntity);
    }

    @ResponseBody
    @RequestMapping(value ="/searchCountOfContract", method = RequestMethod.GET)
    public long searchCountOfContract(){
        return proContractService.searchCountOfContract();
    }

    @ResponseBody
    @RequestMapping(value ="/searchCategoryByPid", method = RequestMethod.GET)
    public ProCategoryEntity searchCategoryByPid(@RequestParam(value = "pid") String pid){
        return proContractService.searchCategoryByPid(pid);
    }

    @ResponseBody
    @RequestMapping(value ="/searchContractFile", method = RequestMethod.GET)
    public ProFileEntity searchContractFile(@RequestParam(value = "type") String type, @RequestParam(value = "resourceCode") String resourceCode){
        return proContractService.searchContractFile(type,resourceCode);
    }

    @ResponseBody
    @RequestMapping(value ="/updateContractFile", method = RequestMethod.POST)
    public void updateContractFile(@RequestBody ProFileEntity proFileEntity){
        proContractService.updateContractFile(proFileEntity);
    }

    @ResponseBody
    @RequestMapping(value ="/searchCategoryByCategory", method = RequestMethod.GET)
    public ProCategoryEntity searchCategoryByCategory(@RequestParam("category") String category){
        return proContractService.searchCategoryByCategory(category);
    }

    @ResponseBody
    @RequestMapping(value ="/searchContractListByCompanyId", method = RequestMethod.GET)
    public List<ProContractEntity> searchContractListByCompanyId(@RequestParam("companyId")Long companyId){
        return proContractService.searchContractListByCompanyId(companyId);
    }

}
