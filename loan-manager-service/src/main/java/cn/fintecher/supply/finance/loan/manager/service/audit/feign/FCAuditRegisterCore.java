package cn.fintecher.supply.finance.loan.manager.service.audit.feign;

import cn.fintecher.supply.finance.loan.manager.common.form.AuditRegisterForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditRegisterEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditResultEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/4 0004下午 9:01
 */
@FeignClient(name = "loan-manager-core")
public interface FCAuditRegisterCore {

    /**
     * 保存注册审核信息
     * @param auditRegisterEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/audit/registerCore/saveAuditRegisterEntity", method = RequestMethod.POST)
    void saveAuditRegisterEntity(@RequestBody AuditRegisterEntity auditRegisterEntity);

    /**
     * 查询注册审核信息
     * @param auditRegisterForm
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/audit/registerCore/searchListAuditRegister", method = RequestMethod.POST)
    List<AuditRegisterEntity> searchListAuditRegister(@RequestBody AuditRegisterForm auditRegisterForm);

    /**
     * 根据pid查询注册审核信息
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/audit/registerCore/searchAuditRegisterByPid", method = RequestMethod.GET)
    AuditRegisterEntity searchAuditRegisterByPid(@RequestParam(value = "pid") String pid);

    /**
     * 更新注册审核信息
     * @param auditRegisterEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/audit/registerCore/updateAuditRegister", method = RequestMethod.POST)
    void updateAuditRegister(@RequestBody AuditRegisterEntity auditRegisterEntity);

    /**
     * 保存审核结果
     * @param auditResultEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/audit/registerCore/saveAuditResultEntity", method = RequestMethod.POST)
    void saveAuditResultEntity(@RequestBody AuditResultEntity auditResultEntity);

    /**
     * 查询注册企业待处理
     * @param state
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/audit/registerCore/searchListAuditRegisterCount", method = RequestMethod.GET)
    Long searchListAuditRegisterCount(@RequestParam(value = "state") String state);

    /**
     * 根据注册审核id 查询审核结果
     * @param objectId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/audit/registerCore/searchAuditResultByObjectId", method = RequestMethod.GET)
    AuditResultEntity searchAuditResultByObjectId(@RequestParam(value = "objectId") String objectId,@RequestParam(value = "objectType") String objectType);

    /**
     * 更新审核结果(删除)
     * @param auditResultEntity
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/audit/registerCore/updateAuditResult", method = RequestMethod.POST)
    void updateAuditResult(@RequestBody AuditResultEntity auditResultEntity);

    /**
     * 查询注册审核信息总条数
     * @param auditRegisterForm
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/audit/registerCore/searchAuditRegisterListCount", method = RequestMethod.POST)
    int searchAuditRegisterListCount(@RequestBody AuditRegisterForm auditRegisterForm);

    /**
     * 根据注册id 查询注册审核信息
     * @param registerId
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/audit/registerCore/searchAuditRegisterByRegisterId", method = RequestMethod.GET)
    AuditRegisterEntity searchAuditRegisterByRegisterId(@RequestParam(value = "registerId") String registerId);

    /**
     * 根据注册企业名称 查询注册审核信息
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/audit/registerCore/searchAuditRegisterByName", method = RequestMethod.GET)
    List<AuditRegisterEntity> searchAuditRegisterByName(@RequestParam(value = "name") String name);
}
