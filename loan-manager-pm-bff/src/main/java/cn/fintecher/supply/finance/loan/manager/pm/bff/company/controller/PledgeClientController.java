package cn.fintecher.supply.finance.loan.manager.pm.bff.company.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.PledgeClientForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.company.service.PledgeClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author gonghebin
 * @date 2018/8/18 0018下午 5:17
 */
@RestController
@RequestMapping("/pledge/client")
@Api(tags = "质押企业信息")
public class PledgeClientController {

    @Autowired
    private PledgeClientService pledgeClientService;


    /**
     * 查询质押企业信息
     * @return
     */
    @ApiOperation(value="查询质押企业信息 ", notes="查询质押企业信息")
    @RequestMapping(value ="/selectPledgeEnterprise", method = RequestMethod.GET)
    public Message selectPledgeEnterprise(Principal principal){
        String name = principal.getName();
        Message message = pledgeClientService.selectPledgeEnterprise(name);
        return message;
    }

    /**
     * 修改质押企业信息
     * @return
     */
    @ApiOperation(value="修改质押企业信息 ", notes="修改质押企业信息")
    @RequestMapping(value ="/updatePledgeEnterprise", method = RequestMethod.POST)
    public Message updatePledgeEnterprise(@RequestBody PledgeClientForm pledgeClientForm){
        Message message = pledgeClientService.updatePledgeEnterprise(pledgeClientForm);
        return message;
    }


}
