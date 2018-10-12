package cn.fintecher.supply.finance.loan.manager.service.pro.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProContractForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProContractEntity;
import cn.fintecher.supply.finance.loan.manager.service.pro.service.ProContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gonghebin
 * @date 2018/7/10 0010下午 12:01
 */
@RestController
@RequestMapping("/pro/contractService")
public class ProContractController {

    @Autowired
    private ProContractService proContractService;

    @ResponseBody
    @RequestMapping(value = "/searchListContract", method = RequestMethod.POST)
    public Message searchListContract(@RequestBody ProContractForm proContractForm){
        return proContractService.searchListContract(proContractForm);
    }

    @ResponseBody
    @RequestMapping(value = "/submitContract", method = RequestMethod.POST)
    public Message submitContract(@RequestBody ProContractEntity proContractEntity){
        return proContractService.submitContract(proContractEntity);
    }

    @ResponseBody
    @RequestMapping(value = "/searchContract", method = RequestMethod.GET)
    public Message searchContract(@RequestParam(value = "pid") String pid){
        return proContractService.searchContract(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteContract", method = RequestMethod.GET)
    public Message deleteContract(@RequestParam(value = "pid") String pid){
        return proContractService.deleteContract(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/updateContract", method = RequestMethod.POST)
    public Message updateContract(@RequestBody ProContractEntity proContractEntity){
        return proContractService.updateContract(proContractEntity);
    }

    @ResponseBody
    @RequestMapping(value = "/disableContract", method = RequestMethod.GET)
    public Message disableContract(@RequestParam(value = "pid") String pid){
        return proContractService.disableContract(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/searchProductListContract", method = RequestMethod.GET)
    public Message searchProductListContract(@RequestParam(value = "pid") String pid){
        return proContractService.searchProductListContract(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/downContract", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downContract(@RequestParam(value = "pid") String pid){
        return proContractService.downContract(pid);
    }

    @ResponseBody
    @RequestMapping(value ="/uploadContractDoc", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Message uploadContractDoc(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "resourceCode") String resourceCode){
        return proContractService.uploadContractDoc(file,type,resourceCode);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteContractFile", method = RequestMethod.GET)
    public Message deleteContractFile(@RequestParam(value = "type") String type, @RequestParam(value = "resourceCode") String resourceCode){
        return proContractService.deleteContractFile(type,resourceCode);
    }

    @ResponseBody
    @RequestMapping(value = "/searchContractListByCategory", method = RequestMethod.GET)
    public Message searchContractListByCategory(@RequestParam(value = "category") String category){
        return proContractService.searchContractListByCategory(category);
    }

    @ResponseBody
    @RequestMapping(value = "/searchContractListByCompanyId", method = RequestMethod.GET)
    public Message searchContractListByCompanyId(@RequestParam(value = "companyId") Long companyId){
        return proContractService.searchContractListByCompanyId(companyId);
    }
}
