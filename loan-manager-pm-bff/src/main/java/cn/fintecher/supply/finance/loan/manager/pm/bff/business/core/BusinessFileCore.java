package cn.fintecher.supply.finance.loan.manager.pm.bff.business.core;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import feign.Headers;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-13 08:56:11
 */
@FeignClient(name = "loan-manager-service")
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
    @RequestMapping(value = "/business/file/upload", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Message upload(@RequestPart("file")MultipartFile file,@RequestParam(value = "type") String type, @RequestParam(value = "registerCode")String registerCode
    		,@RequestParam(value = "id") Long id);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/file/delete", method = RequestMethod.POST)
    public Message delete(@RequestParam(value = "pid",required=true) Integer pid,@RequestParam(value = "userName") String userName);
	
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/business/file/search", method = RequestMethod.GET)
    public ResponseEntity<byte[]> search(@RequestParam(value = "pid",required=true) Integer pid);

}
