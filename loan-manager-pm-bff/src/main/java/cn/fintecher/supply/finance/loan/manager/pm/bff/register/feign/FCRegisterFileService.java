package cn.fintecher.supply.finance.loan.manager.pm.bff.register.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author gonghebin
 * @date 2018/6/27 0027上午 11:47
 */
@FeignClient(name = "loan-manager-service")
public interface FCRegisterFileService {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value ="/register/file/uploadIdentityFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Message uploadIdentityFile(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type,@RequestParam(value = "registerCode") String registerCode);

    @ResponseBody
    @RequestMapping(value ="/register/file/searchRegisterFile", method = RequestMethod.GET)
    Message searchRegisterFile(@RequestParam(value = "type") String type,@RequestParam(value = "registerCode") String registerCode);

    @ResponseBody
    @RequestMapping(value ="/register/file/deleteRegisterFile", method = RequestMethod.GET)
    Message deleteRegisterFile(@RequestParam(value = "type") String type,@RequestParam(value = "registerCode") String registerCode);

    @ResponseBody
    @RequestMapping(value ="/register/file/donwloadEntrustment", method = RequestMethod.GET)
    ResponseEntity<byte[]> donwloadEntrustment(@RequestParam(value = "category") String category);

    @ResponseBody
    @RequestMapping(value ="/register/file/donwloadRegisterIDCard", method = RequestMethod.GET)
    ResponseEntity<byte[]> donwloadRegisterIDCard(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/register/file/searchBaseTemplateDictionaryByCode", method = RequestMethod.GET)
    BaseTemplateDictionaryEntity searchBaseTemplateDictionaryByCode(@RequestParam(value = "category") String category);
}
