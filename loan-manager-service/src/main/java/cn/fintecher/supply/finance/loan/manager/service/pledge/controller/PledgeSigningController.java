package cn.fintecher.supply.finance.loan.manager.service.pledge.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockFrom;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeSigningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pledge/signing")
public class PledgeSigningController extends BaseCompanyUserController {

    @Autowired
    private PledgeSigningService pledgeSigningService;

    @ResponseBody
    @RequestMapping(value ="/selectAdminSigningList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectAdminSigningList(@RequestBody PledgeStockFrom pledgeStockFrom){
        return pledgeSigningService.selectAdminSigningList(pledgeStockFrom);
    }

    @ResponseBody
    @RequestMapping(value ="/selectWebSigningList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectWebSigningList(@RequestBody PledgeStockFrom pledgeStockFrom){
        pledgeStockFrom.setOwnerId(getCompanyUser(pledgeStockFrom.getOwnerId()).getEnterpriseId().toString());
        return pledgeSigningService.selectWebSigningList(pledgeStockFrom);
    }


    @ResponseBody
    @RequestMapping(value ="/selectAdminSigningDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectAdminSigningDetail(@RequestParam Long id){
        return pledgeSigningService.selectAdminSigningDetail(id);
    }

    @ResponseBody
    @RequestMapping(value ="/selectWebSigningDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectWebSigningDetail(@RequestParam Long id){
        return pledgeSigningService.selectWebSigningDetail(id);
    }

    @ResponseBody
    @RequestMapping(value ="/submitAdminSigning", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitAdminSigning(@RequestParam Long id){
        return pledgeSigningService.submitAdminSigning(id);
    }

    @ResponseBody
    @RequestMapping(value ="/submitWebSigning", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitWebSigning(@RequestParam Long id){
        return pledgeSigningService.submitWebSigning(id);
    }


    @ResponseBody
    @RequestMapping(value ="/selectPaperContract", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectPaperContract(@RequestParam Long id){
        return pledgeSigningService.selectPaperContract(id);
    }


    @ResponseBody
    @RequestMapping(value ="/selectContractMoreType", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectContractMoreType(@RequestParam Long id){
        return pledgeSigningService.selectContractMoreType(id);
    }



}

