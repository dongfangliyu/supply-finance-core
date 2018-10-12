package cn.fintecher.supply.finance.loan.manager.service.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessOrderFrom;
import cn.fintecher.supply.finance.loan.manager.common.business.response.BusinessOrderResponse;
import cn.fintecher.supply.finance.loan.manager.common.constant.ReturnMsg;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessOrderCore;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessFileService;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessOrderService;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyUserCore;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 14:59:16
 */
@Service("businessOrderService")
public class BusinessOrderServiceImpl implements BusinessOrderService {

	@Autowired
    private BusinessOrderCore businessOrderCore;
	
	@Autowired
	private FCCompanyUserCore fccompanyUserCore;
	
	@Autowired
	private BusinessFileService businessFileService;
    
    @Override
	public Message insertOrder(BusinessOrderEntity businessOrderEntity){
		return businessOrderCore.insertOrder(businessOrderEntity);
	}
	
	@Override
	public Message<List<BusinessOrderEntity>> selectByOrder(BusinessOrderEntity businessOrderEntity) {
		return businessOrderCore.selectByOrder(businessOrderEntity);
	}
	
	@Override
	public Message updateOrder(BusinessOrderEntity businessOrderEntity) {
		return businessOrderCore.updateOrder(businessOrderEntity);
	}
    
	@Override
	public Message<BusinessOrderEntity> queryOrderByPid(String pid) {
		return businessOrderCore.queryOrderByPid(pid);
	}
	
    public Message searchListOrder(BusinessOrderFrom businessOrderFrom){
    	CompanyUserEntity user = getUserEntityByUserName(businessOrderFrom.getUserName());
    	businessOrderFrom.setSupplierId(user.getEnterpriseId());
    	Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
		PagedResponse response = new PagedResponse();
		//查询总数
		Integer count = null;
		Message msgCount = businessOrderCore.queryPageCount(businessOrderFrom);
		if (MessageType.MSG_SUCCESS == msgCount.getCode()){
			count = Integer.parseInt(msgCount.getMessage().toString());
		}else {
			return new Message(MessageType.MSG_ERROR,"business",ReturnMsg.FAILED_CURRENCY);
		}

		//查询列表
		List<BusinessOrderEntity> list = null;
		Message msgList = businessOrderCore.queryPageList(businessOrderFrom);
		if (MessageType.MSG_SUCCESS == msgList.getCode()){
			list = JSONUtil.toList(msgList.getMessage(),BusinessOrderEntity.class);
		}else {
			return new Message(MessageType.MSG_ERROR,"business",ReturnMsg.FAILED_CURRENCY);
		}
		response.setData(list);
		response.setTotal(count);
		response.setPageNo(businessOrderFrom.getPageNo());
		response.setPageSize(businessOrderFrom.getPageSize());
		msg.setMessage(response);
    	return msg;
    };
	
    public Message selectOrderDetail(Long orderId, String userName){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
    	try {
    		BusinessOrderResponse response = new BusinessOrderResponse();
			 Message<BusinessOrderEntity> msgEntity = this.queryOrderByPid(orderId+"");
			BusinessOrderEntity businessOrderEntity = msgEntity.getMessage();
			response.setBusinessOrder(businessOrderEntity);
			String Code = businessOrderEntity.getOrderCode();
			if (!ChkUtil.isEmpty(Code)) {
				BusinessFileEntity businessFileEntity = new BusinessFileEntity();
				businessFileEntity.setOwnerId(Code);
				businessFileEntity.setStatus(Constants.DATA_STATUS_NOL);
				Message msgFile =businessFileService.selectByFile(businessFileEntity);
				List<BusinessFileEntity> list = JSONUtil.toList(msgFile.getMessage(),BusinessFileEntity.class);
				response.setFileList(list);
			}
			msg.setMessage(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message<>(MessageType.MSG_ERROR,"business",ReturnMsg.FAILED_CURRENCY);
		}
    	return msg;
    };
	
    public Message submitConfirm(Long orderId, String userName){
    
    	BusinessOrderEntity businessOrderEntity = new BusinessOrderEntity();
    	businessOrderEntity.setPid(orderId);
    	businessOrderEntity.setState("1");
    	businessOrderEntity.setAccountConfirmTime(new Date());
    	businessOrderEntity.setUpdateAt(new Date());
    	businessOrderEntity.setUpdateBy(userName);
    	return this.updateOrder(businessOrderEntity);
    };

    
    private CompanyUserEntity getUserEntityByUserName(String userName){
    	CompanyUserEntity user = fccompanyUserCore.findCompanyUserByName(userName);
    	if (ChkUtil.isEmpty(user.getEnterpriseId())) {
    		user.setEnterpriseId(1L);
		}
    	return user;
    }
}

