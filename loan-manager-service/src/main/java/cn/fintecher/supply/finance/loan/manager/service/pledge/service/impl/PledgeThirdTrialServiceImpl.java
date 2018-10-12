package cn.fintecher.supply.finance.loan.manager.service.pledge.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskEntity;
import cn.fintecher.supply.finance.loan.manager.common.audit.entity.AuditTaskHistoryEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.constant.ReturnMsg;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseBankInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyOperationEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockTrialFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSumbitTrialRequest;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeTrialExamineResponse;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeTrialInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeTrialListResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysRoleEntity;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.service.audit.core.AuditSysUserCore;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditTaskHistoryService;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditTaskService;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessFileService;
import cn.fintecher.supply.finance.loan.manager.service.commodity.service.CommodityStockInfoService;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyEnterpriseCore;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyEnterpriseInfoService;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyEnterpriseService;
import cn.fintecher.supply.finance.loan.manager.service.credit.feign.FCEnterpriseFinancialCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.feign.PledgeApplyCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeApplyStockInfoService;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeStockInfoService;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeThirdTrialService;
import cn.fintecher.supply.finance.loan.manager.service.pro.feign.FCProProductCore;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 11:12:09
 */
@Service("pledgeThirdTrialService")
public class PledgeThirdTrialServiceImpl implements PledgeThirdTrialService {

	@Autowired
	private AuditSysUserCore auditSysUserCore;

	@Autowired
	private PledgeApplyStockInfoService applyStockInfoService;

	@Autowired
	private PledgeStockInfoService pledgeStockInfoService;

	@Autowired
	private CommodityStockInfoService commodityStockInfoService;

	@Autowired
	private AuditTaskHistoryService auditTaskHistoryService;

	@Autowired
	private CompanyEnterpriseService companyEnterpriseService;

	@Autowired
	private FCCompanyEnterpriseCore fcCompanyEnterpriseCore;

	@Autowired
	private CompanyEnterpriseInfoService companyEnterpriseInfoService;

	@Autowired
	private FCEnterpriseFinancialCore fcEnterpriseFinancialCore;

	@Autowired
	private BusinessFileService businessFileService;

	@Autowired
	private AuditTaskService auditTaskService;
	
	@Autowired
	private FCProProductCore proProductCore;

	@Autowired
	private PledgeApplyCore pledgeApplyCore;

