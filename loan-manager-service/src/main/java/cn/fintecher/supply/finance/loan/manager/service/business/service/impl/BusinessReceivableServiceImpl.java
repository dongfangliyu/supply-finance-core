package cn.fintecher.supply.finance.loan.manager.service.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableFrom;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableSubmit;
import cn.fintecher.supply.finance.loan.manager.common.business.response.BusinessReceivableResponse;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.constant.ReturnMsg;
import cn.fintecher.supply.finance.loan.manager.common.form.ProProductForm;
import cn.fintecher.supply.finance.loan.manager.common.model.AuditCreditInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.ProProductEntity;
import cn.fintecher.supply.finance.loan.manager.service.audit.feign.FCAuditCompanyCore;
import cn.fintecher.supply.finance.loan.manager.service.base.feign.FCBaseDictionaryCore;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessReceivableCore;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessFileService;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessOrderService;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessReceivableService;
import cn.fintecher.supply.finance.loan.manager.service.common.redis.IRedisService;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyEnterpriseCore;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyUserCore;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyChainService;
import cn.fintecher.supply.finance.loan.manager.service.pro.feign.FCProProductCore;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
@Service("businessReceivableService")
public class BusinessReceivableServiceImpl implements BusinessReceivableService {

	@Autowired
    private BusinessReceivableCore businessReceivableCore;
	
	@Autowired
	private FCCompanyUserCore fccompanyUserCore;
	
	@Autowired
	private FCCompanyEnterpriseCore fcCompanyEnterpriseCore;
	
	@Autowired
	private CompanyChainService companyChainService;
	
	@Autowired
	private BusinessFileService businessFileService;
	
	@Autowired
	private BusinessOrderService businessOrderService;
	
	@Autowired
    private IRedisService redisService;
	
	@Autowired
    private FCProProductCore fcProProductCore;
	
	@Autowired
	private FCBaseDictionaryCore fcBaseDictionaryCore;
	
	@Autowired
	private FCAuditCompanyCore fcAuditCompanyCore;
	
	 @Autowired
	 private FCCompanyEnterpriseCore fccompanyEnterpriseCore;
	
    @Override
	public Message insertReceivable(BusinessReceivableEntity businessReceivableEntity){
		return businessReceivableCore.insertReceivable(businessReceivableEntity);
	}
	
	@Override
	public Message selectByReceivable(BusinessReceivableEntity businessReceivableEntity) {
		return businessReceivableCore.selectByReceivable(businessReceivableEntity);
	}
	
	@Override
	public Message updateReceivable(BusinessReceivableEntity businessReceivableEntity) {
		return businessReceivableCore.updateReceivable(businessReceivableEntity);
	}
    
	@Override
	public Message queryReceivableByPid(String pid) {
		return businessReceivableCore.queryReceivableByPid(pid);
	}
	
    public Message searchListReceivable(BusinessReceivableFrom businessReceivableFrom){
    	CompanyUserEntity user = getUserEntityByUserName(businessReceivableFrom.getUserName());
    	businessReceivableFrom.setEnterpriseId(user.getEnterpriseId());
		try{
			if (StringUtils.isNotBlank(businessReceivableFrom.getAccountStartTime())){
				businessReceivableFrom.setAccountStartTime(DateUtil.TransformatStartTime(businessReceivableFrom.getAccountStartTime()));
			}
			if (StringUtils.isNotBlank(businessReceivableFrom.getAccountEndTime())){
				businessReceivableFrom.setAccountEndTime(DateUtil.TransformatEndTime(businessReceivableFrom.getAccountEndTime()));
			}
		}catch (Exception e){

		}
    	Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
		PagedResponse response = new PagedResponse();
		//查询总数
		Integer count = null;
		Message msgCount = businessReceivableCore.queryPageCount(businessReceivableFrom);
		if (MessageType.MSG_SUCCESS == msgCount.getCode()){
			count = Integer.parseInt(msgCount.getMessage().toString());
		}else {
			return new Message(MessageType.MSG_ERROR,"business",ReturnMsg.FAILED_CURRENCY);
		}

		//查询列表
		List<BusinessReceivableEntity> list = null;
		Message msgList = businessReceivableCore.queryPageList(businessReceivableFrom);
		if (MessageType.MSG_SUCCESS == msgList.getCode()){
			list = JSONUtil.toList(msgList.getMessage(),BusinessReceivableEntity.class);
		}else {
			return new Message(MessageType.MSG_ERROR,"business",ReturnMsg.FAILED_CURRENCY);
		}
		response.setData(list);
		response.setTotal(count);
		response.setPageNo(businessReceivableFrom.getPageNo());
		response.setPageSize(businessReceivableFrom.getPageSize());
		msg.setMessage(response);
    	return msg;
    };
	
