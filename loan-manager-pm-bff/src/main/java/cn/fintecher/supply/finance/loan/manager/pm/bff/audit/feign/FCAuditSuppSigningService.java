package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRepayBankInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

/**
 * @author wuxiaoxing
 * @time 2018/7/18 15:49
 */
@FeignClient(name = "loan-manager-service")
public interface FCAuditSuppSigningService {

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditSuppSigning/searchSigningList", method = RequestMethod.POST)
    Message searchSigningList(@RequestBody AuditSigningRequest auditSigningRequest);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditSuppSigning/searchSigningDetail", method = RequestMethod.GET)
    Message searchSigningDetail(@RequestParam("pid")Long pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditSuppSigning/submitSigningAccount", method = RequestMethod.POST)
    Message submitSigningAccount(@RequestBody AuditRepayBankInfoForm auditRepayBankInfoForm);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditSuppSigning/submitSigning", method = RequestMethod.GET)
    Message submitSigning(@RequestParam("pid")Long pid,@RequestParam("name") String name);

    @ResponseBody
    @RequestMapping(value = "/audit/auditSuppSigning/downloadContract", method = RequestMethod.GET,produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<byte[]> downloadContract(@RequestParam("pid") Long pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/auditSuppSigning/isUpLoadContract", method = RequestMethod.GET)
    Message isUpLoadContract(@RequestParam("pid")Long pid);
}