	@Override
	public Message receiveTask(SysUserAdminEntity user) {
		Message msg = new Message<>(MessageType.MSG_SUCCESS,"pledge",ReturnMsg.SSUCCESS_CURRENCY);
		try {
			//判断是否有审核权限
			if (!IsExamineAuth(new Long(user.getUserId()), Constants.PLEDGE_THIRD_ROLE_ID)) {
				new Message<>(MessageType.MSG_ERROR,"pledge","无领取权限");
			}
			String userName = user.getUsername();
			//查询可领取任务 参数 1初审 2复审 3终审
			List<PledgeApplyStockInfoEntity> listSureReceive = pledgeApplyCore.sureReceiveTask(Constants.TASK_THIRD_NODE);

			if (ChkUtil.isEmpty(listSureReceive)) {
				return new Message<>(MessageType.MSG_ERROR,"pledge","暂无可领取任务，请稍后重试。");
			}

			//查询已领取任务
			AuditTaskEntity auditTaskEntity = new AuditTaskEntity();
			auditTaskEntity.setNode(Constants.TASK_THIRD_NODE);
			auditTaskEntity.setOwerId(new Long(user.getId()));
			auditTaskEntity.setStatus(Constants.DATA_STATUS_NOL);
			auditTaskEntity.setType(Constants.TASK_HISTORY_PLEDGE);

			Message<List<AuditTaskEntity>> msgAlready = auditTaskService.selectByTask(auditTaskEntity);

			List<AuditTaskEntity> listAlready = msgAlready.getMessage();
			//已领取任务总数
			Integer conut = listAlready.size();

			//可领取任务的数量
			Integer sureNum = Constants.RECEIVE_MAX_NUM - conut;
			if (sureNum <= 0) {
				//返回当前待处理任务已达上限
				return new Message(MessageType.MSG_ERROR,"pledge","当前可领取任务已达上限");
			}
			//修改apply_stock_info信息,新增AuditTask表信息
			for (int i = 0; i < listSureReceive.size(); i++) {
				PledgeApplyStockInfoEntity resultEntity = listSureReceive.get(i);
				PledgeApplyStockInfoEntity updateEntity = new PledgeApplyStockInfoEntity();
				updateEntity.setPid(resultEntity.getPid());
				updateEntity.setApplyFinalState(Constants.PLEDGE_TRIAL_ALREADY); //改为待审核
				updateEntity.setUpdateAt(new Date());
				updateEntity.setUpdateBy(userName);
				//修改apply_stock_info状态为已领取
				Message updateMsg = this.applyStockInfoService.updateApplyStockInfo(updateEntity);
				if (updateMsg.getCode() == MessageType.MSG_ERROR) {
					return new Message<>(MessageType.MSG_ERROR,"pledge",ReturnMsg.FAILED_CURRENCY);
				}

				//新增task记录信息
				AuditTaskEntity taskEntity = new AuditTaskEntity();
				taskEntity.setOwerId(new Long(user.getId()));
				taskEntity.setStatus(Constants.DATA_STATUS_NOL);
				taskEntity.setNode(Constants.TASK_THIRD_NODE);
				taskEntity.setOrderInfoId(resultEntity.getPid());
				taskEntity.setCreateBy(userName);
				taskEntity.setUpdateBy(userName);
				taskEntity.setCreateAt(new Date());
				taskEntity.setUpdateAt(new Date());
				taskEntity.setType(Constants.TASK_HISTORY_PLEDGE);
				Message taskMsg = auditTaskService.insertTask(taskEntity);
				if (taskMsg.getCode() == MessageType.MSG_ERROR) {
					return new Message<>(MessageType.MSG_ERROR,"pledge",ReturnMsg.FAILED_CURRENCY);
				}
				if (i == sureNum - 1) {
					break;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message<>(MessageType.MSG_ERROR,"pledge",ReturnMsg.FAILED_CURRENCY);
		}
		return msg;
	}

	@Override
	public Message searchPledgeList(PledgeStockTrialFrom pledgeStockTrialFrom, SysUserAdminEntity user) {
		Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",ReturnMsg.SSUCCESS_CURRENCY);
		PagedResponse response = new PagedResponse();
		try {

			pledgeStockTrialFrom.setUserId(user.getUserId().toString());
			pledgeStockTrialFrom.setNode(Constants.TASK_THIRD_NODE);
			//总数
			Integer count = pledgeApplyCore.selectTrialCount(pledgeStockTrialFrom);
			//列表
			List<PledgeTrialListResponse> list = pledgeApplyCore.selectTrialList(pledgeStockTrialFrom);

			PledgeTrialExamineResponse pledgeTrialExamineResponse= new PledgeTrialExamineResponse();
			pledgeTrialExamineResponse.setList(list);
			//待处理任务
			PledgeStockTrialFrom infoFrom = new PledgeStockTrialFrom();
			infoFrom.setUserId(user.getUserId().toString());
			infoFrom.setNode(Constants.TASK_THIRD_NODE);
			infoFrom.setTaskStatus(Constants.DATA_STATUS_STP);
			Integer countNum = pledgeApplyCore.selectTrialCount(infoFrom);
			pledgeTrialExamineResponse.setWaitNum(countNum.toString());
			//可领取任务
			pledgeTrialExamineResponse.setAlreadyNum(((Constants.RECEIVE_MAX_NUM - countNum)<=0?0:(Constants.RECEIVE_MAX_NUM - countNum))+"");
			response.setData(pledgeTrialExamineResponse);
			response.setTotal(count);
			response.setPageNo(pledgeStockTrialFrom.getPageNo());
			response.setPageSize(pledgeStockTrialFrom.getPageSize());
			msg.setMessage(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message<>(MessageType.MSG_ERROR,"pledge",ReturnMsg.FAILED_CURRENCY);
		}

		return msg;
	}

	@Override
	public Message searchDetail(Long id, SysUserAdminEntity sysUserAdminEntity) {
		Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
		PledgeTrialInfoResponse response = new PledgeTrialInfoResponse();
		//查询申请负表信息
		PledgeApplyStockInfoEntity applyStockInfo = this.applyStockInfoService.queryApplyStockInfoByPid(id.toString()).getMessage();
		//查询申请表信息
		PledgeStockInfoEntity stockInfo = this.pledgeStockInfoService.queryStockInfoByPid(applyStockInfo.getPledgeId().toString()).getMessage();
		//查询入库申请表信息
		CommodityStockInfoEntity commodityStockInfoEntity = this.commodityStockInfoService.queryStockInfoByPid(stockInfo.getCommodityId().toString()).getMessage();

		//查询审批历史
		AuditTaskHistoryEntity auditTaskHistoryEntity = new AuditTaskHistoryEntity();
		auditTaskHistoryEntity.setOrderId(id);
		auditTaskHistoryEntity.setStatus(Constants.DATA_STATUS_NOL);
		auditTaskHistoryEntity.setType(Constants.TASK_HISTORY_PLEDGE);
		List<AuditTaskHistoryEntity> historyList = auditTaskHistoryService.selectByTaskHistory(auditTaskHistoryEntity).getMessage();

		for (AuditTaskHistoryEntity auditTask : historyList) {
			if ("终审".equals(auditTask.getNode())) {
				response.setTaskResult(auditTask);
				break;
			}
		}

		BusinessFileEntity businessFileEntity = new BusinessFileEntity();
		businessFileEntity.setCommodityId(commodityStockInfoEntity.getPid());
		businessFileEntity.setStatus(Constants.DATA_STATUS_NOL);
		List<BusinessFileEntity> files = JSONUtil.toList(businessFileService.selectByFile(businessFileEntity).getMessage(), BusinessFileEntity.class);

		//企业信息
		CompanyEnterpriseEntity companyEnterpriseEntity = companyEnterpriseService.searchCompanyEnterpriseEntity(commodityStockInfoEntity.getCompanyId());
		//企业银行卡信息
		BaseBankInfoEntity baseBankInfoEntity = fcCompanyEnterpriseCore.searchBaseBankInfo(commodityStockInfoEntity.getCompanyId());
		//企业信息详情
		CompanyEnterpriseInfoEntity companyEnterpriseInfoEntity = companyEnterpriseInfoService.searchCompanyEnterpriseInfoEntity(commodityStockInfoEntity.getCompanyId());
		//企业联系人列表
		List<CompanyOperationEntity> list = fcCompanyEnterpriseCore.searchCompanyOperation(commodityStockInfoEntity.getCompanyId());
		//企业文件列表
		List<CompanyFileEntity> companyFileEntities = fcEnterpriseFinancialCore.searchAccountingStatementInfo(commodityStockInfoEntity.getCompanyId());

		response.setCompanyEnterprise(companyEnterpriseEntity);
		response.setBaseBankInfo(baseBankInfoEntity);
		response.setCompanyEnterpriseInfo(companyEnterpriseInfoEntity);
		response.setOperationList(list);
		response.setFileEntities(companyFileEntities);


		response.setApplyStockInfo(applyStockInfo);
		response.setStockInfo(stockInfo);
		response.setResultlist(historyList);
		response.setFiles(files);
		response.setCommodityStockInfo(commodityStockInfoEntity);



		msg.setMessage(response);
		return msg;
	}

	@Override
	public Message submitContnet(PledgeSumbitTrialRequest pledgeSumbitTrialRequest,
			SysUserAdminEntity user) {
		PledgeApplyStockInfoEntity applyStockInfo = applyStockInfoService.queryApplyStockInfoByPid(pledgeSumbitTrialRequest.getId().toString()).getMessage();
		if (!Constants.PLEDGE_TRIAL_ALREADY.equals(applyStockInfo.getApplyFinalState())) {
			return new Message(MessageType.MSG_ERROR,"pledge","当前数据状态不正确");
		}
		if (pledgeSumbitTrialRequest.getResult()==null||pledgeSumbitTrialRequest.getResult().equals("")) {
			return new Message(MessageType.MSG_ERROR,"pledge","审核结果不能为空");
		}
		if (!(pledgeSumbitTrialRequest.getResult().equals(Constants.PLEDGE_TRIAL_REFUSE)||
				pledgeSumbitTrialRequest.getResult().equals(Constants.PLEDGE_TRIAL_CANCEL)||
				pledgeSumbitTrialRequest.getResult().equals(Constants.PLEDGE_TRIAL_ADOPT))) {
			return new Message(MessageType.MSG_ERROR,"pledge","审核结果不正确");
		}
		//判断当前用户是否能处理此数据

		AuditTaskEntity auditTaskEntity= new AuditTaskEntity();
		auditTaskEntity.setNode(Constants.TASK_THIRD_NODE);
		auditTaskEntity.setOwerId(new Long(user.getId()));
		auditTaskEntity.setOrderInfoId(pledgeSumbitTrialRequest.getId());
		auditTaskEntity.setType(Constants.TASK_HISTORY_PLEDGE);
		Message<List<AuditTaskEntity>> auditTaskMsg = auditTaskService.selectByTask(auditTaskEntity);
		if (auditTaskMsg.getCode() == MessageType.MSG_ERROR) {
			return new Message<>(MessageType.MSG_ERROR,"pledge",ReturnMsg.FAILED_CURRENCY);
		}
		List<AuditTaskEntity> list = auditTaskMsg.getMessage();
		if (list.size()<1) {
			return new Message<>(MessageType.MSG_ERROR,"pledge","无处理权限");
		}
		auditTaskEntity=list.get(0);


		//新增审核记录信息
		AuditTaskHistoryEntity auditTaskHistoryEntity = new AuditTaskHistoryEntity();
		PledgeStockInfoEntity infoEntity = new PledgeStockInfoEntity();
		PledgeApplyStockInfoEntity updateApplyStockInfo = new PledgeApplyStockInfoEntity();
		CommodityStockInfoEntity  CommodityStockInfoEntity = new CommodityStockInfoEntity();
		PledgeStockInfoEntity StockInfo = pledgeStockInfoService.queryStockInfoByPid(applyStockInfo.getPledgeId().toString()).getMessage();
		CommodityStockInfoEntity csi = commodityStockInfoService.queryStockInfoByPid(StockInfo.getCommodityId().toString()).getMessage();

		CommodityStockInfoEntity.setPid(csi.getPid());
		
		auditTaskHistoryEntity.setNode("终审");
		auditTaskHistoryEntity.setApprovalTime(new Date());
		if (pledgeSumbitTrialRequest.getResult().equals(Constants.PLEDGE_TRIAL_REFUSE)) {
			auditTaskHistoryEntity.setResult("拒绝");
		}else if (pledgeSumbitTrialRequest.getResult().equals(Constants.PLEDGE_TRIAL_CANCEL)){
			auditTaskHistoryEntity.setResult("取消");
		}else if (pledgeSumbitTrialRequest.getResult().equals(Constants.PLEDGE_TRIAL_ADOPT)){
			auditTaskHistoryEntity.setResult("通过");
		}
		//如果为审批通过
		if (pledgeSumbitTrialRequest.getResult().equals(Constants.PLEDGE_TRIAL_ADOPT)) {
			infoEntity.setPledgeApplyState("1");
			infoEntity.setContractApplyState("0");
			infoEntity.setContractElectronics("0");
			ProProductEntity proDetail = proProductCore.searchProductDetail(pledgeSumbitTrialRequest.getProductNoId().toString());
			
			CommodityStockInfoEntity.setState("3");

			//审批总额度 = 审批单价* 审批质押率 * 数量
			BigDecimal approvalAmount = pledgeSumbitTrialRequest.getContractApplyPrice() 
					.multiply(pledgeSumbitTrialRequest.getContractApplyPledgeRate())
					.divide(new BigDecimal(100)).multiply(csi.getProductNumber());
			approvalAmount = approvalAmount.setScale(2, BigDecimal.ROUND_UP);
			
			//服务费=终审金额*服务费率/360*天数
			BigDecimal contractApplyServiceFee = approvalAmount
					.multiply(new BigDecimal(proDetail.getServiceFee()))
					.divide(new BigDecimal(360),2,BigDecimal.ROUND_HALF_UP)
					.multiply(new BigDecimal(pledgeSumbitTrialRequest.getApprovalTerm()))
					.divide(new BigDecimal(100));
			//利息=终审金额*利率/360*天数
			BigDecimal contractApplyInterest = approvalAmount
					.multiply(new BigDecimal(proDetail.getInterestRate()))
					.divide(new BigDecimal(360),2,BigDecimal.ROUND_HALF_UP)
					.multiply(new BigDecimal(pledgeSumbitTrialRequest.getApprovalTerm()))
					.divide(new BigDecimal(100));
			
			auditTaskHistoryEntity.setContractApplyPledgeRate(pledgeSumbitTrialRequest.getContractApplyPledgeRate());
			auditTaskHistoryEntity.setContractApplyPrice(pledgeSumbitTrialRequest.getContractApplyPrice());
			auditTaskHistoryEntity.setContractNo(pledgeSumbitTrialRequest.getContractNo());
			auditTaskHistoryEntity.setProductNo(pledgeSumbitTrialRequest.getProductNo());
			auditTaskHistoryEntity.setProductType(pledgeSumbitTrialRequest.getProductType());
			auditTaskHistoryEntity.setApplicationAmount(approvalAmount.toString());
			auditTaskHistoryEntity.setApplicationTerm(Integer.parseInt(pledgeSumbitTrialRequest.getApprovalTerm()));

			infoEntity.setContractApplyPledgeRate(pledgeSumbitTrialRequest.getContractApplyPledgeRate());
			infoEntity.setContractApplyPrice(pledgeSumbitTrialRequest.getContractApplyPrice());
			infoEntity.setProContractId(pledgeSumbitTrialRequest.getContractNoId());
			infoEntity.setProRoductId(pledgeSumbitTrialRequest.getProductNoId());
			infoEntity.setContractApplyAmount(approvalAmount);
			infoEntity.setContractApplyTerm(new Long(pledgeSumbitTrialRequest.getApprovalTerm()));
			infoEntity.setContractApplyServiceFee(contractApplyServiceFee);
			infoEntity.setContractApplyInterest(contractApplyInterest);
		}else {
			infoEntity.setPledgeApplyState("2");
			
			CommodityStockInfoEntity.setState("4");
		}
		commodityStockInfoService.updateStockInfo(CommodityStockInfoEntity);
		
		//修改pledge_stock_info 信息
		infoEntity.setPledgeNoPassTime(new Date());
		infoEntity.setPid(applyStockInfo.getPledgeId());
		infoEntity.setUpdateAt(new Date());
		infoEntity.setUpdateBy(user.getUsername());
		this.pledgeStockInfoService.updateStockInfo(infoEntity);

		auditTaskHistoryEntity.setResultStatus(pledgeSumbitTrialRequest.getResult());
		auditTaskHistoryEntity.setApprovalMan(user.getRealname());
		auditTaskHistoryEntity.setOrderId(pledgeSumbitTrialRequest.getId());
		auditTaskHistoryEntity.setContent(pledgeSumbitTrialRequest.getContent());
		auditTaskHistoryEntity.setCreateAt(new Date());
		auditTaskHistoryEntity.setUpdateAt(new Date());
		auditTaskHistoryEntity.setUpdateBy(user.getUsername());
		auditTaskHistoryEntity.setCreateBy(user.getUsername());
		auditTaskHistoryEntity.setStatus(Constants.DATA_STATUS_NOL);
		auditTaskHistoryEntity.setType(Constants.TASK_HISTORY_PLEDGE);

		Message thMsg =auditTaskHistoryService.insertTaskHistory(auditTaskHistoryEntity);
		if (thMsg.getCode() == MessageType.MSG_ERROR) {
			return new Message<>(MessageType.MSG_ERROR,"pledge",ReturnMsg.FAILED_CURRENCY);
		}

		//修改ApplyStockInfo表

		updateApplyStockInfo.setApplyFinalState(pledgeSumbitTrialRequest.getResult());
		updateApplyStockInfo.setPid(pledgeSumbitTrialRequest.getId());
		updateApplyStockInfo.setUpdateAt(new Date());
		updateApplyStockInfo.setUpdateBy(user.getUsername());

		updateApplyStockInfo.setApplyFinalTime(new Date());
		applyStockInfoService.updateApplyStockInfo(updateApplyStockInfo);




		//修改task关联表信息
		AuditTaskEntity taskEntity= new AuditTaskEntity();
		taskEntity.setPid(auditTaskEntity.getPid());
		taskEntity.setUpdateAt(new Date());
		taskEntity.setUpdateBy(user.getUsername());
		taskEntity.setStatus(Constants.DATA_STATUS_STP);//修改审核人状态为已审核的状态，目前使用的为停用
		taskEntity.setType(Constants.TASK_HISTORY_PLEDGE);
		Message tsakMsg =auditTaskService.updateTask(taskEntity);
		if (tsakMsg.getCode() == MessageType.MSG_ERROR) {
			return new Message<>(MessageType.MSG_ERROR,"pledge",ReturnMsg.FAILED_CURRENCY);
		}

		return new Message<>(MessageType.MSG_SUCCESS,"pledge",ReturnMsg.SSUCCESS_CURRENCY);
	}

	/**
	 * 判断用户是否有审核权限
	 * @param userId
	 * @param roleId
	 * @return
	 */
	private Boolean  IsExamineAuth(Long userId,Integer roleId){
		return true;
		/*if (userId.intValue() == Constants.SYSTEM_ADMIN) {
			return true;
		}
		Message<List<SysRoleEntity>> msg = auditSysUserCore.getRolesByUserId(userId);
		List<SysRoleEntity> list = msg.getMessage();
		for (SysRoleEntity sysRoleEntity : list) {
			if (roleId == sysRoleEntity.getRoleId()) {
				return true;
			}
		}
		return false;*/
	}
	
}