    public Message submitReceivable(BusinessReceivableSubmit businessReceivableSubmit){
    	/*CompanyEnterpriseEntity supplier = fcCompanyEnterpriseCore.searchCompanyEnterpriseEntity(businessReceivableEntity.getSupplierId());
    	if (!businessReceivableEntity.getSupplierName().equals(supplier.getName())) {
			return new Message(MessageType.MSG_ERROR,"business",ReturnMsg.FAILED_CURRENCY);
		}*/
    	Integer num = AllowPreservation(businessReceivableSubmit.getOrderCode());
    	if (num == -1) {
    		return new Message(MessageType.MSG_ERROR,"business","请上传凭证后重试");
		}
    	if (num == 2) {
    		return new Message(MessageType.MSG_ERROR,"business","请上传商务合同后重试");
    	}
    	if (num != 1) {
			return new Message(MessageType.MSG_ERROR,"business","请添加该供应商后操作");
			
		}
    	CompanyUserEntity user = getUserEntityByUserName(businessReceivableSubmit.getUserName());
    	CompanyChainEntity companyChainEntity = new CompanyChainEntity();
    	companyChainEntity.setOwerId(user.getEnterpriseId());
    	companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
    	int flag = inspectNameAndPid(user.getEnterpriseId(), businessReceivableSubmit.getSupplierId(),businessReceivableSubmit.getSupplierName(),businessReceivableSubmit.getId()+"");
    	if (flag == 2) {
    		return new Message(MessageType.MSG_ERROR,"business","供应商名称有误");
		}
    	if (flag == 3) {
    		return new Message(MessageType.MSG_ERROR,"business","已邀请不可修改");
    	}
    	if (flag != 1) {
			return new Message(MessageType.MSG_ERROR,"business",ReturnMsg.FAILED_CURRENCY);
			
		}
    	BusinessReceivableEntity businessReceivableEntity = new BusinessReceivableEntity();
    	ProProductEntity proProductEntity;
		try {
			AuditCreditInfoEntity creditInfo = fcAuditCompanyCore.searchEntityByEnterpriseId(new Long(user.getEnterpriseId()));
			ProProductForm proProductForm = new ProProductForm();
			proProductForm.setCategory(creditInfo.getProductType());
			List<ProProductEntity> list = fcProProductCore.searchProductList(proProductForm);
			if (ChkUtil.isEmpty(list) || list.size() == 0 ) {
				return new Message(MessageType.MSG_ERROR,"business","产品异常，请联系管理员!");
			}
			proProductEntity = list.get(0);
			businessReceivableEntity.setServiceFee(proProductEntity.getServiceFee().intValue());
	    	businessReceivableEntity.setInterestRate(proProductEntity.getInterestRate().intValue());
	    	businessReceivableEntity.setRebateRatio(proProductEntity.getRebateRatio().intValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"business",ReturnMsg.FAILED_CURRENCY);
		}
    	
		BaseDictionaryEntity msgDictionary = fcBaseDictionaryCore.getEntityByPid(Long.parseLong(businessReceivableSubmit.getCertificateCategory()));
    	
		businessReceivableEntity.setCertificateCategoryValue(msgDictionary.getValue());
    	businessReceivableEntity.setAccountEndTime(businessReceivableSubmit.getAccountEndTime());
		businessReceivableEntity.setAccountStartTime(businessReceivableSubmit.getAccountStartTime());
		businessReceivableEntity.setSupplierId(businessReceivableSubmit.getSupplierId());
		businessReceivableEntity.setSupplierName(businessReceivableSubmit.getSupplierName());
		businessReceivableEntity.setContractNo(businessReceivableSubmit.getContractNo());
		businessReceivableEntity.setCertificateAmount(businessReceivableSubmit.getCertificateAmount());
		businessReceivableEntity.setCertificateCategory(businessReceivableSubmit.getCertificateCategory());
		businessReceivableEntity.setOrderCode(businessReceivableSubmit.getOrderCode());
		businessReceivableEntity.setUpdateAt(new Date());
		businessReceivableEntity.setUpdateBy(businessReceivableSubmit.getUserName());
    	businessReceivableEntity.setCreateBy(businessReceivableSubmit.getUserName());
    	businessReceivableEntity.setCreateAt(new Date());
    	businessReceivableEntity.setPid(null);
    	businessReceivableEntity.setInviteTime(null);
    	CompanyEnterpriseEntity company = fcCompanyEnterpriseCore.searchCompanyEnterpriseEntity(user.getEnterpriseId());
    	businessReceivableEntity.setEnterpriseId(company.getPid());
    	businessReceivableEntity.setEnterpriseName(company.getName());
    	businessReceivableEntity.setStatus(Constants.DATA_STATUS_NOL);
    	businessReceivableEntity.setState("0");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	String accountNoDate = sdf.format(new Date());
    	String accountNumber=redisService.get(accountNoDate+"_accountNo");
    	String accountNo = "AP"+accountNoDate;
    	Integer number = 0;
    	if (!ChkUtil.isEmpty(accountNumber)) {
			 number = Integer.parseInt(accountNumber)+1;
		}else {
			String newAccountNo = businessReceivableCore.queryNewAccountNo();
			if (newAccountNo==null) {
				number = 1;
			}else {
				number = Integer.parseInt(newAccountNo.substring(newAccountNo.length()-1, newAccountNo.length()))+1;
			}
			
		}
    	redisService.set(accountNoDate+"_accountNo",number);
    	String numberStr = number+"";
    	switch (numberStr.length()) {
		case 1:
			numberStr = "000"+numberStr;
			break;
		case 2:
			numberStr = "00"+numberStr;
			break;
		case 3:
			numberStr = "0"+numberStr;
			break;
		default:
			break;
		}
    	businessReceivableEntity.setAccountNo(accountNo+numberStr);
    	Message InsterReceivablemMsg = this.insertReceivable(businessReceivableEntity);
    	if (businessReceivableSubmit.getType().equals("1")) {
    		businessReceivableEntity.setPid(new Long(InsterReceivablemMsg.getMessage().toString()));
			this.inviteSupplier(businessReceivableEntity, businessReceivableSubmit.getUserName());
		}
    	return new Message(MessageType.MSG_SUCCESS,"business","操作成功");
    };
	
