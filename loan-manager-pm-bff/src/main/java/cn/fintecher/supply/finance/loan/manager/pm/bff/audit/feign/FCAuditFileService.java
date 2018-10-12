package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 11:08 2018/7/11
 */

@FeignClient(name = "loan-manager-service")
public interface FCAuditFileService {

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/uploadAuditFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Message uploadAuditFile(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/donwloadCompanyCreditDoc", method = RequestMethod.GET)
    ResponseEntity<byte[]> donwloadCompanyCreditDoc(@RequestParam(value = "pid") Long pid, @RequestParam(value = "type") String type, @RequestParam(value = "year", required = false) String year);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/donwloadAuditFile", method = RequestMethod.GET)
    ResponseEntity<byte[]> donwloadAuditFile(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/searchAllFileByFid", method = RequestMethod.GET)
    AuditFileEntity searchAllFileByFid(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/searchAllFileByOwnerId", method = RequestMethod.GET)
    AuditFileEntity searchAllFileByOwnerId(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/uploadAuditFileLimit", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Message uploadAuditFileLimit(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type, @RequestParam(value = "pid") String pid, @RequestParam(value = "count") Integer count);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/searchAllAuditFileByOwnerIdAndCategory", method = RequestMethod.GET)
    Message<List<AuditFileEntity>> searchAllAuditFileByOwnerIdAndCategory(@RequestParam(value = "pid") String pid, @RequestParam(value = "type") String type);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/deleteAuditFile", method = RequestMethod.GET)
    Message deleteAuditFile(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/searchCompanyById", method = RequestMethod.GET)
    AuditCompanyEntity searchCompanyById(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/searchAllAuditFileByOwnerId", method = RequestMethod.GET)
    Message<List<AuditFileEntity>> searchAllAuditFileByOwnerId(@RequestParam(value = "pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/deleteAuditFileByOwnerId", method = RequestMethod.GET)
    Message deleteAuditFileByOwnerId(@RequestParam(value = "ownerId") String ownerId, @RequestParam(value = "type") String type);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/update", method = RequestMethod.POST)
    Message update(@RequestBody AuditFileEntity auditFileEntity);

}