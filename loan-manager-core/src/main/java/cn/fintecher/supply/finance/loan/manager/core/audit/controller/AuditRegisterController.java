package cn.fintecher.supply.finance.loan.manager.core.audit.controller;

import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditRegisterEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditResultEntity;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 9:11
 */
@RestController
@RequestMapping("/audit/registerCore")
public class AuditRegisterController {

    @Autowired
    private AuditRegisterService auditRegisterService;

    /**
     * 保存注册审核信息
     * @param auditRegisterEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/saveAuditRegisterEntity", method = RequestMethod.POST)
    public void saveAuditRegisterEntity(@RequestBody AuditRegisterEntity auditRegisterEntity){
        auditRegisterService.saveAuditRegisterEntity(auditRegisterEntity);
    }

    /**
     * 查询注册审核信息
     * @param auditRegisterForm
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/searchListAuditRegister", method = RequestMethod.POST)
    public List<AuditRegisterEntity> searchListAuditRegister(@RequestBody AuditRegisterForm auditRegisterForm){
        return auditRegisterService.searchListAuditRegister(auditRegisterForm);
    }

    /**
     * 根据pid查询注册审核信息
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/searchAuditRegisterByPid", method = RequestMethod.GET)
    public AuditRegisterEntity searchAuditRegisterByPid(@RequestParam(value = "pid") String pid){
        return auditRegisterService.searchAuditRegisterByPid(pid);
    }

    /**
     * 更新注册审核信息
     * @param auditRegisterEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/updateAuditRegister", method = RequestMethod.POST)
    public void updateAuditRegister(@RequestBody AuditRegisterEntity auditRegisterEntity){
        auditRegisterService.updateAuditRegister(auditRegisterEntity);
    }


    /**
     * 保存审核结果
     * @param auditResultEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/saveAuditResultEntity", method = RequestMethod.POST)
    public void saveAuditResultEntity(@RequestBody AuditResultEntity auditResultEntity){
        auditRegisterService.saveAuditResultEntity(auditResultEntity);
    }


    /**
     * 查询注册企业待处理
     * @param state
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/searchListAuditRegisterCount", method = RequestMethod.GET)
    public Long searchListAuditRegisterCount(@RequestParam(value = "state") String state){
        return auditRegisterService.searchListAuditRegisterCount(state);
    }


    /**
     * 根据注册审核id 查询审核结果
     * @param objectId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/searchAuditResultByObjectId", method = RequestMethod.GET)
    public AuditResultEntity searchAuditResultByObjectId(@RequestParam(value = "objectId") String objectId,@RequestParam(value = "objectType") String objectType){
        return auditRegisterService.searchAuditResultByObjectId(objectId,objectType);
    }


    /**
     * 更新审核结果(删除)
     * @param auditResultEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/updateAuditResult", method = RequestMethod.POST)
    public void updateAuditResult(@RequestBody AuditResultEntity auditResultEntity){
        auditRegisterService.updateAuditResult(auditResultEntity);
    }

    /**
     * 查询注册审核信息总条数
     * @param auditRegisterForm
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/searchAuditRegisterListCount", method = RequestMethod.POST)
    public int searchAuditRegisterListCount(@RequestBody AuditRegisterForm auditRegisterForm){
        return auditRegisterService.searchAuditRegisterListCount(auditRegisterForm);
    }

    /**
     * 根据注册id 查询注册审核信息
     * @param registerId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/searchAuditRegisterByRegisterId", method = RequestMethod.GET)
    public AuditRegisterEntity searchAuditRegisterByRegisterId(@RequestParam(value = "registerId") String registerId){
       return auditRegisterService.searchAuditRegisterByRegisterId(registerId);
    }

    /**
     * 根据注册企业名称 查询注册审核信息
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/searchAuditRegisterByName", method = RequestMethod.GET)
    public List<AuditRegisterEntity> searchAuditRegisterByName(@RequestParam(value = "name") String name){
        return auditRegisterService.searchAuditRegisterByName(name);
    }

}
