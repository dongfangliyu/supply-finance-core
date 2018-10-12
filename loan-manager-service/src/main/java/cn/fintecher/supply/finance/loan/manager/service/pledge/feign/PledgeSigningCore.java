package cn.fintecher.supply.finance.loan.manager.service.pledge.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSigningResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(name = "loan-manager-core")
public interface PledgeSigningCore {



    @ResponseBody
    @RequestMapping(value = "/pledge/stockInfo/selectAdminSigningList", method = RequestMethod.POST)
    List<PledgeSigningResponse> selectAdminSigningList(@RequestBody PledgeStockFrom pledgeStockFrom);

    @ResponseBody
    @RequestMapping(value = "/pledge/stockInfo/selectAdminSigningListCount", method = RequestMethod.POST)
    Integer selectAdminSigningListCount(@RequestBody PledgeStockFrom pledgeStockFrom);

    @ResponseBody
    @RequestMapping(value = "/pledge/stockInfo/selectWebSigningList", method = RequestMethod.POST)
    List<PledgeSigningResponse> selectWebSigningList(@RequestBody PledgeStockFrom pledgeStockFrom);

    @ResponseBody
    @RequestMapping(value = "/pledge/stockInfo/selectWebSigningListCount", method = RequestMethod.POST)
    Integer selectWebSigningListCount(@RequestBody PledgeStockFrom pledgeStockFrom);
}
