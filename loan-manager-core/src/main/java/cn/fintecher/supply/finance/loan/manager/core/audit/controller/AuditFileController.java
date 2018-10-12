package cn.fintecher.supply.finance.loan.manager.core.audit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditFileEntity;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 12:19 2018/7/11
 */
@RestController
@RequestMapping("/audit/companyFile")
public class AuditFileController {
    @Autowired
    private AuditFileService auditFileService;

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Message insert(@RequestBody AuditFileEntity auditFileEntity) {
        return auditFileService.uploadAuditFile(auditFileEntity);
    }


    @ResponseBody
    @RequestMapping(value = "/searchAllFileByFid", method = RequestMethod.GET)
    public AuditFileEntity searchAllFileByFid(@RequestParam(value="pid")Long pid){
        return auditFileService.searchAllFileByFid(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/searchAllFileByOwnerId", method = RequestMethod.GET)
    public List<AuditFileEntity> searchAllFileByOwnerId(@RequestParam(value="ownerId") Long ownerId){
        return auditFileService.searchAllFileByOwnerId(ownerId);
    }

    @ResponseBody
    @RequestMapping(value = "/searchAllAuditFileByOwnerIdAndCategory", method = RequestMethod.GET)
    public List<AuditFileEntity> searchAllAuditFileByOwnerIdAndCategory(@RequestParam(value = "ownerId") String ownerId, @RequestParam(value = "category") String category){
        return auditFileService.searchAllAuditFileByOwnerIdAndCategory(ownerId,category);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Message update(@RequestBody AuditFileEntity auditFileEntity){
        return auditFileService.update(auditFileEntity);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAuditFileByOwnerId", method = RequestMethod.GET)
    public Boolean deleteAuditFileByOwnerId(@RequestParam(value="ownerId") String ownerId,@RequestParam(value="type") String type){
        return auditFileService.deleteAuditFileByOwnerId(ownerId,type);
    }
}
