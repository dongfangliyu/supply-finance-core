package cn.fintecher.supply.finance.loan.manager.service.pledge.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyBlacklistEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeApplyStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.entity.PledgeStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplyForm;
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeApplySubmit;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeApplyInfoResponse;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeApplyListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.*;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessFileService;
import cn.fintecher.supply.finance.loan.manager.service.commodity.service.CommodityStockInfoService;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyEnterpriseCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.feign.PledgeApplyCore;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeApplyService;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeApplyStockInfoService;
import cn.fintecher.supply.finance.loan.manager.service.pledge.service.PledgeStockInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-23 10:56:11
 */
@Service("pledgeApplyService")
public class PledgeApplyServiceImpl implements PledgeApplyService {


	@Autowired
	private CommodityStockInfoService commodityStockInfoService;
	
	@Autowired
	private BusinessFileService businessFileService;
	
	@Autowired
	private PledgeStockInfoService pledgeStockInfoService;
	
	@Autowired
	private FCCompanyEnterpriseCore companyEnterpriseCore;
	
	@Autowired
	private PledgeApplyStockInfoService applyStockInfoService;

	@Autowired
	private PledgeApplyCore pledgeApplyCore;

	@Override
	public Message searchApplyList(PledgeApplyForm pledgeApplyForm, CompanyUserEntity user) {
		// TODO Auto-generated method stub
		Message msg = new Message<>(MessageType.MSG_SUCCESS,"pledge",null);
		PagedResponse response = new PagedResponse();
		try {
			if(StringUtils.isNotBlank(pledgeApplyForm.getPledgeApplyEndTime())){
				pledgeApplyForm.setPledgeApplyEndTime(DateUtil.TransformatEndTime(pledgeApplyForm.getPledgeApplyEndTime()));
			}
			if(StringUtils.isNotBlank(pledgeApplyForm.getPledgeApplyStratTime())){
				pledgeApplyForm.setPledgeApplyStratTime(DateUtil.TransformatStartTime(pledgeApplyForm.getPledgeApplyStratTime()));
			}
			if(StringUtils.isNotBlank(pledgeApplyForm.getPledgeNoPassStartTime())){
				pledgeApplyForm.setPledgeNoPassStartTime(DateUtil.TransformatStartTime(pledgeApplyForm.getPledgeNoPassStartTime()));
			}
			if(StringUtils.isNotBlank(pledgeApplyForm.getPledgeNoPassEndTime())){
				pledgeApplyForm.setPledgeNoPassEndTime(DateUtil.TransformatEndTime(pledgeApplyForm.getPledgeNoPassEndTime()));
			}
			if(StringUtils.isNotBlank(pledgeApplyForm.getPledgePassEndTime())){
				pledgeApplyForm.setPledgePassEndTime(DateUtil.TransformatEndTime(pledgeApplyForm.getPledgePassEndTime()));
			}
			if(StringUtils.isNotBlank(pledgeApplyForm.getPledgePassStartTime())){
				pledgeApplyForm.setPledgePassStartTime(DateUtil.TransformatStartTime(pledgeApplyForm.getPledgePassStartTime()));
			}
		}catch (Exception e){

		}

		pledgeApplyForm.setCompanyId(user.getEnterpriseId());
		
		//查询列表
		List<PledgeApplyListResponse> list = this.pledgeApplyCore.queryPageListByPledgeApply(pledgeApplyForm);

		//查询总数
		Integer count = this.pledgeApplyCore.queryPageCountByPledgeApply(pledgeApplyForm);

		response.setData(list);
		response.setTotal(count);
		response.setPageNo(pledgeApplyForm.getPageNo());
		response.setPageSize(pledgeApplyForm.getPageSize());
		msg.setMessage(response);
		return msg;
	}

	@Override
	public Message searchApplyDetail(Long id, CompanyUserEntity user) {
		PledgeApplyInfoResponse response = new PledgeApplyInfoResponse();
		CommodityStockInfoEntity commodityStockInfoEntity = this.commodityStockInfoService.queryStockInfoByPid(id.toString()).getMessage();
		if ((long)user.getEnterpriseId() != (long)commodityStockInfoEntity.getCompanyId()) {
			return new Message<>(MessageType.MSG_SUCCESS,"pledge","无查询权限");
		}
		
		BusinessFileEntity businessFileEntity = new BusinessFileEntity();
		businessFileEntity.setCommodityId(commodityStockInfoEntity.getPid());
		businessFileEntity.setStatus(Constants.DATA_STATUS_NOL);
		List<BusinessFileEntity> files = JSONUtil.toList(businessFileService.selectByFile(businessFileEntity).getMessage(), BusinessFileEntity.class);
		PledgeStockInfoEntity pledgeStockInfoEntity = new PledgeStockInfoEntity();
		pledgeStockInfoEntity.setCommodityId(commodityStockInfoEntity.getPid());
		pledgeStockInfoEntity.setStatus(Constants.DATA_STATUS_NOL);
		List<PledgeStockInfoEntity> stockInfoList = pledgeStockInfoService.selectByStockInfo(pledgeStockInfoEntity).getMessage();
		response.setCommodityStockInfo(commodityStockInfoEntity);
		response.setFiles(files);
		if (stockInfoList.size()>0) {
			response.setStockInfo(stockInfoList.get(0));
		}
		return new Message<>(MessageType.MSG_SUCCESS,"pledge",response);
	}