    public Message inviteSupplier(Long receivableId,String userName){
    	
    	Message msg = this.queryReceivableByPid(receivableId+"");
    	BusinessReceivableEntity receivableEntity = JSONUtil.toBean(msg.getMessage(), BusinessReceivableEntity.class);
    	Long pid = IsRegister(receivableEntity.getSupplierName());
    	if (pid>-1) {
    		BusinessOrderEntity orderSelect = new BusinessOrderEntity();
     		orderSelect.setAccountNo(receivableEntity.getAccountNo());
     		orderSelect.setStatus(Constants.DATA_STATUS_NOL);
     		Message<List<BusinessOrderEntity>> orderMsg = businessOrderService.selectByOrder(orderSelect);
     		if (orderMsg.getCode() == MessageType.MSG_ERROR || ChkUtil.isEmpty(orderMsg.getMessage())) {
     			BusinessOrderEntity order = TransformationReceivakeToOrder(receivableEntity,pid);
         		order.setInviteTime(new Date());
         		businessOrderService.insertOrder(order);
			}
    	}
    	BusinessReceivableEntity businessReceivableEntity = new BusinessReceivableEntity();
    	businessReceivableEntity.setPid(receivableId);
    	businessReceivableEntity.setState("1");
    	businessReceivableEntity.setInviteTime(new Date());
    	businessReceivableEntity.setUpdateAt(new Date());
    	businessReceivableEntity.setUpdateBy(userName);
    	return this.updateReceivable(businessReceivableEntity);
    };
    
