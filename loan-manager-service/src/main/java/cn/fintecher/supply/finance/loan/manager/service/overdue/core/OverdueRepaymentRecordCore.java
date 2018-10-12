package cn.fintecher.supply.finance.loan.manager.service.overdue.core;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueRepaymentRecordEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRecordForm;
import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 13:24:15
 */
@FeignClient(name = "loan-manager-core")
public interface OverdueRepaymentRecordCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/repaymentRecord/insertRepaymentRecord", method = RequestMethod.POST)
    public Message insertRepaymentRecord(@RequestBody OverdueRepaymentRecordEntity overdueRepaymentRecordEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/repaymentRecord/updateRepaymentRecord", method = RequestMethod.POST)
    public Message updateRepaymentRecord(@RequestBody OverdueRepaymentRecordEntity overdueRepaymentRecordEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/repaymentRecord/selectByRepaymentRecord", method = RequestMethod.POST)
    public Message<List<OverdueRepaymentRecordEntity>> selectByRepaymentRecord(@RequestBody OverdueRepaymentRecordEntity overdueRepaymentRecordEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/repaymentRecord/queryRepaymentRecordByPid", method = RequestMethod.GET)
    public Message<OverdueRepaymentRecordEntity> queryRepaymentRecordByPid(@RequestParam(value = "pid")String pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/repaymentRecord/queryListByRecordForm", method = RequestMethod.POST)
	public Message<List<OverdueRepaymentRecordEntity>> queryListByRecordForm(@RequestBody
			OverdueOrderRecordForm overdueOrderRecordForm);
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/overdue/repaymentRecord/queryCountByRecordForm", method = RequestMethod.POST)
	public Message<Integer> queryCountByRecordForm(@RequestBody OverdueOrderRecordForm overdueOrderRecordForm);

}
