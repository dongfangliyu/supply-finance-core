package cn.fintecher.supply.finance.loan.manager.pm.bff.business.controller;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.pm.bff.business.service.BusinessFileService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.util.DownLoadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-13 08:56:11
 */
@Api(value = "账款管理文件接口", tags = "账款管理文件接口")
@RestController
@RequestMapping("/business/file")
public class BusinessFileController {

    @Autowired
    private BusinessFileService businessFileService;
    

	@ApiOperation(value="上传", notes="上传")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/upload", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message upload(@RequestPart("file") MultipartFile file, 
    		@RequestParam(value = "type") String type, 
    		@RequestParam(value = "code",required = false) String code,
    		@RequestParam(value = "id",required = false) Long id){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        if (code == null && id == null) {
        	 return new Message(MessageType.MSG_ERROR,"business","拥有者不能为空");
		}
        try {
            Message message =businessFileService.upload(file,type,code,id);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="删除", notes="删除")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/delete", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message delete(@RequestParam(value = "pid",required=true) Integer pid, Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessFileService.delete(pid,getUserName(principa));
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="下载查看", notes="下载查看")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/search", method = RequestMethod.GET)
    public ResponseEntity<byte[]> search(@RequestParam(value = "pid",required=true) Integer pid){
        return businessFileService.search(pid);

    }
    
    @ApiOperation(value="下载", notes="下载")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/download", method = RequestMethod.GET)
    public void download(@RequestParam(value = "pid",required=true) Integer pid, Principal principa,
    		HttpServletResponse response, HttpServletRequest request) throws FileNotFoundException{
    	ResponseEntity<byte[]> resp = businessFileService.search(pid);
    	InputStream inStream = new ByteArrayInputStream(resp.getBody());
    	BusinessFileEntity file = businessFileService.queryFileByPid(pid);
		DownLoadUtil.downLoadByInputStream(response, request, file.getFileName(), inStream);
    	
    }
    
	private String getUserName(Principal principa){
		return principa.getName();
	   }


   

}