    public Message inviteSupplier(BusinessReceivableEntity receivableEntity,String userName){
    	BusinessReceivableEntity businessReceivableEntity = new BusinessReceivableEntity();
    	businessReceivableEntity.setPid(receivableEntity.getPid());
    	businessReceivableEntity.setState("1");
    	Date date = new Date();
    	businessReceivableEntity.setInviteTime(date);
    	businessReceivableEntity.setUpdateAt(date);
    	businessReceivableEntity.setUpdateBy(userName);
    	this.updateReceivable(businessReceivableEntity);
    	Long pid = IsRegister(receivableEntity.getSupplierName());
     	if (pid>-1) {
     		BusinessOrderEntity orderSelect = new BusinessOrderEntity();
     		orderSelect.setAccountNo(receivableEntity.getAccountNo());
     		orderSelect.setStatus(Constants.DATA_STATUS_NOL);
     		Message<List<BusinessOrderEntity>> orderMsg = businessOrderService.selectByOrder(orderSelect);
     		if (orderMsg.getCode() == MessageType.MSG_ERROR || ChkUtil.isEmpty(orderMsg.getMessage())) {
     			BusinessOrderEntity order = TransformationReceivakeToOrder(receivableEntity,pid);
         		order.setInviteTime(date);
         		businessOrderService.insertOrder(order);
			}
     		
     	}
     	
    	 return new Message<>();
    };
	
