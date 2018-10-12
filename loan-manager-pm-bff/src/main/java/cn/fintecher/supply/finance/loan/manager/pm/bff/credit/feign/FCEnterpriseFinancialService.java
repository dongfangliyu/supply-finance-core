package cn.fintecher.supply.finance.loan.manager.pm.bff.credit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.EnterpriseFileForm;
import cn.fintecher.supply.finance.loan.manager.common.form.TableValidateForm;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description:
 * @Author WuTianJuan
 * @Date Created in 10:49 2018/6/21
 */
@FeignClient(name = "loan-manager-service")
public interface FCEnterpriseFinancialService {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value ="/enterprise/financial/uploadCompanyCreditDoc", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Message uploadCompanyCreditDoc(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value="year") String year,@RequestParam(value ="pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/enterprise/financial/donwloadCompanyCreditDoc", method = RequestMethod.GET)
    ResponseEntity<byte[]> donwloadCompanyCreditDoc(@RequestParam(value = "pid") Long pid,@RequestParam(value="type") String type,@RequestParam(value="year",required = false) String year);

    @ResponseBody
    @RequestMapping(value ="/enterprise/financial/deleteCompanyCreditDoc", method = RequestMethod.GET)
    Message deleteCompanyCreditDoc(@RequestParam(value ="pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/enterprise/financial/submitAccountingStatementInfo", method = RequestMethod.POST)
    Message submitAccountingStatementInfo(@RequestParam(value ="pid") Long pid,@RequestParam(value = "code") String code);

    @ResponseBody
    @RequestMapping(value ="/enterprise/financial/submitCompanyCreditImageInfo", method = RequestMethod.POST)
    Message submitCompanyCreditImageInfo(@RequestParam(value ="pid") Long pid,@RequestParam(value = "code") String code);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value ="/enterprise/financial/searchAccountingStatementInfo", method = RequestMethod.GET)
    List<CompanyFileEntity> searchAccountingStatementInfo(@RequestParam(value = "pid") Long pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value ="/file/table/fileTableValidate", method = RequestMethod.GET)
    Message fileTableValidate(@RequestBody TableValidateForm tableValidateForm);

    @ResponseBody
    @RequestMapping(value ="/enterprise/financial/searchRegisterFile", method = RequestMethod.GET)
    List<RegisterFileEntity> searchRegisterFile(@RequestParam(value ="pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/enterprise/financial/donwloadCompanyCreditDocByFid", method = RequestMethod.GET)
    ResponseEntity<byte[]> donwloadCompanyCreditDocByFid(@RequestParam(value ="pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/enterprise/financial/deletePledgeEnterpriseFile", method = RequestMethod.GET)
    Message deletePledgeEnterpriseFile(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value ="/enterprise/financial/uploadPledgeEnterpriseFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Message uploadPledgeEnterpriseFile(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type,@RequestParam(value = "enterpriseId") Long enterpriseId);
}
