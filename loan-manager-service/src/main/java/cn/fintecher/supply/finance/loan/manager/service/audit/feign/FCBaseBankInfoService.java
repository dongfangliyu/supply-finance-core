package cn.fintecher.supply.finance.loan.manager.service.audit.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuxiaoxing
 * @time 2018/7/19 17:18
 */
@FeignClient(name = "loan-manager-service")
public interface FCBaseBankInfoService {
    @ResponseBody
    @RequestMapping(value ="/base/bankInfo/getLoanBankByCompanyId", method = RequestMethod.GET)
    public BaseBankInfoEntity getLoanBankByCompanyId(@RequestParam("companyId") Long companyId);

    @ResponseBody
    @RequestMapping(value ="/base/bankInfo/getRepayBankBySignId", method = RequestMethod.GET)
    public BaseBankInfoEntity getRepayBankBySignId(@RequestParam("signId") Long signId);

    @ResponseBody
    @RequestMapping(value ="/base/bankInfo/update", method = RequestMethod.POST)
    Message update(@RequestBody BaseBankInfoEntity baseBankInfoEntity);

    @ResponseBody
    @RequestMapping(value ="/base/bankInfo/insert", method = RequestMethod.POST)
    Message insert(@RequestBody BaseBankInfoEntity baseBankInfoEntity);
}