    public Message selectReceivableDetail(Long receivableId,String userName){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
    	try {
			BusinessReceivableResponse response = new BusinessReceivableResponse();
			Message msgEntity = this.queryReceivableByPid(receivableId+"");
			BusinessReceivableEntity businessReceivableEntity = JSONUtil.toBean(msgEntity.getMessage(), BusinessReceivableEntity.class);
			response.setBusinessReceivable(businessReceivableEntity);
			String Code = businessReceivableEntity.getOrderCode();
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
	
    public Message submitUpdateReceivable(BusinessReceivableSubmit businessReceivableSubmit){
    	Integer num = AllowPreservation(businessReceivableSubmit.getOrderCode());
    	if (num == -1) {
    		return new Message(MessageType.MSG_ERROR,"business","请上传凭证后重试");
		}
    	if (num == 2) {
    		return new Message(MessageType.MSG_ERROR,"business","请上传商务合同后重试");
    	}
    	if (num != 1) {
			return new Message(MessageType.MSG_ERROR,"business",ReturnMsg.FAILED_CURRENCY);
			
		}
    	CompanyUserEntity user = getUserEntityByUserName(businessReceivableSubmit.getUserName());
    	int flag = inspectNameAndPid(user.getEnterpriseId(), businessReceivableSubmit.getSupplierId(),businessReceivableSubmit.getSupplierName(),businessReceivableSubmit.getId()+"");
    	if (flag == 2) {
    		return new Message(MessageType.MSG_ERROR,"business","供应商名称有误");
		}
    	if (flag == 3) {
    		return new Message(MessageType.MSG_ERROR,"business","已邀请不可修改");
    	}
    	if (flag != 1) {
			return new Message(MessageType.MSG_ERROR,"business","请添加该供应商后操作");
		}
    	Message msg = new Message<>();
		try {
			if (ChkUtil.isEmpty(businessReceivableSubmit.getId())) {
				new Message<>(MessageType.MSG_ERROR,"business",ReturnMsg.FAILED_CURRENCY);
			}
			BusinessReceivableEntity businessReceivableEntity = new BusinessReceivableEntity();
			BaseDictionaryEntity msgDictionary = fcBaseDictionaryCore.getEntityByPid(Long.parseLong(businessReceivableSubmit.getCertificateCategory()));
	    	
			businessReceivableEntity.setCertificateCategoryValue(msgDictionary.getValue());
			businessReceivableEntity.setPid(businessReceivableSubmit.getId());
			businessReceivableEntity.setAccountEndTime(businessReceivableSubmit.getAccountEndTime());
			businessReceivableEntity.setAccountStartTime(businessReceivableSubmit.getAccountStartTime());
			businessReceivableEntity.setSupplierId(businessReceivableSubmit.getSupplierId());
			businessReceivableEntity.setSupplierName(businessReceivableSubmit.getSupplierName());
			businessReceivableEntity.setContractNo(businessReceivableSubmit.getContractNo());
			businessReceivableEntity.setCertificateAmount(businessReceivableSubmit.getCertificateAmount());
			businessReceivableEntity.setCertificateCategory(businessReceivableSubmit.getCertificateCategory());
			businessReceivableEntity.setOrderCode(businessReceivableSubmit.getOrderCode());
			businessReceivableEntity.setUpdateAt(new Date());
			businessReceivableEntity.setUpdateBy(businessReceivableSubmit.getUserName());
			businessReceivableEntity.setStatus(null);
			businessReceivableEntity.setState(null);
			businessReceivableEntity.setCreateAt(null);
			businessReceivableEntity.setCreateBy(null);
			businessReceivableEntity.setInviteTime(null);
			businessReceivableEntity.setRemark(null);
			msg = this.updateReceivable(businessReceivableEntity);
			if (businessReceivableSubmit.getType().equals("1")) {
				this.inviteSupplier(businessReceivableEntity.getPid(), businessReceivableSubmit.getUserName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Message<>(MessageType.MSG_ERROR,"business",ReturnMsg.FAILED_CURRENCY);
		}
    	return msg;
    };
    
    private CompanyUserEntity getUserEntityByUserName(String userName){
    	CompanyUserEntity user = fccompanyUserCore.findCompanyUserByName(userName);
    	if (ChkUtil.isEmpty(user.getEnterpriseId())) {
    		user.setEnterpriseId(1L);
		}
    	return user;
    }

	@Override
	public Message deleteReceivable(String id, String userName) {
		// TODO Auto-generated method stub
		Integer flag = inspectNameAndPid(null, null, null, id);
		if (flag == 3) {
    		return new Message(MessageType.MSG_ERROR,"business","删除失败，不可删除");
    	}
		BusinessReceivableEntity businessReceivableEntity = new BusinessReceivableEntity();
		businessReceivableEntity.setPid(new Long(id));
		businessReceivableEntity.setUpdateAt(new Date());
		businessReceivableEntity.setUpdateBy(userName);
		
		businessReceivableEntity.setStatus(Constants.DATA_STATUS_DEL);
		
		return this.updateReceivable(businessReceivableEntity);
	}

	/**
	 * 校验供应商id名称是否对应，及是否为已邀请状态
	 * @param owerId 拥有者id
	 * @param supplierId 供应商id
	 * @param supplierName 供应商名称
	 * @param receivableId 应付账款id
	 * @return -1 无供应商  1 正常 2 供应商id和名字不匹配 3已邀请状态不允许修改
	 */
	private Integer inspectNameAndPid(Long owerId, Long supplierId,String supplierName,String receivableId){
		Integer flag =-1;
		if (!ChkUtil.isEmpty(owerId) && !ChkUtil.isEmpty(supplierId) &&!ChkUtil.isEmpty(supplierName) ) {
			CompanyChainEntity companyChainEntity = new CompanyChainEntity();
			companyChainEntity.setOwerId(owerId);
	    	companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
	    	Message msg = companyChainService.selectByChain(companyChainEntity);
	    	List<CompanyChainEntity> list = JSONUtil.toList(msg.getMessage(),CompanyChainEntity.class);
	    	for (CompanyChainEntity company : list) {
	    		if ((long)company.getPid() == (long)supplierId) {
	    			flag = 1;
	    			if (!supplierName.equals(company.getCompanyName())) {
						flag = 2;
					}
	    			break;
				}
			}
		}
		
    	if (!ChkUtil.isEmpty(receivableId)) {
    		Message resultMsg = this.queryReceivableByPid(receivableId);
        	BusinessReceivableEntity resultReceivableEntity = JSONUtil.toBean(resultMsg.getMessage(), BusinessReceivableEntity.class);
        	if (resultReceivableEntity.getState().equals("1")) {
        		flag = 3;
    		}
		}
    	return flag;
		
	}
	
	private Integer AllowPreservation(String code){
		Integer flag = -1;
		BusinessFileEntity businessFileEntity = new BusinessFileEntity();
		businessFileEntity.setOwnerId(code);
		businessFileEntity.setStatus(Constants.DATA_STATUS_NOL);
		Message msg =businessFileService.selectByFile(businessFileEntity);
		List<BusinessFileEntity> list = JSONUtil.toList(msg.getMessage(), BusinessFileEntity.class);
		for (BusinessFileEntity businessFileEntity2 : list) {
			if("voucher".equals(businessFileEntity2.getCategory())){
				flag = 2;
				break;
			}
		}
		if (flag == 2) {
			for (BusinessFileEntity businessFileEntity2 : list) {
				if("contract".equals(businessFileEntity2.getCategory())){
					flag = 1;
					break;
				}
			}
		}
		
		
		return flag;
	}
	
	/**
	 * 供应商是否已注册 ,若注册返回pid 未注册返回-1;
	 */
	private Long IsRegister(String suppName){
		List<CompanyEnterpriseEntity> list = fccompanyEnterpriseCore.searchCompanyByNameAndEnterpriceType(suppName, "1");
        if (!ChkUtil.isEmpty(list)) {
        	return list.get(0).getPid();
		}
		return -1L;
	}
	
	private BusinessOrderEntity TransformationReceivakeToOrder(BusinessReceivableEntity receivableEntity,Long pid){
		BusinessOrderEntity orderEntity = new BusinessOrderEntity();
        orderEntity.setEnterpriseId(receivableEntity.getEnterpriseId());
        orderEntity.setEnterCategoryValue(receivableEntity.getCertificateCategoryValue());
        orderEntity.setEnterpriseName(receivableEntity.getEnterpriseName());
        orderEntity.setContractNo(receivableEntity.getContractNo());
        orderEntity.setCertificateAmount(receivableEntity.getCertificateAmount());
        orderEntity.setSupplierId(pid);
        orderEntity.setSupplierName(receivableEntity.getSupplierName());
        orderEntity.setAccountNo(receivableEntity.getAccountNo());
        orderEntity.setAccountStartTime(receivableEntity.getAccountStartTime());
        orderEntity.setAccountEndTime(receivableEntity.getAccountEndTime());
        orderEntity.setServiceFee(receivableEntity.getServiceFee());
        orderEntity.setInterestRate(receivableEntity.getInterestRate());
        orderEntity.setRebateRatio(receivableEntity.getRebateRatio());
        orderEntity.setEnterCategory(receivableEntity.getCertificateCategory());
        orderEntity.setEnterCategoryValue(receivableEntity.getCertificateCategoryValue());
        orderEntity.setState("0");
		orderEntity.setFinancingStatus("0");
        orderEntity.setInviteTime(receivableEntity.getInviteTime());
        orderEntity.setOrderCode(receivableEntity.getOrderCode());
        orderEntity.setCreateAt(new Date());
        orderEntity.setStatus(Constants.DATA_STATUS_NOL);
        return orderEntity;
	} 
}

