package cn.fintecher.supply.finance.loan.manager.service.pledge.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 17:22:04
 */
@FeignClient(name = "loan-manager-core")
public interface PledgeApplyStockInfoCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/applyStockInfo/insertApplyStockInfo", method = RequestMethod.POST)
    public Integer insertApplyStockInfo(@RequestBody PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/applyStockInfo/updateApplyStockInfo", method = RequestMethod.POST)
    public Integer updateApplyStockInfo(@RequestBody PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/applyStockInfo/selectByApplyStockInfo", method = RequestMethod.POST)
    public List<PledgeApplyStockInfoEntity> selectByApplyStockInfo(@RequestBody PledgeApplyStockInfoEntity pledgeApplyStockInfoEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/applyStockInfo/queryApplyStockInfoByPid", method = RequestMethod.GET)
    public PledgeApplyStockInfoEntity queryApplyStockInfoByPid(@RequestParam(value = "pid")String pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/applyStockInfo/queryApplyStockInfoByPid", method = RequestMethod.GET)
	public void SureReceiveTask(@RequestParam(value = "task")Integer task);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/pledge/applyStockInfo/countConfirmingTaskCanReceiveNum", method = RequestMethod.GET)
    Message<Integer> countConfirmingTaskCanReceiveNum(@RequestParam(value = "node")Integer node);

}
