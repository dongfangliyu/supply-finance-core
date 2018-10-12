package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/4 11:14
 */
@FeignClient(name = "loan-manager-service")
public interface FCConfirmingStockApplyService {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/loan/confirmingStockPageList", method = RequestMethod.POST)
    Message<PageResponse<List<ConfirmingStockInfoListResponse>>> confirmingStockPageList(@RequestBody ConfirmingStockInfoResquest confirmingStockInfoResquest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/loan/saveConfirmingStockinfo", method = RequestMethod.POST)
    Message saveConfirmingStockinfo(@RequestBody ConfirmingStockInfoForm confirmingStockInfoForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/loan/submitConfirmingStockinfo", method = RequestMethod.POST)
    Message submitConfirmingStockinfo(@RequestBody ConfirmingStockInfoForm confirmingStockInfoForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/loan/upload", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Message upload(@RequestPart("file")MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "code")String code);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/loan/getDetailById", method = RequestMethod.GET)
    Message<ConfirmingStockInfoDetailResponse> getDetailById(@RequestParam(value = "pid")Long pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/confirming/loan/getCompany", method = RequestMethod.GET)
    Message<CompanyEnterpriseEntity> getCompany(@RequestParam(value = "name")String name);


}
