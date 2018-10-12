package cn.fintecher.supply.finance.loan.manager.service.audit.service.impl;

import com.google.common.base.Strings;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditOrderInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSumbitContentRequest;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditExamineResponse;
import cn.fintecher.supply.finance.loan.manager.common.audit.response.AuditFirstTrialResponse;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.constant.ReturnMsg;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyOperationEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.DateUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditSysUserCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditOrderInfoService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditSecondTrialService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditTaskHistoryService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditTaskService;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessFileService;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessOrderService;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyEnterpriseCore;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-19 10:01:21
 */
@Service("auditSecondTrialService")
public class AuditSecondTrialServiceImpl implements AuditSecondTrialService {

	
	@Autowired
	private BusinessOrderService businessOrderService;
	
	@Autowired
	private BusinessFileService businessFileService;
	
	@Autowired
	private AuditTaskService auditTaskService;
	
	@Autowired
	private AuditOrderInfoService auditOrderInfoService;
	
	@Autowired
	private AuditTaskHistoryService auditTaskHistoryService;
	
	@Autowired
	private FCCompanyEnterpriseCore fccompanyEnterpriseCore;
	
	@Autowired
	private AuditSysUserCore auditSysUserCore;
	/**
	 * 复审领取任务
	 */
    public Message receiveTask(SysUserAdminEntity user){
    	Message msg = new Message<>(MessageType.MSG_SUCCESS,"audit",ReturnMsg.SSUCCESS_CURRENCY);
    	try {
    		//判断是否有审核权限
    		if (!IsExamineAuth(new Long(user.getUserId()), Constants.SECOND_ROLE_ID)) {
				 new Message<>(MessageType.MSG_ERROR,"audit","无领取权限");
			}
    		String userName = user.getUsername();
    		//查询可领取任务
			BusinessOrderEntity businessOrderEntity = new BusinessOrderEntity();
			businessOrderEntity.setProcessStatus(Constants.APPROVAL_SECOND_STAY);
			businessOrderEntity.setStatus(Constants.DATA_STATUS_NOL);
			Message<List<BusinessOrderEntity>> resultMsg = businessOrderService.selectByOrder(businessOrderEntity);
			if (resultMsg.getCode() == MessageType.MSG_ERROR) {
				return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
			}
			//可领取任务列表
			List<BusinessOrderEntity> listCanReceive = resultMsg.getMessage();
			if (ChkUtil.isEmpty(listCanReceive)) {
				return new Message<>(MessageType.MSG_ERROR,"audit","暂无可领取任务，请稍后重试。");
			}
			//查询已领取任务
			AuditTaskEntity auditTaskEntity = new AuditTaskEntity();
			auditTaskEntity.setNode(Constants.TASK_SECOND_NODE);
			auditTaskEntity.setOwerId(new Long(user.getId()));
			auditTaskEntity.setStatus(Constants.DATA_STATUS_NOL);
			auditTaskEntity.setType(Constants.TASK_HISTORY_FACTORING);
			Message<List<AuditTaskEntity>> msgAlready = auditTaskService.selectByTask(auditTaskEntity);
			List<AuditTaskEntity> listAlready = msgAlready.getMessage();
			//已领取任务总数
			Integer conut = listAlready.size();
			
			//可领取任务的数量
			Integer sureNum = Constants.RECEIVE_MAX_NUM - conut;
			if (sureNum <= 0) {
				//返回当前待处理任务已达上限
				return new Message(MessageType.MSG_ERROR,"audit","当前可领取任务已达上限");
			}
			//修改order状态为已领取，修改order_info信息，添加task信息
			for (int i = 0; i < listCanReceive.size(); i++) {
				BusinessOrderEntity resultEntity = listCanReceive.get(i);
				BusinessOrderEntity orderEntity = new BusinessOrderEntity();
				orderEntity.setPid(resultEntity.getPid());
				orderEntity.setProcessStatus(Constants.APPROVAL_SECOND_ALREADY); //改为复审已领取
				orderEntity.setUpdateAt(new Date());
				orderEntity.setUpdateBy(userName);
				//修改order状态为已领取
				Message updateMsg = businessOrderService.updateOrder(orderEntity);
				if (updateMsg.getCode() == MessageType.MSG_ERROR) {
					return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
				}
				
				//查询orderInfo存在的信息
				AuditOrderInfoEntity queryInfoEntity = new AuditOrderInfoEntity();
				queryInfoEntity.setStatus(Constants.DATA_STATUS_NOL);
				queryInfoEntity.setOrderId(resultEntity.getPid());
				Message<List<AuditOrderInfoEntity>> returnInfoMsg = auditOrderInfoService.selectByOrderInfo(queryInfoEntity);
				if (returnInfoMsg.getCode() == MessageType.MSG_ERROR || ChkUtil.isEmpty(returnInfoMsg.getMessage())) {
					return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
				}
				queryInfoEntity = returnInfoMsg.getMessage().get(0);
				
				//修改orderInfo信息为已领取状态
				AuditOrderInfoEntity infoEntity = new AuditOrderInfoEntity();
				infoEntity.setPid(queryInfoEntity.getPid());
				infoEntity.setState(Constants.APPROVAL_SECOND_ALREADY);
				infoEntity.setUpdateBy(userName);
				infoEntity.setUpdateAt(new Date());
				Message infoMsg = auditOrderInfoService.updateOrderInfo(infoEntity);
				if (infoMsg.getCode() == MessageType.MSG_ERROR) {
					return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
				}
				
				//添加关联表信息
				AuditTaskEntity taskEntity = new AuditTaskEntity();
				taskEntity.setOwerId(new Long(user.getId()));
				taskEntity.setStatus(Constants.DATA_STATUS_NOL);
				taskEntity.setNode(Constants.TASK_SECOND_NODE);
				taskEntity.setOrderInfoId(queryInfoEntity.getPid());
				taskEntity.setCreateBy(userName);
				taskEntity.setUpdateBy(userName);
				taskEntity.setCreateAt(new Date());
				taskEntity.setUpdateAt(new Date());
				taskEntity.setType(Constants.TASK_HISTORY_FACTORING);
				Message taskMsg = auditTaskService.insertTask(taskEntity);
				if (taskMsg.getCode() == MessageType.MSG_ERROR) {
					return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
				}
				if (i == sureNum - 1) {
					break;
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
		}
    	return msg;
    };
	/**
	 * 查询复审列表
	 */
    public Message searchOrderList(AuditOrderInfoFrom auditOrderInfoFrom){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"audit",ReturnMsg.SSUCCESS_CURRENCY);
		PagedResponse response = new PagedResponse();
    	try {
			if (!Strings.isNullOrEmpty(auditOrderInfoFrom.getStartTime())){
				// 转换日期
				auditOrderInfoFrom.setStartTime(DateUtil.TransformatStartTime(auditOrderInfoFrom.getStartTime()));
			}
			if (!Strings.isNullOrEmpty(auditOrderInfoFrom.getEndTime())){
				// 转换日期
				auditOrderInfoFrom.setEndTime(DateUtil.TransformatEndTime(auditOrderInfoFrom.getEndTime()));
			}
			auditOrderInfoFrom.setNode(Constants.TASK_SECOND_NODE);
			Message<Integer> countMsg = auditOrderInfoService.selectSecondCount(auditOrderInfoFrom);
			Message<List<AuditOrderInfoEntity>> listMsg = auditOrderInfoService.selectSecondList(auditOrderInfoFrom);
			if (countMsg.getCode()==MessageType.MSG_ERROR || listMsg.getCode()==MessageType.MSG_ERROR) {
				return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
			}
			AuditExamineResponse AuditExamineResponse= new AuditExamineResponse();
			AuditExamineResponse.setList(listMsg.getMessage());
			AuditOrderInfoFrom infoFrom = new AuditOrderInfoFrom();
			infoFrom.setUserId(auditOrderInfoFrom.getUserId());
			infoFrom.setNode(Constants.TASK_SECOND_NODE);
			infoFrom.setTaskStatus(Constants.DATA_STATUS_STP);
			Message<Integer> countNum = auditOrderInfoService.selectFristCount(infoFrom);
			AuditExamineResponse.setWaitNum(countNum.getMessage()+"");
			AuditExamineResponse.setAlreadyNum(((Constants.RECEIVE_MAX_NUM - countNum.getMessage())<=0?0:(Constants.RECEIVE_MAX_NUM - countNum.getMessage()))+"");
			response.setData(AuditExamineResponse);
			response.setTotal(countMsg.getMessage());
			response.setPageNo(auditOrderInfoFrom.getPageNo());
			response.setPageSize(auditOrderInfoFrom.getPageSize());
			msg.setMessage(response);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
		}
    	
    	return msg;
    };
	/**
	 * 查看详情
	 */
    public Message searchDetail(Long pid){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"audit",ReturnMsg.SSUCCESS_CURRENCY);
    	try{
    		AuditFirstTrialResponse response = getAuditFirstTrialResponse(pid);
    		if (response.getFlag() == -1) {
    			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
			}
    		msg.setMessage(response);
    		
    	} catch (Exception e) {
			e.printStackTrace();
			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
		}
    	return msg;
    };
	
