package cn.fintecher.supply.finance.loan.manager.service.company.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.PledgeClientForm;
import cn.fintecher.supply.finance.loan.manager.service.company.service.PledgeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonghebin
 * @date 2018/8/18 0018下午 5:29
 */
@RestController
@RequestMapping("/company/pledgeService")
public class PledgeClientController {

    @Autowired
    private PledgeClientService pledgeClientService;

    @ResponseBody
    @RequestMapping(value = "/selectPledgeEnterprise", method = RequestMethod.GET)
    public Message selectPledgeEnterprise(@RequestParam(value = "name") String name){
        return pledgeClientService.selectPledgeEnterprise(name);
    }


    @ResponseBody
    @RequestMapping(value = "/updatePledgeEnterprise", method = RequestMethod.POST)
    public Message updatePledgeEnterprise(@RequestBody PledgeClientForm pledgeClientForm){
        return pledgeClientService.updatePledgeEnterprise(pledgeClientForm);
    }

}
