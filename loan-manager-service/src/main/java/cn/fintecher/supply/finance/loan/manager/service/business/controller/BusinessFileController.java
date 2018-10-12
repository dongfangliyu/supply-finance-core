package cn.fintecher.supply.finance.loan.manager.service.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessFileService;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-13 08:56:11
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/business/file")
public class BusinessFileController {

    @Autowired
    private BusinessFileService businessFileService;
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/insertFile", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message insertFile(@RequestBody BusinessFileEntity businessFileEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessFileService.insertFile(businessFileEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/updateFile", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message updateFile(@RequestBody BusinessFileEntity businessFileEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessFileService.updateFile(businessFileEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/selectByFile", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectByFile(@RequestBody BusinessFileEntity businessFileEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessFileService.selectByFile(businessFileEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/queryFileByPid", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message queryFileByPid(@RequestParam("pid") String pid){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessFileService.queryFileByPid(pid);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }

	@ApiOperation(value="", notes="")
	@ResponseBody
    @RequestMapping(value ="/upload", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Message upload(@RequestPart("file")MultipartFile file,@RequestParam(value = "type")String type,@RequestParam(value = "registerCode",required = false)String registerCode,@RequestParam(value = "id",required = false) Long id){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessFileService.upload(file,type,registerCode,id);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="")
    @RequestMapping(value ="/delete", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message delete(@RequestParam(value = "pid",required=true) Integer pid,@RequestParam(value = "userName") String userName){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessFileService.delete(pid,userName);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="")
    @RequestMapping(value ="/search", method = RequestMethod.GET)
    public ResponseEntity<byte[]> search(@RequestParam(value = "pid",required=true) Integer pid){
       return businessFileService.search(pid);
            
    }
    
   


   

}