    /**
     * 提交审批
     */
    public Message submitContnet(AuditSumbitContentRequest auditSumbitContentRequest, SysUserAdminEntity user){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"audit",ReturnMsg.SSUCCESS_CURRENCY);
    	try {
    		//判断审核结果字段是否为复审可处理字段
    		if (ChkUtil.isEmpty(auditSumbitContentRequest.getResult())) {
    			return new Message<>(MessageType.MSG_ERROR,"audit","审批结果不能为空");
			}
    		if (!(auditSumbitContentRequest.getResult().equals(Constants.APPROVAL_SECOND_REFUSE )||
    				auditSumbitContentRequest.getResult().equals(Constants.APPROVAL_SECOND_CANCEL) ||
    				auditSumbitContentRequest.getResult().equals(Constants.APPROVAL_SECOND_STAY) ||
    				auditSumbitContentRequest.getResult().equals( Constants.APPROVAL_THIRD_STAY)) ) {
    			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
			}
    		if (auditSumbitContentRequest.getResult().equals( Constants.APPROVAL_THIRD_STAY)) {
				if (ChkUtil.isEmpty(auditSumbitContentRequest.getApprovalAmount())) {
					return new Message<>(MessageType.MSG_ERROR,"audit","审批金额不能为空");
				}
				if (ChkUtil.isEmpty(auditSumbitContentRequest.getApprovalTerm())) {
					return new Message<>(MessageType.MSG_ERROR,"audit","审批期限不能为空");
				}
			}
    		//判断该条数据是否为复审待处理数据
    		Message<AuditOrderInfoEntity> infoMsg = auditOrderInfoService.queryOrderInfoByPid(auditSumbitContentRequest.getId()+"");
    		if (infoMsg.getCode() == MessageType.MSG_ERROR) {
    			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
			}
    		AuditOrderInfoEntity auditOrderInfo = infoMsg.getMessage();
    		//判断金额与期限是否超过
    		if (auditSumbitContentRequest.getResult().equals(Constants.APPROVAL_THIRD_STAY )) {
    			if (Double.parseDouble(auditSumbitContentRequest.getApprovalAmount())*10L > Double.parseDouble(auditOrderInfo.getCertificateAmount())*8L) {
        			return new Message<>(MessageType.MSG_ERROR,"audit","审批金额不能超过账款金额的80%");
    			}
        			Date start = auditOrderInfo.getAccountStartTime();
            		Date end = auditOrderInfo.getAccountEndTime();
            		Integer day =(DateUtil.diffDays(start, end));
        		if (Integer.parseInt(auditSumbitContentRequest.getApprovalTerm()) > day) {
        			return new Message<>(MessageType.MSG_ERROR,"audit","审批期限不能超过账款期限");
    			}
			}
    		
    		if (!Constants.APPROVAL_SECOND_ALREADY.equals(auditOrderInfo.getState())) {
    			//该条数据为非复审待审核数据，返回报错
    			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
			}
    		//判断当前用户是否能处理此数据
    		
    		AuditTaskEntity auditTaskEntity= new AuditTaskEntity();
    		auditTaskEntity.setNode(Constants.TASK_SECOND_NODE);
    		auditTaskEntity.setOwerId(new Long(user.getId()));
    		auditTaskEntity.setOrderInfoId(auditSumbitContentRequest.getId());
    		auditTaskEntity.setType(Constants.TASK_HISTORY_FACTORING);
    		Message<List<AuditTaskEntity>> auditTaskMsg = auditTaskService.selectByTask(auditTaskEntity);
    		if (auditTaskMsg.getCode() == MessageType.MSG_ERROR) {
    			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
			}
    		List<AuditTaskEntity> list = auditTaskMsg.getMessage();
    		if (list.size()<1) {
    			return new Message<>(MessageType.MSG_ERROR,"audit","无处理权限");
			}
    		auditTaskEntity=list.get(0);
    		
    		//新增审核记录信息
    		AuditTaskHistoryEntity auditTaskHistoryEntity = new AuditTaskHistoryEntity();
    		auditTaskHistoryEntity.setNode("复审");
    		auditTaskHistoryEntity.setApprovalTime(new Date());
    		if (auditSumbitContentRequest.getResult().equals(Constants.APPROVAL_SECOND_REFUSE)) {
    			auditTaskHistoryEntity.setResult("复审拒绝");
			}else if (auditSumbitContentRequest.getResult().equals(Constants.APPROVAL_SECOND_CANCEL)){
				auditTaskHistoryEntity.setResult("复审取消");
			}else if (auditSumbitContentRequest.getResult().equals(Constants.APPROVAL_SECOND_BACK)){
				auditTaskHistoryEntity.setResult("复审回退");
			}else if (auditSumbitContentRequest.getResult().equals(Constants.APPROVAL_THIRD_STAY)){
				auditTaskHistoryEntity.setResult("复审通过");
			}
    		if (auditSumbitContentRequest.getResult().equals( Constants.APPROVAL_THIRD_STAY)) {
        			auditTaskHistoryEntity.setApplicationAmount(auditSumbitContentRequest.getApprovalAmount());
        			auditTaskHistoryEntity.setApplicationTerm(Integer.parseInt(auditSumbitContentRequest.getApprovalTerm()));
        		}
    		auditTaskHistoryEntity.setResultStatus(auditSumbitContentRequest.getResult());
    		auditTaskHistoryEntity.setApprovalMan(user.getRealname());
    		auditTaskHistoryEntity.setOrderId(auditOrderInfo.getOrderId());
    		auditTaskHistoryEntity.setContent(auditSumbitContentRequest.getContent());
    		auditTaskHistoryEntity.setCreateAt(new Date());
    		auditTaskHistoryEntity.setUpdateAt(new Date());
    		auditTaskHistoryEntity.setUpdateBy(user.getUsername());
    		auditTaskHistoryEntity.setCreateBy(user.getUsername());
    		auditTaskHistoryEntity.setStatus(Constants.DATA_STATUS_NOL);
    		auditTaskHistoryEntity.setType(Constants.TASK_HISTORY_FACTORING);
    		Message thMsg =auditTaskHistoryService.insertTaskHistory(auditTaskHistoryEntity);
    		if (thMsg.getCode() == MessageType.MSG_ERROR) {
    			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
			}
    		
    		//修改order信息
    		BusinessOrderEntity order = new BusinessOrderEntity();
    		order.setPid(auditOrderInfo.getOrderId());
			if (auditSumbitContentRequest.getResult().equals(Constants.APPROVAL_FIRST_REFUSE )||
					auditSumbitContentRequest.getResult().equals(Constants.APPROVAL_FIRST_CANCEL )||
					auditSumbitContentRequest.getResult().equals(Constants.APPROVAL_SECOND_REFUSE )||
					auditSumbitContentRequest.getResult().equals(Constants.APPROVAL_SECOND_CANCEL )||
					auditSumbitContentRequest.getResult().equals(Constants.APPROVAL_THIRD_REFUSE )||
					auditSumbitContentRequest.getResult().equals(Constants.APPROVAL_THIRD_CANCEL )) {
				//警告此状态没加常量类
				order.setFinancingStatus("3");
			}
    		order.setProcessStatus(auditSumbitContentRequest.getResult());
    		order.setUpdateAt(new Date());
    		order.setUpdateBy(user.getUsername());
    		
    		Message orMsg =businessOrderService.updateOrder(order);
    		if (orMsg.getCode() == MessageType.MSG_ERROR) {
    			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
			}
    		
    		//修改orderInfo信息
    		AuditOrderInfoEntity orderInfo = new AuditOrderInfoEntity();
    		if (auditSumbitContentRequest.getResult().equals( Constants.APPROVAL_THIRD_STAY)) {
    			orderInfo.setApprovalAmount(auditSumbitContentRequest.getApprovalAmount());
    			orderInfo.setApprovalTerm(Integer.parseInt(auditSumbitContentRequest.getApprovalTerm()));
    		}
    		orderInfo.setUpdateAt(new Date());
    		orderInfo.setUpdateBy(user.getUsername());
    		orderInfo.setPid(auditOrderInfo.getPid());
    		orderInfo.setState(auditSumbitContentRequest.getResult());
    		orderInfo.setSecondTrialTime(new Date());
    		Message orifMsg =auditOrderInfoService.updateOrderInfo(orderInfo);
    		if (orifMsg.getCode() == MessageType.MSG_ERROR) {
    			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
			}
    		
    		//修改task关联表信息
    		AuditTaskEntity taskEntity= new AuditTaskEntity();
    		taskEntity.setPid(auditTaskEntity.getPid());
    		taskEntity.setUpdateAt(new Date());
    		taskEntity.setUpdateBy(user.getUsername());
    		taskEntity.setStatus(Constants.DATA_STATUS_STP);//修改审核人状态为已审核的状态，目前使用的为停用
    		auditTaskEntity.setType(Constants.TASK_HISTORY_FACTORING);
    		Message tsakMsg =auditTaskService.updateTask(taskEntity);
    		if (orifMsg.getCode() == MessageType.MSG_ERROR) {
    			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
			}
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
		}
    	
