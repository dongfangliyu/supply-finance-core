package cn.fintecher.supply.finance.loan.manager.service.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCompanyEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 11:20 2018/7/11
 */
@FeignClient(name = "loan-manager-core")
public interface FCAuditFileCore {
    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/insert", method = RequestMethod.POST)
    Message insert(@RequestBody AuditFileEntity auditFileEntity);


    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/searchAllFileByFid", method = RequestMethod.GET)
    AuditFileEntity searchAllFileByFid(@RequestParam(value="pid") Long pid);

    @ResponseBody
    @RequestMapping(value = "/audit/companyCore/searchCompanyById", method = RequestMethod.GET)
    AuditCompanyEntity searchCompanyById(@RequestParam(value="pid") Long pid);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/searchAllFileByOwnerId", method = RequestMethod.GET)
    AuditFileEntity searchAllFileByOwnerId(@RequestParam(value="pid") Long pid);

    @RequestMapping(value = "/audit/companyFile/searchAllAuditFileByOwnerIdAndCategory", method = RequestMethod.GET)
    List<AuditFileEntity> searchAllAuditFileByOwnerIdAndCategory(@RequestParam(value = "ownerId") String ownerId,@RequestParam(value = "category",required = false) String category);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/update", method = RequestMethod.POST)
    Message update(@RequestBody AuditFileEntity auditFileEntity);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/searchAllFileByOwnerId", method = RequestMethod.GET)
    List<AuditFileEntity> searchAllFileByOwnerId(@RequestParam(value = "ownerId") String ownerId);

    @ResponseBody
    @RequestMapping(value = "/audit/companyFile/deleteAuditFileByOwnerId", method = RequestMethod.GET)
    Boolean deleteAuditFileByOwnerId(@RequestParam(value="ownerId") String ownerId,@RequestParam(value="type") String type);
}
