package cn.fintecher.supply.finance.loan.manager.pm.bff.company.feign;

import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyBlacklistEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-20 11:42:47
 */
@FeignClient(name = "loan-manager-service")
public interface CompanyBlacklistCore {

    @ResponseBody
    @RequestMapping(value = "/company/blacklist/selectBlackList", method = RequestMethod.POST)
    public Message selectBlackList(@RequestBody BlackListFrom blackListFrom);
}
