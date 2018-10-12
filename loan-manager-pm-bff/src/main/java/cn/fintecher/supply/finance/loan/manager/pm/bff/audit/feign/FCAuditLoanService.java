package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.audit.AuditFinanceForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gonghebin
 * @date 2018/7/19 0019上午 10:52
 */
@FeignClient(name = "loan-manager-service")
public interface FCAuditLoanService {

    @ResponseBody
    @RequestMapping(value = "/audit/loanService/searchLoanList", method = RequestMethod.POST)
    Message searchLoanList(@RequestBody AuditFinanceForm auditFinanceForm);

    @ResponseBody
    @RequestMapping(value = "/audit/loanService/searchLoanDetail", method = RequestMethod.GET)
    Message searchLoanDetail(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/audit/loanService/submitLoanPass", method = RequestMethod.GET)
    Message<Object> submitLoanPass(@RequestParam(value = "pid") String pid,@RequestParam(value = "loanTime") String loanTime, @RequestParam(value = "userName") String userName);

    @ResponseBody
    @RequestMapping(value = "/audit/loanService/submitLoanFail", method = RequestMethod.GET)
    Message<Object> submitLoanFail(@RequestParam(value = "pid") String pid, @RequestParam(value = "userName") String userName);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/uploadAuditLoanFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Message uploadAuditLoanFile(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "pid") String pid);
}
