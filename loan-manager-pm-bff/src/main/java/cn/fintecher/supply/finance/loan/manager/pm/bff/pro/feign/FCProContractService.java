package cn.fintecher.supply.finance.loan.manager.pm.bff.pro.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.ProContractForm;
import cn.fintecher.supply.finance.loan.manager.common.model.ProContractEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gonghebin
 * @date 2018/7/10 0010上午 11:59
 */
@FeignClient(name = "loan-manager-service")
public interface FCProContractService {

    @ResponseBody
    @RequestMapping(value = "/pro/contractService/searchListContract", method = RequestMethod.POST)
    Message searchListContract(@RequestBody ProContractForm proContractForm);

    @ResponseBody
    @RequestMapping(value = "/pro/contractService/submitContract", method = RequestMethod.POST)
    Message submitContract(@RequestBody ProContractEntity proContractEntity);

    @ResponseBody
    @RequestMapping(value = "/pro/contractService/searchContract", method = RequestMethod.GET)
    Message searchContract(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/pro/contractService/deleteContract", method = RequestMethod.GET)
    Message deleteContract(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/pro/contractService/updateContract", method = RequestMethod.POST)
    Message updateContract(@RequestBody ProContractEntity proContractEntity);

    @ResponseBody
    @RequestMapping(value = "/pro/contractService/disableContract", method = RequestMethod.GET)
    Message disableContract(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/pro/contractService/searchProductListContract", method = RequestMethod.GET)
    Message searchProductListContract(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/pro/contractService/downContract", method = RequestMethod.GET)
    ResponseEntity<byte[]> downContract(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value ="/pro/contractService/uploadContractDoc", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Message uploadContractDoc(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type,@RequestParam(value = "resourceCode") String resourceCode);

    @ResponseBody
    @RequestMapping(value = "/pro/contractService/deleteContractFile", method = RequestMethod.GET)
    Message deleteContractFile(@RequestParam(value = "type") String type, @RequestParam(value = "resourceCode") String resourceCode);

    @ResponseBody
    @RequestMapping(value = "/pro/contractService/searchContractListByCategory", method = RequestMethod.GET)
    Message searchContractListByCategory(@RequestParam(value = "category") String category);

    @ResponseBody
    @RequestMapping(value = "/pro/contractService/searchContractListByCompanyId", method = RequestMethod.GET)
    Message searchContractListByCompanyId(@RequestParam(value = "companyId") Long companyId);
}
