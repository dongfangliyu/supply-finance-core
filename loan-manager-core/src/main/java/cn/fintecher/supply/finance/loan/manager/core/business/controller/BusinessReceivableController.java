package cn.fintecher.supply.finance.loan.manager.core.business.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableFrom;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CompanyChainFrom;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.core.business.service.BusinessReceivableService;



/**
 * 应收账款
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
@RestController
@RequestMapping("business/Receivable")
public class BusinessReceivableController {
    @Autowired
    private BusinessReceivableService businessReceivableService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertReceivable", method = RequestMethod.POST)
    public Message insertReceivable(@RequestBody BusinessReceivableEntity businessReceivableEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
    	try {
    		msg.setMessage(businessReceivableService.insertReceivable(businessReceivableEntity));
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
    @RequestMapping(value = "/updateReceivable", method = RequestMethod.POST)
    public Message updateReceivable(@RequestBody BusinessReceivableEntity businessReceivableEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
    	try {
    		msg.setMessage(businessReceivableService.updateReceivable(businessReceivableEntity));
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
    @RequestMapping(value = "/selectByReceivable", method = RequestMethod.POST)
    public Message selectByReceivable(@RequestBody BusinessReceivableEntity businessReceivableEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
    	try {
    		msg.setMessage(businessReceivableService.selectByReceivable(businessReceivableEntity));
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
    @RequestMapping(value = "/queryReceivableByPid", method = RequestMethod.GET)
    public Message queryReceivableByPid(@RequestParam("pid") String pid){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
    	try {
    		msg.setMessage(businessReceivableService.queryReceivableByPid(pid));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    /**
	 * 分页查询条数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPageCount", method = RequestMethod.POST)
	public Message queryPageCount(@RequestBody BusinessReceivableFrom businessReceivableFrom){
		Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
		try {
			msg.setMessage(businessReceivableService.queryPageCount(businessReceivableFrom));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;
	}

	/**
	 * 分页查询List内容
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPageList", method = RequestMethod.POST)
	public Message queryPageList(@RequestBody BusinessReceivableFrom businessReceivableFrom){
		Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
		try {
			msg.setMessage(businessReceivableService.queryPageList(businessReceivableFrom));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;
	}
	
	/**
	 * 查询最新的一条记录的编号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryNewAccountNo", method = RequestMethod.GET)
	public String queryNewAccountNo(){
		return businessReceivableService.queryNewAccountNo();
	}
    
    
    

}