package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SearchDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.response.AuditAndDetailResponse;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ZhangYaJun
 * @Title: CoreEnterpriseCore
 * @ProjectName supply-finance
 * @Description:
 * @date 2018/8/30 0030下午 14:59
 */

@FeignClient(name = "loan-manager-service")
public interface FCCoreEnterpriseCore {

   @ResponseBody
   @RequestMapping(value = "/confirming/CoreEnterpriseService/searchCoreEnterpriseList", method = RequestMethod.POST)
   Message searchCoreEnterpriseList(@RequestBody SearchDebitAndCredit searchDebitAndCredit);


   @ResponseBody
   @RequestMapping(value = "/confirming/CoreEnterpriseService/searchCoreEnterpriseDetail", method = RequestMethod.GET)
   Message<AuditAndDetailResponse> searchCoreEnterpriseDetail(@RequestParam(value = "pid") Long pid);


   @ResponseBody
   @RequestMapping(value = "/confirming/CoreEnterpriseService/submitCoreEnterpriseInfo", method = RequestMethod.GET)
   Message submitCoreEnterpriseInfo(@RequestParam(value = "pid") Long pid);


   @ResponseBody
   @RequestMapping(value = "/confirming/CoreEnterpriseService/isUpLoadContract", method = RequestMethod.GET)
   Message isUpLoadContract(@RequestParam(value = "pid") Long pid);

   @Headers("Accept2: application/json")
   @ResponseBody
   @RequestMapping(value = "/confirming/CoreEnterpriseService/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   Message upload(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "registerCode") String registerCode, @RequestParam(value = "id") Long id);


   @ResponseBody
   @RequestMapping(value = "/confirming/CoreEnterpriseService/searchCoreEnterpriseMoreType", method = RequestMethod.GET)
   Message<List<BusinessFileEntity>> searchCoreEnterpriseMoreType(@RequestParam(value = "pid") String pid);


}
