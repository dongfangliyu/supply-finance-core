package cn.fintecher.supply.finance.loan.manager.service.base.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.response.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.DictionaryResponse;
import cn.fintecher.supply.finance.loan.manager.service.base.service.BaseBankInfoService;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseSysUserController;
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

    //获取签约审核还款账号
    @ResponseBody
    @RequestMapping(value ="/getRepayBankBySignId", method = RequestMethod.GET)
    public BaseBankInfoEntity getRepayBankBySignId(@RequestParam("signId") Long signId){
        return baseBankInfoService.getRepayBankBySignId(signId);
    }

    //获取企业放款账号
    @ResponseBody
    @RequestMapping(value ="/getLoanBankByCompanyId", method = RequestMethod.GET)
    public BaseBankInfoEntity getLoanBankByCompanyId(@RequestParam("companyId") Long companyId){
        return baseBankInfoService.getLoanBankByCompanyId(companyId);
    }

    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    Message update(@RequestBody BaseBankInfoEntity baseBankInfoEntity){
        return baseBankInfoService.update(baseBankInfoEntity);
    }

    @ResponseBody
    @RequestMapping(value ="/insert", method = RequestMethod.POST)
    Message insert(@RequestBody BaseBankInfoEntity baseBankInfoEntity){
        return baseBankInfoService.insert(baseBankInfoEntity);
    }
}
