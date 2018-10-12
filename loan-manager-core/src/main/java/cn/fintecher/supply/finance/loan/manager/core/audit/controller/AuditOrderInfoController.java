package cn.fintecher.supply.finance.loan.manager.core.audit.controller;


import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditRemindForm;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSigningRequest;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.core.audit.service.AuditOrderInfoService;



/**
 * 审批交易订单
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:10:50
 */
@RestController
@RequestMapping("/audit/orderInfo")
public class AuditOrderInfoController {
    @Autowired
    private AuditOrderInfoService auditOrderInfoService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertOrderInfo", method = RequestMethod.POST)
    public Message insertOrderInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditOrderInfoService.insertOrderInfo(auditOrderInfoEntity));
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
    @RequestMapping(value = "/updateOrderInfo", method = RequestMethod.POST)
    public Message updateOrderInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditOrderInfoService.updateOrderInfo(auditOrderInfoEntity));
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
    @RequestMapping(value = "/selectByOrderInfo", method = RequestMethod.POST)
    public Message<List<AuditOrderInfoEntity>> selectByOrderInfo(@RequestBody AuditOrderInfoEntity auditOrderInfoEntity){
    	Message<List<AuditOrderInfoEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditOrderInfoService.selectByOrderInfo(auditOrderInfoEntity));
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
    @RequestMapping(value = "/queryOrderInfoByPid", method = RequestMethod.GET)
    public Message<AuditOrderInfoEntity> queryOrderInfoByPid(@RequestParam("pid") String pid){
    	Message<AuditOrderInfoEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditOrderInfoService.queryOrderInfoByPid(pid));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    
    /**
     * 查询初审列表总数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectFristCount", method = RequestMethod.POST)
    public Message<Integer> selectFristCount(@RequestBody AuditOrderInfoFrom auditOrderInfoFrom){
    	Message<Integer> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditOrderInfoService.selectFristCount(auditOrderInfoFrom));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    /**
     * 查询初审列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectFristList", method = RequestMethod.POST)
    public Message<List<AuditOrderInfoEntity>> selectFristList(@RequestBody AuditOrderInfoFrom auditOrderInfoFrom){
    	Message<List<AuditOrderInfoEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditOrderInfoService.selectFristList(auditOrderInfoFrom));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }


    /**
     * 查询复审列表总数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectSecondCount", method = RequestMethod.POST)
    public Message<Integer> selectSecondCount(@RequestBody AuditOrderInfoFrom auditOrderInfoFrom){
    	Message<Integer> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditOrderInfoService.selectSecondCount(auditOrderInfoFrom));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }

    /**
     * 查询复审列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectSecondList", method = RequestMethod.POST)
    public Message<List<AuditOrderInfoEntity>> selectSecondList(@RequestBody AuditOrderInfoFrom auditOrderInfoFrom){
    	Message<List<AuditOrderInfoEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditOrderInfoService.selectSecondList(auditOrderInfoFrom));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }

    /**
     * 查询终审列表总数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectThirdCount", method = RequestMethod.POST)
    public Message<Integer> selectThirdCount(@RequestBody AuditOrderInfoFrom auditOrderInfoFrom){
    	Message<Integer> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditOrderInfoService.selectThirdCount(auditOrderInfoFrom));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }

    /**
     * 查询终审列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectThirdList", method = RequestMethod.POST)
    public Message<List<AuditOrderInfoEntity>> selectThirdList(@RequestBody AuditOrderInfoFrom auditOrderInfoFrom){
    	Message<List<AuditOrderInfoEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"audit",null);
    	try {
    		msg.setMessage(auditOrderInfoService.selectThirdList(auditOrderInfoFrom));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }

	@ResponseBody
	@RequestMapping(value = "/searchSigningList", method = RequestMethod.POST)
	public Message<List<AuditOrderInfoEntity>> searchSigningList(@RequestBody AuditSigningRequest auditSigningRequest){
		try {
			return new Message<List<AuditOrderInfoEntity>>(MessageType.MSG_SUCCESS,"audit_core",auditOrderInfoService.searchSigningList(auditSigningRequest));
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"audit_core",e.getMessage());
		}
	}


	@ResponseBody
	@RequestMapping(value = "/searchSigningListCount", method = RequestMethod.POST)
	public Message<Integer> searchSigningListCount(@RequestBody AuditSigningRequest auditSigningRequest){
		try {
			return new Message<Integer>(MessageType.MSG_SUCCESS,"audit_core",auditOrderInfoService.searchSigningListCount(auditSigningRequest));
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"audit_core",e.getMessage());
		}
	}
	/**
	 * 查询逾期列表
	 * @param auditSigningRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOverdueOrderInfo", method = RequestMethod.GET)
	public Message<List<AuditOrderInfoEntity>> getOverdueOrderInfo(){
		try {
			return new Message<List<AuditOrderInfoEntity>>(MessageType.MSG_SUCCESS,"audit_core",auditOrderInfoService.getOverdueOrderInfo());
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"audit_core",e.getMessage());
		}
	}


	@ResponseBody
	@RequestMapping(value = "/searchEnterSigningList", method = RequestMethod.POST)
	public Message<List<AuditOrderInfoEntity>> searchEnterSigningList(@RequestBody AuditSigningRequest auditSigningRequest){
		try {
			return new Message<List<AuditOrderInfoEntity>>(MessageType.MSG_SUCCESS,"audit_core",auditOrderInfoService.searchEnterSigningList(auditSigningRequest));
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"audit_core",e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/searchEnterSigningListCount", method = RequestMethod.POST)
	public Message<Integer> searchEnterSigningListCount(@RequestBody AuditSigningRequest auditSigningRequest){
		try {
			return new Message<Integer>(MessageType.MSG_SUCCESS,"audit_core",auditOrderInfoService.searchEnterSigningListCount(auditSigningRequest));
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"audit_core",e.getMessage());
		}
	}


	@ResponseBody
	@RequestMapping(value = "/searchPlatformSigningList", method = RequestMethod.POST)
	public Message<List<AuditOrderInfoEntity>> searchPlatformSigningList(@RequestBody AuditSigningRequest auditSigningRequest){
		try {
			return new Message<List<AuditOrderInfoEntity>>(MessageType.MSG_SUCCESS,"audit_core",auditOrderInfoService.searchPlatformSigningList(auditSigningRequest));
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"audit_core",e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/searchPlatformSigningListCount", method = RequestMethod.POST)
	public Message<Integer> searchPlatformSigningListCount(@RequestBody AuditSigningRequest auditSigningRequest){
		try {
			return new Message<Integer>(MessageType.MSG_SUCCESS,"audit_core",auditOrderInfoService.searchPlatformSigningListCount(auditSigningRequest));
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"audit_core",e.getMessage());
		}
	}

    @ResponseBody
    @RequestMapping(value = "/searchRemindListCount", method = RequestMethod.POST)
    public int searchRemindListCount(@RequestBody AuditRemindForm auditRemindForm){
        return auditOrderInfoService.searchRemindListCount(auditRemindForm);
    }

    @ResponseBody
    @RequestMapping(value = "/searchRemindList", method = RequestMethod.POST)
    public List<AuditOrderInfoEntity> searchRemindList(@RequestBody AuditRemindForm auditRemindForm){
        return auditOrderInfoService.searchRemindList(auditRemindForm);
    }

	@ResponseBody
	@RequestMapping(value = "/searchReviewListCount", method = RequestMethod.POST)
	public int searchReviewListCount(@RequestBody AuditRemindForm auditRemindForm){
		return auditOrderInfoService.searchReviewListCount(auditRemindForm);
	}

	@ResponseBody
	@RequestMapping(value = "/searchReviewList", method = RequestMethod.POST)
	public List<AuditOrderInfoEntity> searchReviewList(@RequestBody AuditRemindForm auditRemindForm){
		return auditOrderInfoService.searchReviewList(auditRemindForm);
	}


	@ResponseBody
	@RequestMapping(value = "/searchSuppReviewList", method = RequestMethod.POST)
	public Message<List<AuditOrderInfoEntity>> searchSuppReviewList(@RequestBody AuditSuppReviewRequest auditSuppReviewRequest){
		try {
			return new Message<List<AuditOrderInfoEntity>>(MessageType.MSG_SUCCESS,"audit_core",auditOrderInfoService.searchSuppReviewList(auditSuppReviewRequest));
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"audit_core",e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/searchSuppReviewListCount", method = RequestMethod.POST)
	public Message<Integer> searchSuppReviewListCount(@RequestBody AuditSuppReviewRequest auditSuppReviewRequest){
		try {
			return new Message<Integer>(MessageType.MSG_SUCCESS,"audit_core",auditOrderInfoService.searchSuppReviewListCount(auditSuppReviewRequest));
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"audit_core",e.getMessage());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/searchEntryListCount", method = RequestMethod.POST)
	public int searchEntryListCount(@RequestBody AuditRemindForm auditRemindForm){
		return auditOrderInfoService.searchEntryListCount(auditRemindForm);
	}

	@ResponseBody
	@RequestMapping(value = "/searchEntryList", method = RequestMethod.POST)
	public List<AuditOrderInfoEntity> searchEntryList(@RequestBody AuditRemindForm auditRemindForm){
		return auditOrderInfoService.searchEntryList(auditRemindForm);
	}

}