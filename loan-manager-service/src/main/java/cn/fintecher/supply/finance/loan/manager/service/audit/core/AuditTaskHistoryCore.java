package cn.fintecher.supply.finance.loan.manager.service.audit.core;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:12:08
 */
@FeignClient(name = "loan-manager-core")
public interface AuditTaskHistoryCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/taskHistory/insertTaskHistory", method = RequestMethod.POST)
    public Message insertTaskHistory(@RequestBody AuditTaskHistoryEntity auditTaskHistoryEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/taskHistory/updateTaskHistory", method = RequestMethod.POST)
    public Message updateTaskHistory(@RequestBody AuditTaskHistoryEntity auditTaskHistoryEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/taskHistory/selectByTaskHistory", method = RequestMethod.POST)
    public Message<List<AuditTaskHistoryEntity>> selectByTaskHistory(@RequestBody AuditTaskHistoryEntity auditTaskHistoryEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/taskHistory/queryTaskHistoryByPid", method = RequestMethod.GET)
    public Message<AuditTaskHistoryEntity> queryTaskHistoryByPid(@RequestParam(value = "pid")String pid);

}
