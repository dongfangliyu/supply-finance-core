package cn.fintecher.supply.finance.loan.manager.core.base.controller;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.debitandcredit.request.SubmitDebitAndCredit;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.core.base.service.BaseBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/7/19 17:02
 */
@RestController
@RequestMapping("/base/bankInfo")
public class BaseBankInfoController {

    @Autowired
    private BaseBankInfoService baseBankInfoService;

    @ResponseBody
    @RequestMapping(value ="/getLoanBankByCompanyId", method = RequestMethod.GET)
    public BaseBankInfoEntity getLoanBankByCompanyId(@RequestParam("companyId") Long companyId){
        return baseBankInfoService.getLoanBankByCompanyId(companyId);
    }

    @ResponseBody
    @RequestMapping(value ="/getRepayBankBySignId", method = RequestMethod.GET)
    public BaseBankInfoEntity getRepayBankBySignId(@RequestParam("signId") Long signId){
        return baseBankInfoService.getRepayBankBySignId(signId);
    }


    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public Message update(@RequestBody BaseBankInfoEntity baseBankInfoEntity){
        return baseBankInfoService.update(baseBankInfoEntity);
    }

    @ResponseBody
    @RequestMapping(value ="/insert", method = RequestMethod.POST)
    public Message insert(@RequestBody BaseBankInfoEntity baseBankInfoEntity){
        return baseBankInfoService.insert(baseBankInfoEntity);
    }



}