	@Override
	public Message submitApply(PledgeApplySubmit pledgeApplySubmit, CompanyUserEntity user) {
		// TODO Auto-generated method stub
		//新增申请表信息
		CommodityStockInfoEntity commodityStockInfoEntity = this.commodityStockInfoService.queryStockInfoByPid(pledgeApplySubmit.getId().toString()).getMessage();
		
		CompanyBlacklistEntity  companyBlacklistEntity = new CompanyBlacklistEntity(); 
		companyBlacklistEntity.setEvent("1");
		companyBlacklistEntity.setCompanyId(commodityStockInfoEntity.getCompanyId());
		try {
			CompanyEnterpriseEntity companyEnterpriseEntity = this.companyEnterpriseCore.searchCompanyEnterpriseEntity(commodityStockInfoEntity.getCompanyId());
			if ("1".equals(companyEnterpriseEntity.getBlacklist())) {
				return new Message<>(MessageType.MSG_ERROR,"pledge","黑名单用户不能申请");
			}
		} catch (Exception e) {
		}
		
		
		BusinessFileEntity businessFileEntity = new BusinessFileEntity();
		businessFileEntity.setCommodityId(pledgeApplySubmit.getId());
		businessFileEntity.setStatus(Constants.DATA_STATUS_NOL);
		List<BusinessFileEntity> files = JSONUtil.toList(businessFileService.selectByFile(businessFileEntity).getMessage(), BusinessFileEntity.class);
		if (ChkUtil.isEmpty(files)) {
			return new Message<>(MessageType.MSG_ERROR,"pledge","合同未上传");
		}
		for (int i = 0; i < files.size(); i++) {
			if ("pledge_contract".equals(files.get(i).getCategory())) {
				break;
			}
			if (i == files.size()-1) {
				return new Message<>(MessageType.MSG_ERROR,"pledge","合同未上传");
			}
		}
		for (int i = 0; i < files.size(); i++) {
			if ("pledge_purchaseorder".equals(files.get(i).getCategory())) {
				break;
			}
			if (i == files.size()-1) {
				return new Message<>(MessageType.MSG_ERROR,"pledge","采购单未上传");
			}
		}
		for (int i = 0; i < files.size(); i++) {
			if ("pledge_invoice".equals(files.get(i).getCategory())) {
				break;
			}
			if (i == files.size()-1) {
				return new Message<>(MessageType.MSG_ERROR,"pledge","发票未上传");
			}
		}
		for (int i = 0; i < files.size(); i++) {
			if ("pledge_transport_contract".equals(files.get(i).getCategory())) {
				break;
			}
			if (i == files.size()-1) {
				return new Message<>(MessageType.MSG_ERROR,"pledge","运输合同未上传");
			}
		}
		for (int i = 0; i < files.size(); i++) {
			if ("pledge_transport_list".equals(files.get(i).getCategory())) {
				break;
			}
			if (i == files.size()-1) {
				return new Message<>(MessageType.MSG_ERROR,"pledge","运输清单未上传");
			}
		}
		for (int i = 0; i < files.size(); i++) {
			if ("pledge_voucher".equals(files.get(i).getCategory())) {
				break;
			}
			if (i == files.size()-1) {
				return new Message<>(MessageType.MSG_ERROR,"pledge","打款凭证未上传");
			}
		}
		
		if (!Constants.COMMODITY_STOCK_YES.equals(commodityStockInfoEntity.getState())) {
			return new Message<>(MessageType.MSG_ERROR,"pledge","当前状态不可申请");
		}
		PledgeStockInfoEntity pledgeStockInfoEntity = new PledgeStockInfoEntity();
		pledgeStockInfoEntity.setStatus(Constants.DATA_STATUS_NOL);
		pledgeStockInfoEntity.setCreateAt(new Date());
		pledgeStockInfoEntity.setCreateBy(user.getUserName());
		pledgeStockInfoEntity.setUpdateAt(new Date());
		pledgeStockInfoEntity.setUpdateBy(user.getUserName());
		pledgeStockInfoEntity.setCommodityId(commodityStockInfoEntity.getPid());
		pledgeStockInfoEntity.setPledgeApplyTime(new Date());
		pledgeStockInfoEntity.setPledgeTerm(pledgeApplySubmit.getPledgeTerm());
		pledgeStockInfoEntity.setPledgeFinanceAmount(pledgeApplySubmit.getPledgeFinanceAmount());
		pledgeStockInfoEntity.setPledgeEarmarking(pledgeApplySubmit.getPledgeEarmarking());
		Message msg = this.pledgeStockInfoService.insertStockInfo(pledgeStockInfoEntity);
		
		//新增apply_stock_info信息
		PledgeApplyStockInfoEntity applyStockInfoEntity = new PledgeApplyStockInfoEntity();
		applyStockInfoEntity.setStatus(Constants.DATA_STATUS_NOL);
		applyStockInfoEntity.setApplyType(Constants.TASK_HISTORY_PLEDGE);
		applyStockInfoEntity.setCreateAt(new Date());
		applyStockInfoEntity.setCreateBy(user.getUserName());
		applyStockInfoEntity.setUpdateAt(new Date());
		applyStockInfoEntity.setUpdateBy(user.getUserName());
		applyStockInfoEntity.setApplyFirstState(Constants.PLEDGE_TRIAL_STAY);
		applyStockInfoEntity.setPledgeId(new Long(msg.getMessage().toString()));
		this.applyStockInfoService.insertApplyStockInfo(applyStockInfoEntity);
		
		
		//修改状态为申请中
		CommodityStockInfoEntity cstockInfo = new CommodityStockInfoEntity();
		cstockInfo.setPid(commodityStockInfoEntity.getPid());
		cstockInfo.setState(Constants.COMMODITY_APPLY_GO);
		this.commodityStockInfoService.updateStockInfo(cstockInfo);
		
		return new Message<>(MessageType.MSG_SUCCESS,"pledge","操作成功");
	};

}
