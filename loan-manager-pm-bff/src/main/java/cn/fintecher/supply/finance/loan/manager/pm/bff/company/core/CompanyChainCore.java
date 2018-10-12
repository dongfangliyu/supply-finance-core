package cn.fintecher.supply.finance.loan.manager.pm.bff.company.core;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CompanyChainFrom;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-11 13:34:43
 */
@FeignClient(name = "loan-manager-service")
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
    @RequestMapping(value = "/company/chain/searchListChain", method = RequestMethod.POST)
    public Message<PagedResponse<List<CompanyChainEntity>>> searchListChain(@RequestBody CompanyChainFrom companyChainFrom);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/submitChain", method = RequestMethod.POST)
    public Message submitChain(@RequestBody  CompanyChainEntity companyChainEntity);
	
    /*@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/submitUploadChain", consumes = "multipart/form-data", method = RequestMethod.POST)
    public Message submitUploadChain(HttpServletResponse response, HttpServletRequest request,@RequestParam("file")MultipartFile file,@RequestParam("userName")String userName);
*/	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/submitUpdateChain", method = RequestMethod.POST)
    public Message submitUpdateChain(@RequestBody CompanyChainEntity companyChainEntity);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/deleteChain", method = RequestMethod.GET)
    public Message deleteChain(@RequestParam("id") String id,@RequestParam("userName")String userName);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/searchDownLoadList", method = RequestMethod.POST)
	public Message searchDownLoadList(@RequestBody CompanyChainFrom companyChainFrom);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/searchList", method = RequestMethod.POST)
	public Message searchList(@RequestParam("userName")String userName);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/company/chain/inviteAgency", method = RequestMethod.GET)
    Message inviteAgency(@RequestParam("id")Long id, @RequestParam("userName")String userName);


}
