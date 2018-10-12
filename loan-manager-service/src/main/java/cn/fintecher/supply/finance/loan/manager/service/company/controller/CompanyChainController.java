package cn.fintecher.supply.finance.loan.manager.service.company.controller;

import cn.fintecher.supply.finance.loan.manager.common.company.request.CompanyChainFrom;
import cn.fintecher.supply.finance.loan.manager.common.constant.ReturnMsg;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseSysUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyChainService;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;

/**
 * 链属关系
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-11 13:34:43
 */
@Api(value = "链属关系", tags = "链属关系")
@RestController
@RequestMapping("/company/chain")
public class CompanyChainController extends BaseCompanyUserController {

    @Autowired
    private CompanyChainService companyChainService;
    
    @ApiOperation(value="新增链属关系", notes="新增链属关系")
    @RequestMapping(value ="/insertChain", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message insertChain(@RequestBody CompanyChainEntity companyChainEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
        try {
            Message message =companyChainService.insertChain(companyChainEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="修改链属关系", notes="修改链属关系")
    @RequestMapping(value ="/updateChain", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message updateChain(@RequestBody CompanyChainEntity companyChainEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
        try {
            Message message =companyChainService.updateChain(companyChainEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="查询链属关系", notes="查询链属关系")
    @RequestMapping(value ="/selectByChain", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectByChain(@RequestBody CompanyChainEntity companyChainEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
        try {
            Message message =companyChainService.selectByChain(companyChainEntity);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
    @ApiOperation(value="根据主键查询链属关系", notes="根据主键查询链属关系")
    @RequestMapping(value ="/queryChainByPid", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message queryChainByPid(@RequestParam("pid") String pid){
        Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
        try {
            Message message =companyChainService.queryChainByPid(pid);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }

	@ApiOperation(value="分页查询链属关系", notes="分页查询链属关系")
    @RequestMapping(value ="/searchListChain", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchListChain(@RequestBody CompanyChainFrom companyChainFrom){
        Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
        try {
            Message message =companyChainService.searchListChain(companyChainFrom);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="新增链属关系", notes="新增链属关系")
    @RequestMapping(value ="/submitChain", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitChain(@RequestBody CompanyChainEntity companyChainEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"company",ReturnMsg.SSUCCESS_CURRENCY);
        try {
            CompanyUserEntity user = getCompanyUser(companyChainEntity.getCreateBy());
            Message message =companyChainService.submitChain(companyChainEntity,user);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

    

	@ApiOperation(value="修改链属关系", notes="修改链属关系")
    @RequestMapping(value ="/submitUpdateChain", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitUpdateChain(@RequestBody CompanyChainEntity companyChainEntity){
        Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
        try {
        	 CompanyUserEntity user = getCompanyUser(companyChainEntity.getCreateBy());
            Message message =companyChainService.submitUpdateChain(companyChainEntity,user);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="删除链属关系", notes="删除链属关系")
    @RequestMapping(value ="/deleteChain", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message deleteChain(@RequestParam("id") String id,@RequestParam("userName")String userName){
        Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
        try {
            Message message =companyChainService.deleteChain(id,userName);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
	
	@ApiOperation(value="查询下载链属列表", notes="查询下载链属列表")
    @RequestMapping(value ="/searchDownLoadList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchDownLoadList(@RequestBody CompanyChainFrom companyChainFrom){
        Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
        try {
            Message message =companyChainService.searchDownLoadList(companyChainFrom);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
	 @RequestMapping(value = "/searchList", method = RequestMethod.POST)
	public Message searchList(@RequestParam("userName")String userName){
		 Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
	        try {
	            Message message =companyChainService.searchList(userName);
	            msg.setCode(message.getCode());
	            msg.setMessage(message.getMessage());
	        } catch (Exception e) {
	        	e.printStackTrace();
	            msg.setCode(MessageType.MSG_ERROR);
	        }
	        return msg;
	 };

    /**
     * @author tym    2018.8.28
     * @param
     * @param userName
     * @return
     */
    @ApiOperation(value="", notes="")
    @RequestMapping(value ="/inviteAgency", method = RequestMethod.GET)
    public Message inviteAgency(@RequestParam("id")Long id , @RequestParam("userName")String userName){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =companyChainService.inviteAgency(id,userName);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }


   

}
