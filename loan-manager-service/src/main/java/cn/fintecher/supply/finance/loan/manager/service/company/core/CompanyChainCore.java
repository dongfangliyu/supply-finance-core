package cn.fintecher.supply.finance.loan.manager.service.company.core;

import cn.fintecher.supply.finance.loan.manager.common.company.request.CompanyChainFrom;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-11 13:34:43
 */
@FeignClient(name = "loan-manager-core")
public interface CompanyChainCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/insertChain", method = RequestMethod.POST)
    public Message insertChain(@RequestBody CompanyChainEntity companyChainEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/updateChain", method = RequestMethod.POST)
    public Message updateChain(@RequestBody CompanyChainEntity companyChainEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/selectByChain", method = RequestMethod.POST)
    public Message selectByChain(@RequestBody CompanyChainEntity companyChainEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/queryChainByPid", method = RequestMethod.GET)
    public Message queryChainByPid(@RequestParam(value = "pid") String pid);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/queryPageCount", method = RequestMethod.POST)
    public Message queryPageCount(@RequestBody CompanyChainFrom companyChainFrom);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/queryPageList", method = RequestMethod.POST)
    public Message queryPageList(@RequestBody CompanyChainFrom companyChainFrom);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/searchDownLoadList", method = RequestMethod.POST)
	public Message searchDownLoadList(@RequestBody CompanyChainFrom companyChainFrom);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/getByCompanyName", method = RequestMethod.GET)
    Message<CompanyChainEntity> getByCompanyName(@RequestParam(value = "companyName") String companyName);
}
