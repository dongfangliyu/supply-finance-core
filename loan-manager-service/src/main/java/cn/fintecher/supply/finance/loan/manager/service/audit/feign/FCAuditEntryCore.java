package cn.fintecher.supply.finance.loan.manager.service.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditEntryForm;
import cn.fintecher.supply.finance.loan.manager.common.form.SearchMarginDetailForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntryEntity;
import cn.fintecher.supply.finance.loan.manager.common.response.MarginDetailResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/7/23 0023下午 5:47
 */
@FeignClient(name = "loan-manager-core")
public interface FCAuditEntryCore {

    /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/audit/taskEntry/insertTaskEntry", method = RequestMethod.POST)
    public Message insertTaskEntry(@RequestBody AuditTaskEntryEntity auditTaskEntryEntity);

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/audit/taskEntry/updateTaskEntry", method = RequestMethod.POST)
    public Message updateTaskEntry(@RequestBody AuditTaskEntryEntity auditTaskEntryEntity);

    /**
     * 查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/audit/taskEntry/selectByTaskEntry", method = RequestMethod.POST)
    public Message<List<AuditTaskEntryEntity>> selectByTaskEntry(@RequestBody AuditTaskEntryEntity auditTaskEntryEntity);

    /**
     * 根据主键查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/audit/taskEntry/queryTaskEntryByPid", method = RequestMethod.GET)
    public Message<AuditTaskEntryEntity> queryTaskEntryByPid(@RequestParam("pid") String pid);


    @ResponseBody
    @RequestMapping(value = "/audit/taskEntry/searchTaskEntryListCount", method = RequestMethod.POST)
    int searchTaskEntryListCount(@RequestBody AuditEntryForm auditEntryForm);

    @ResponseBody
    @RequestMapping(value = "/audit/taskEntry/searchTaskEntryList", method = RequestMethod.POST)
    List<AuditTaskEntryEntity> searchTaskEntryList(@RequestBody AuditEntryForm auditEntryForm);

    @ResponseBody
    @RequestMapping(value = "/audit/taskEntry/searchSumOfRepaymentPrice", method = RequestMethod.GET)
    Double searchSumOfRepaymentPrice(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value = "/audit/taskEntry/searchTaskEntityByOrderId", method = RequestMethod.GET)
    List<AuditTaskEntryEntity> searchTaskEntityByOrderId(@RequestParam(value = "pid") Long pid);

    @ResponseBody
    @RequestMapping(value = "/audit/taskEntry/searchSumOfRepaymentPriceByState", method = RequestMethod.GET)
    Double searchSumOfRepaymentPriceByState(@RequestParam(value = "orderId") Long orderId);
    
    @ResponseBody
    @RequestMapping(value = "/audit/taskEntry/searchMarginDetail", method = RequestMethod.POST)
    public Message<List<MarginDetailResponse>> searchMarginDetail(@RequestBody SearchMarginDetailForm searchMarginDetailForm);

}
