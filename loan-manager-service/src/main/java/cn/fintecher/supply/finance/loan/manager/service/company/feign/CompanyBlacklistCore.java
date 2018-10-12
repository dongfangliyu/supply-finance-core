package cn.fintecher.supply.finance.loan.manager.service.company.feign;

import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import io.swagger.models.auth.In;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

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
@FeignClient(name = "loan-manager-core")
public interface CompanyBlacklistCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/blacklist/insertBlacklist", method = RequestMethod.POST)
    public Message insertBlacklist(@RequestBody CompanyBlacklistEntity companyBlacklistEntity);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/blacklist/updateBlacklist", method = RequestMethod.POST)
    public Message updateBlacklist(@RequestBody CompanyBlacklistEntity companyBlacklistEntity);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/blacklist/selectByBlacklist", method = RequestMethod.POST)
    public Message<List<CompanyBlacklistEntity>> selectByBlacklist(@RequestBody CompanyBlacklistEntity companyBlacklistEntity);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/blacklist/queryBlacklistByPid", method = RequestMethod.GET)
    public Message<CompanyBlacklistEntity> queryBlacklistByPid(@RequestParam(value = "pid")String pid);



    @ResponseBody
    @RequestMapping(value = "/company/blacklist/selectBlackList", method = RequestMethod.POST)
    public List<CompanyBlacklistEntity> selectBlackList(@RequestBody BlackListFrom blackListFrom);

    @ResponseBody
    @RequestMapping(value = "/company/blacklist/selectBlackListCount", method = RequestMethod.POST)
    public Integer selectBlackListCount(@RequestBody BlackListFrom blackListFrom);

}
