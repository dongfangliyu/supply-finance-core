package cn.fintecher.supply.finance.loan.manager.core.business.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.core.business.service.BusinessFileService;

import java.util.List;


/**
 * 
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-13 08:56:11
 */
@RestController
@RequestMapping("business/file")
public class BusinessFileController {
    @Autowired
    private BusinessFileService businessFileService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertFile", method = RequestMethod.POST)
    public Message insertFile(@RequestBody BusinessFileEntity businessFileEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
    	try {
    		msg.setMessage(businessFileService.insertFile(businessFileEntity));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
     /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/updateFile", method = RequestMethod.POST)
    public Message updateFile(@RequestBody BusinessFileEntity businessFileEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
    	try {
    		msg.setMessage(businessFileService.updateFile(businessFileEntity));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    

    /**
     * 查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectByFile", method = RequestMethod.POST)
    public Message selectByFile(@RequestBody BusinessFileEntity businessFileEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
    	try {
    		msg.setMessage(businessFileService.selectByFile(businessFileEntity));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    /**
     * 根据主键查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryFileByPid", method = RequestMethod.GET)
    public Message queryFileByPid(@RequestParam("pid") String pid){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
    	try {
    		msg.setMessage(businessFileService.queryFileByPid(pid));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    

    @ResponseBody
	@RequestMapping(value = "/queryEnterFileList",method = RequestMethod.GET)
	public Message<List<BusinessFileEntity>> queryEnterFileList(@RequestParam("orderCode") String orderCode){
		try {
			return new Message<List<BusinessFileEntity>>(MessageType.MSG_SUCCESS,"business_core",businessFileService.queryEnterFileList(orderCode));
		} catch (Exception e) {
			e.printStackTrace();
			return  new Message(MessageType.MSG_ERROR,"business_core",e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/querySuppFileList",method = RequestMethod.GET)
	public Message<List<BusinessFileEntity>> querySuppFileList(@RequestParam("suppCode") String suppCode){
		try {
			return new Message<List<BusinessFileEntity>>(MessageType.MSG_SUCCESS,"business_core",businessFileService.querySuppFileList(suppCode));
		} catch (Exception e) {
			e.printStackTrace();
			return  new Message(MessageType.MSG_ERROR,"business_core",e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/countByCodeAndType",method = RequestMethod.GET)
	public Message<Integer> countByCodeAndType(@RequestParam(value = "category")String category,@RequestParam("ownerId") String ownerId){
		try {
			return new Message<Integer>(MessageType.MSG_SUCCESS,"business_core",businessFileService.countByCodeAndType(category,ownerId));
		} catch (Exception e) {
			e.printStackTrace();
			return  new Message(MessageType.MSG_ERROR,"business_core",e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getListByCodeAndType",method = RequestMethod.GET)
	public Message<List<BusinessFileEntity>> getListByCodeAndType(@RequestParam(value = "category")String category,@RequestParam("ownerId") String ownerId){
		try {
			return new Message<List<BusinessFileEntity>>(MessageType.MSG_SUCCESS,"business_core",businessFileService.getListByCodeAndType(category,ownerId));
		} catch (Exception e) {
			e.printStackTrace();
			return  new Message(MessageType.MSG_ERROR,"business_core",e.getMessage());
		}
	}
}