package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockFrom;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "loan-manager-service")
public interface PledgeSigningCore {

    @ResponseBody
    @RequestMapping(value = "/pledge/signing/selectAdminSigningList", method = RequestMethod.POST)
    public Message selectAdminSigningList(@RequestBody PledgeStockFrom pledgeStockFrom);

    @ResponseBody
    @RequestMapping(value = "/pledge/signing/selectWebSigningList", method = RequestMethod.POST)
    public Message selectWebSigningList(@RequestBody PledgeStockFrom pledgeStockFrom);

    @ResponseBody
    @RequestMapping(value = "/pledge/signing/selectAdminSigningDetail", method = RequestMethod.GET)
    public Message selectAdminSigningDetail(@RequestParam(value = "id") Long id);

    @ResponseBody
    @RequestMapping(value = "/pledge/signing/selectWebSigningDetail", method = RequestMethod.GET)
    public Message selectWebSigningDetail(@RequestParam(value = "id") Long id);

    @ResponseBody
    @RequestMapping(value = "/pledge/signing/submitAdminSigning", method = RequestMethod.GET)
    public Message submitAdminSigning(@RequestParam(value = "id") Long id);

    @ResponseBody
    @RequestMapping(value = "/pledge/signing/submitWebSigning", method = RequestMethod.GET)
    public Message submitWebSigning(@RequestParam(value = "id") Long id);

    @ResponseBody
    @RequestMapping(value = "/pledge/signing/selectPaperContract", method = RequestMethod.GET)
    public Message selectPaperContract(@RequestParam(value = "id") Long id);

    @ResponseBody
    @RequestMapping(value = "/pledge/signing/selectContractMoreType", method = RequestMethod.GET)
    public Message selectContractMoreType(@RequestParam(value = "id") Long id);

}
