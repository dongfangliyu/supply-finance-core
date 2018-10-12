package cn.fintecher.supply.finance.loan.manager.service.overdue.core;

import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:10:50
 */
@FeignClient(name = "loan-manager-core")
public interface OverdueAuditOrderInfoCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/insertOrderInfo", method = RequestMethod.POST)
    public Message insertOrderInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/updateOrderInfo", method = RequestMethod.POST)
    public Message updateOrderInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/selectByOrderInfo", method = RequestMethod.POST)
    public Message<List<AuditOrderInfoEntity>> selectByOrderInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/queryOrderInfoByPid", method = RequestMethod.GET)
    public Message<AuditOrderInfoEntity> queryOrderInfoByPid(@RequestParam(value = "pid")String pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/audit/orderInfo/getOverdueOrderInfo", method = RequestMethod.GET)
	public Message<List<AuditOrderInfoEntity>> getOverdueOrderInfo();


}