    	return msg;
    };
	
    /**
     * 查看审核通过后详情
     */
    public Message searchDetailResult(Long pid){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"audit",ReturnMsg.SSUCCESS_CURRENCY);
    	
    	try{
    		AuditFirstTrialResponse response = getAuditFirstTrialResponse(pid);
    		if (response.getFlag() == -1) {
    			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
			}
    		//复审审核结果
    		List<AuditTaskHistoryEntity> resultlist = response.getResultlist();
    		for (AuditTaskHistoryEntity auditTaskHistoryEntity : resultlist) {
				if ("复审".equals(auditTaskHistoryEntity.getNode())) {
					response.setTaskResult(auditTaskHistoryEntity); 
					break;
				}
			}
    		
    		msg.setMessage(response);
    	} catch (Exception e) {
			e.printStackTrace();
			return new Message<>(MessageType.MSG_ERROR,"audit",ReturnMsg.FAILED_CURRENCY);
		}
    	
    	return msg;
    }
	/**
	 * 根据pid查询复审审核详情
	 * @param pid
	 * @return
	 */
    private AuditFirstTrialResponse getAuditFirstTrialResponse(Long pid){
    	AuditFirstTrialResponse response = new AuditFirstTrialResponse();
    		//查询orderInfo信息
    		Message<AuditOrderInfoEntity> infoMsg = auditOrderInfoService.queryOrderInfoByPid(pid+"");
    		if (infoMsg.getCode() == MessageType.MSG_ERROR) {
    			response.setFlag(-1);
    			return response;
			}
    		AuditOrderInfoEntity auditOrderInfo = infoMsg.getMessage();
    		
    		try {
    			Date start = auditOrderInfo.getAccountStartTime();
        		Date end = auditOrderInfo.getAccountEndTime();
    			response.setDay(DateUtil.diffDays(start, end));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		response.setAuditOrderInfo(auditOrderInfo);
    		
    		if (ChkUtil.isEmpty(auditOrderInfo) || ChkUtil.isEmpty(auditOrderInfo.getOrderId())) {
    			response.setFlag(-1);
    			return response;
			}
    		//查询订单信息
    		Message<BusinessOrderEntity> orderMsg = businessOrderService.queryOrderByPid(auditOrderInfo.getOrderId()+"");
    		if (orderMsg.getCode() == MessageType.MSG_ERROR) {
    			response.setFlag(-1);
    			return response;
			}
    		BusinessOrderEntity businessOrder = orderMsg.getMessage();
    		response.setBusinessOrder(businessOrder);
    		
    		//查询供应商文件信息
    		if (!ChkUtil.isEmpty(businessOrder.getSuppCode())) {
    			BusinessFileEntity businessFileEntity = new BusinessFileEntity();
        		businessFileEntity.setStatus(Constants.DATA_STATUS_NOL);
        		businessFileEntity.setOwnerId(businessOrder.getSuppCode());
        		Message suppFileMsg = businessFileService.selectByFile(businessFileEntity);
        		if (orderMsg.getCode() == MessageType.MSG_SUCCESS) {
        			List<BusinessFileEntity> supplist = JSONUtil.toList(suppFileMsg.getMessage(), BusinessFileEntity.class);
        			response.setSupplist(supplist);
        		}
			}
    		//查询核心企业文件信息
    		if (!ChkUtil.isEmpty(businessOrder.getOrderCode())) {
    			BusinessFileEntity businessFileEntity = new BusinessFileEntity();
        		businessFileEntity.setStatus(Constants.DATA_STATUS_NOL);
        		businessFileEntity.setOwnerId(businessOrder.getOrderCode());
        		Message suppFileMsg = businessFileService.selectByFile(businessFileEntity);
        		if (orderMsg.getCode() == MessageType.MSG_SUCCESS) {
        			List<BusinessFileEntity> certlist = JSONUtil.toList(suppFileMsg.getMessage(), BusinessFileEntity.class);
        			response.setCertlist(certlist);
        		}
			}
    		
    		//查询审批历史
    		AuditTaskHistoryEntity auditTaskHistoryEntity = new AuditTaskHistoryEntity();
    		auditTaskHistoryEntity.setOrderId(auditOrderInfo.getOrderId());
    		auditTaskHistoryEntity.setStatus(Constants.DATA_STATUS_NOL);
    		auditTaskHistoryEntity.setType(Constants.TASK_HISTORY_FACTORING);
    		Message<List<AuditTaskHistoryEntity>> historyMsg = auditTaskHistoryService.selectByTaskHistory(auditTaskHistoryEntity);
    		if (orderMsg.getCode() == MessageType.MSG_SUCCESS) {
    			response.setResultlist(historyMsg.getMessage());
    		}
    		
    		//查询供应商联系人信息
    		if (ChkUtil.isEmpty(businessOrder.getSupplierId())) {
    			response.setFlag(-1);
    			return response;
			}
    		List<CompanyOperationEntity> suppOperationList = fccompanyEnterpriseCore.searchCompanyOperation(businessOrder.getSupplierId());
    		if (suppOperationList.size() == 1) {
    			response.setSuppOperation(suppOperationList.get(0));
			}else {
				for (int i = 0; i < suppOperationList.size(); i++) {
					if ("agent".equals(suppOperationList.get(i).getType())) {
						response.setSuppOperation(suppOperationList.get(i));
						break;
					}
					if (i==suppOperationList.size()-1) {
						response.setSuppOperation(suppOperationList.get(0));
					}
				}
			}
    		
    		//查询核心企业联系人信息
    		if (ChkUtil.isEmpty(businessOrder.getEnterpriseId())) {
    			response.setFlag(-1);
    			return response;
			}
    		List<CompanyOperationEntity> certOperationList = fccompanyEnterpriseCore.searchCompanyOperation(businessOrder.getEnterpriseId());
    		if (certOperationList.size() == 1) {
    			response.setCertOperation(certOperationList.get(0));
			}else {
				for (int i = 0; i < certOperationList.size(); i++) {
					if ("agent".equals(certOperationList.get(i).getType())) {
						response.setCertOperation(certOperationList.get(i));
						break;
					}
					if (i==certOperationList.size()-1) {
						response.setCertOperation(certOperationList.get(0));
					}
				}
			}
    		response.setFlag(1);
    		return response;
    }
    	
    private Boolean  IsExamineAuth(Long userId,Integer roleId){
    	if (userId.intValue() == Constants.SYSTEM_ADMIN) {
			return true;
		}
    	Message<List<SysRoleEntity>> msg = auditSysUserCore.getRolesByUserId(userId);
    	List<SysRoleEntity> list = msg.getMessage();
    	for (SysRoleEntity sysRoleEntity : list) {
			if (roleId == sysRoleEntity.getRoleId()) {
				return true;
			}
		}
    	return false;
    }

}
