package cn.fintecher.supply.finance.loan.manager.service.business.core;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import feign.Headers;

import java.util.List;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-13 08:56:11
 */
@FeignClient(name = "loan-manager-core")
public interface BusinessFileCore {

 	@Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/file/insertFile", method = RequestMethod.POST)
    public Message insertFile(@RequestBody BusinessFileEntity businessFileEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/file/updateFile", method = RequestMethod.POST)
    public Message updateFile(@RequestBody BusinessFileEntity businessFileEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/file/selectByFile", method = RequestMethod.POST)
    public Message selectByFile(@RequestBody BusinessFileEntity businessFileEntity);
    
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/file/queryFileByPid", method = RequestMethod.GET)
    public Message queryFileByPid(@RequestParam(value = "pid")String pid);


    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/file/queryEnterFileList", method = RequestMethod.GET)
    public Message queryEnterFileList(@RequestParam(value = "orderCode")String orderCode);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/file/querySuppFileList", method = RequestMethod.GET)
    public Message querySuppFileList(@RequestParam(value = "suppCode")String suppCode);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/file/countByCodeAndType", method = RequestMethod.GET)
    Message<Integer> countByCodeAndType(@RequestParam(value = "category")String category,@RequestParam("ownerId") String ownerId);

    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/file/getListByCodeAndType", method = RequestMethod.GET)
    Message<List<BusinessFileEntity>> getListByCodeAndType(@RequestParam(value = "category")String category, @RequestParam("ownerId") String ownerId);
}
