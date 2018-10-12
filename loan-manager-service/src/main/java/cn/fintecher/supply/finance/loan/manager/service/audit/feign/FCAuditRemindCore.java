package cn.fintecher.supply.finance.loan.manager.service.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskRemindEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditTaskRemindForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 1:49
 */
@FeignClient(name = "loan-manager-core")
public interface FCAuditRemindCore {

    /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/audit/taskRemind/insertTaskRemind", method = RequestMethod.POST)
    Message insertTaskRemind(@RequestBody AuditTaskRemindEntity auditTaskRemindEntity);

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/audit/taskRemind/updateTaskRemind", method = RequestMethod.POST)
    Message updateTaskRemind(@RequestBody AuditTaskRemindEntity auditTaskRemindEntity);

    /**
     * 查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/audit/taskRemind/selectByTaskRemind", method = RequestMethod.POST)
    Message<List<AuditTaskRemindEntity>> selectByTaskRemind(@RequestBody AuditTaskRemindEntity auditTaskRemindEntity);

    /**
     * 根据主键查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/audit/taskRemind/queryTaskRemindByPid", method = RequestMethod.GET)
    Message<AuditTaskRemindEntity> queryTaskRemindByPid(@RequestParam("pid") String pid);

    @ResponseBody
    @RequestMapping(value = "/audit/taskRemind/searchRemindCount", method = RequestMethod.POST)
    int searchRemindCount(@RequestBody AuditTaskRemindForm auditTaskRemindForm);

    @ResponseBody
    @RequestMapping(value = "/audit/taskRemind/searchRemind", method = RequestMethod.POST)
    List<AuditTaskRemindEntity> searchRemind(@RequestBody AuditTaskRemindForm auditTaskRemindForm);

    @ResponseBody
    @RequestMapping(value = "/audit/taskRemind/selectAllRemind", method = RequestMethod.GET)
    List<AuditTaskRemindEntity> selectAllRemind();
}
