package cn.fintecher.supply.finance.loan.manager.pm.bff.company.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.PledgeClientForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/8/18 0018下午 5:27
 */
@FeignClient(name = "loan-manager-service")
public interface FCPledgeClientService {

    @ResponseBody
    @RequestMapping(value = "/company/pledgeService/selectPledgeEnterprise", method = RequestMethod.GET)
    Message selectPledgeEnterprise(@RequestParam(value = "name") String name);

    @ResponseBody
    @RequestMapping(value = "/company/pledgeService/updatePledgeEnterprise", method = RequestMethod.POST)
    Message updatePledgeEnterprise(@RequestBody PledgeClientForm pledgeClientForm);
}
