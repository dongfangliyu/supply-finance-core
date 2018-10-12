package cn.fintecher.supply.finance.loan.manager.service.company.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CompanyChainFrom;
import cn.fintecher.supply.finance.loan.manager.common.constant.ReturnMsg;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyEnterpriseEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.*;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessReceivableService;
import cn.fintecher.supply.finance.loan.manager.service.company.core.CompanyChainCore;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyEnterpriseCore;
import cn.fintecher.supply.finance.loan.manager.service.company.feign.FCCompanyUserCore;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyChainService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-11 13:34:43
 */
@Service("companyChainService")
public class CompanyChainServiceImpl implements CompanyChainService {

	@Autowired
    private CompanyChainCore companyChainCore;
	
	@Autowired
	private FCCompanyUserCore fccompanyUserCore;
	
	@Autowired
	private BusinessReceivableService businessReceivableService;
	
	 @Autowired
	 private FCCompanyEnterpriseCore fccompanyEnterpriseCore;

	 @Autowired
	 private FCCompanyUserCore fcCompanyUserCore;

    @Override
	public Message insertChain(CompanyChainEntity companyChainEntity){
		return companyChainCore.insertChain(companyChainEntity);
	}
	
	@Override
	public Message selectByChain(CompanyChainEntity companyChainEntity) {
		return companyChainCore.selectByChain(companyChainEntity);
	}
	
	@Override
	public Message updateChain(CompanyChainEntity companyChainEntity) {
		return companyChainCore.updateChain(companyChainEntity);
	}
    
	@Override
	public Message queryChainByPid(String pid) {
		return companyChainCore.queryChainByPid(pid);
	}
	
    public Message searchListChain(CompanyChainFrom companyChainFrom){
    	CompanyUserEntity user = getUserEntityByUserName(companyChainFrom.getUserName());
    	companyChainFrom.setOwerId(user.getEnterpriseId());
    	try{
			if (StringUtils.isNotBlank(companyChainFrom.getTimeStart())){
				companyChainFrom.setTimeStart(DateUtil.TransformatStartTime(companyChainFrom.getTimeStart()));
			}
			if (StringUtils.isNotBlank(companyChainFrom.getTimeEnd())){
				companyChainFrom.setTimeEnd(DateUtil.TransformatEndTime(companyChainFrom.getTimeEnd()));
			}
		}catch (Exception e){

		}
    	Message<PagedResponse<List<CompanyChainEntity>>> msg = new Message(MessageType.MSG_SUCCESS,"company",null);
		PagedResponse<List<CompanyChainEntity>> response = new PagedResponse<>();
		//查询总数
		Integer count = null;
		Message msgCount = companyChainCore.queryPageCount(companyChainFrom);
		if (MessageType.MSG_SUCCESS == msgCount.getCode()){
			count = Integer.parseInt(msgCount.getMessage().toString());
		}else {
			return new Message(MessageType.MSG_ERROR,"company",ReturnMsg.FAILED_CURRENCY);
		}

		//查询列表
		List<CompanyChainEntity> list = null;
		Message msgList = companyChainCore.queryPageList(companyChainFrom);
		if (MessageType.MSG_SUCCESS == msgList.getCode()){
			list = JSONUtil.toList(msgList.getMessage(),CompanyChainEntity.class);
		}else {
			return new Message(MessageType.MSG_ERROR,"company",ReturnMsg.FAILED_CURRENCY);
		}
		response.setData(list);
		response.setTotal(count);
		response.setPageNo(companyChainFrom.getPageNo());
		response.setPageSize(companyChainFrom.getPageSize());
		msg.setMessage(response);
    	return msg;
    };
	
    public Message submitChain(CompanyChainEntity companyChainEntity,CompanyUserEntity adminUser){
    	if (!companyChainEntity.getMobile().matches("1[3456789]\\d{9}")) {
    		return new Message<>(MessageType.MSG_ERROR,"company","手机号码格式不正确");
		};
		if (!companyChainEntity.getEmail().matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")) {
			return new Message<>(MessageType.MSG_ERROR,"company","邮箱格式不正确");
		};
    	if (entNameIsRepeat(companyChainEntity.getCompanyName(),new Long(adminUser.getEnterpriseId()))) {
			return new Message<>(MessageType.MSG_ERROR,"company","企业名称已存在");
		}
		if (!identicalNameAndMobile(companyChainEntity.getCompanyName(),companyChainEntity.getMobile())){
            return new Message<>(MessageType.MSG_ERROR,"company","公司法人手机号与企业名称不一致");
        }
		if (mobileIsEnterprise(companyChainEntity.getMobile())){
            return new Message<>(MessageType.MSG_ERROR,"company","该手机号已注册为核心企业");
        }
    	List<CompanyEnterpriseEntity> list = fccompanyEnterpriseCore.searchCompanyByNameAndEnterpriceType(companyChainEntity.getCompanyName(), "1");
        if (!ChkUtil.isEmpty(list)) {
        	companyChainEntity.setState(Constants.COMPANY_CHAIN_STATE_YES);
		}else{
			companyChainEntity.setState(Constants.COMPANY_CHAIN_STATE_NOT);
		}
    	CompanyUserEntity user = getUserEntityByUserName(companyChainEntity.getCreateBy());
    	companyChainEntity.setOwerId(user.getEnterpriseId());
    	companyChainEntity.setPid(null);
		companyChainEntity.setCreateAt(new Date());
		companyChainEntity.setUpdateAt(new Date());
		companyChainEntity.setCreateBy(user.getUserName());
        companyChainEntity.setUpdateBy(user.getUserName());
		companyChainEntity.setType(Constants.COMPANY_CHAIN_TYPE_SUP);
		
		companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
    	return this.insertChain(companyChainEntity);
    };
	
	
    public Message submitUpdateChain(CompanyChainEntity companyChainEntity,CompanyUserEntity adminUser){
    	if (!ChkUtil.isEmpty(companyChainEntity.getCompanyName())) {
    		if (!isModify(companyChainEntity.getPid())) {
    			return new Message(MessageType.MSG_ERROR,"company","此供应商有应付账款信息，不可修改");
    		}
		}
		if (!companyChainEntity.getMobile().matches("1[3456789]\\d{9}")) {
			return new Message<>(MessageType.MSG_ERROR,"company","手机号码格式不正确");
		};
		if (!companyChainEntity.getEmail().matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")) {
			return new Message<>(MessageType.MSG_ERROR,"company","邮箱格式不正确");
		};
		if (entNameIsUpdateRepeat(companyChainEntity.getCompanyName(),companyChainEntity.getPid(),new Long(adminUser.getEnterpriseId()))) {
			return new Message<>(MessageType.MSG_ERROR,"company","企业名称已存在");
		}
		if (!identicalUpdateNameAndMobile(companyChainEntity.getCompanyName(),companyChainEntity.getPid(),companyChainEntity.getMobile())){
			return new Message<>(MessageType.MSG_ERROR,"company","公司法人手机号与企业名称不一致");
		}
		if (mobileIsEnterprise(companyChainEntity.getMobile())){
			return new Message<>(MessageType.MSG_ERROR,"company","该手机号已注册为核心企业");
		}
    	
    	
    	Message msgEntity = this.queryChainByPid(companyChainEntity.getPid()+"");
    	if (ChkUtil.isEmpty(companyChainEntity.getPid())||ChkUtil.isEmpty(msgEntity.getMessage())) {
			return new Message<>(MessageType.MSG_ERROR,"company",ReturnMsg.FAILED_CURRENCY);
		}
    	companyChainEntity.setUpdateAt(new Date());
    	companyChainEntity.setCreateAt(null);
    	companyChainEntity.setCreateBy(null);
		companyChainEntity.setType(null);
		companyChainEntity.setState(null);
		companyChainEntity.setStatus(null);
    	return this.updateChain(companyChainEntity);
    };
	

	public Message deleteChain(String id,String userName){
    	if (!isModify(new Long(id))) {
			return new Message(MessageType.MSG_ERROR,"company","删除失败，此供应商有应付账款信息");
		}
    	CompanyChainEntity companyChainEntity = new CompanyChainEntity();
    	try {
    		companyChainEntity.setPid(new Long(id));
    		companyChainEntity.setUpdateAt(new Date());
    		companyChainEntity.setUpdateBy(userName);
    		companyChainEntity.setStatus(Constants.DATA_STATUS_DEL);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"company",ReturnMsg.FAILED_CURRENCY);
		}
    	
    	return this.updateChain(companyChainEntity);
    };

    private CompanyUserEntity getUserEntityByUserName(String userName){
    	CompanyUserEntity user = fccompanyUserCore.findCompanyUserByName(userName);
    	if (ChkUtil.isEmpty(user.getEnterpriseId())) {
    		user.setEnterpriseId(1L);
		}
    	return user;
    }
    /**
     * 根据名称查询是否已添加链属名单
     * @param name
     * @param pid
     * @return true 已添加 false 未添加
     */
    private Boolean entNameIsRepeat(String name,Long pid){
    	CompanyChainEntity companyChainEntity = new CompanyChainEntity();
    	companyChainEntity.setCompanyName(name);
        companyChainEntity.setOwerId(pid);
    	companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
    	Message msg = companyChainCore.selectByChain(companyChainEntity);
    	if (msg.getMessage() == null) {
			return false;
		}else {
			List<CompanyChainEntity> list = JSONUtil.toList(msg.getMessage(), CompanyChainEntity.class);
			if (!ChkUtil.isEmpty(list)) {
				return true;
			}
			return false;
		}
    }
    /**
     * 根据名称和id查询包含链属名单，修改时使用
     * @param name
     * @param pid
     * @return true 已添加 false 未添加
     */
    private Boolean entNameIsUpdateRepeat(String name,long pid,Long owerId){
    	CompanyChainEntity companyChainEntity = new CompanyChainEntity();
    	companyChainEntity.setCompanyName(name);
    	companyChainEntity.setOwerId(owerId);
    	companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
    	Message msg = companyChainCore.selectByChain(companyChainEntity);
    	if (msg.getMessage() == null) {
    		return false;
    	}else {
    		List<CompanyChainEntity> list = JSONUtil.toList(msg.getMessage(), CompanyChainEntity.class);
    		if (!ChkUtil.isEmpty(list)) {
    			
    			if (list.size() == 0) {
    				return false;
				}
    			
    			if (list.size()>1) {
    				return true;
				}
    			
    			if (list.size() == 1) {
					long returnId = list.get(0).getPid();
					if (returnId == pid) {
						return false;
					}
					return true;
				}
    			
    		}
    		return false;
    	}
    }

	@Override
	public Message searchDownLoadList(CompanyChainFrom companyChainFrom) {
		// TODO Auto-generated method stub
		try {
			CompanyUserEntity user = getUserEntityByUserName(companyChainFrom.getUserName());
			long OwerId = companyChainFrom.getOwerId();
			long EnterpriseId = user.getEnterpriseId();

			if (OwerId != EnterpriseId) {
				//用户名与企业id不一致，不允许下载
				return new Message();
			}
			companyChainFrom.setOwerId(user.getEnterpriseId());
			return companyChainCore.searchDownLoadList(companyChainFrom);
		}catch (Exception e){
			return new Message();
		}
	}
	/**
	 * 数据是否可修改
	 * @param pid 文件id
	 * @return true 可修改 ，false 不可修改
	 */
	private Boolean isModify(Long pid){
		if (pid!=null) {
			BusinessReceivableEntity businessReceivableEntity = new BusinessReceivableEntity();
			businessReceivableEntity.setStatus(Constants.DATA_STATUS_NOL);
			businessReceivableEntity.setSupplierId(pid);
    		Message resultMsg = businessReceivableService.selectByReceivable(businessReceivableEntity);
        	List<BusinessReceivableEntity> list = JSONUtil.toList(resultMsg.getMessage(), BusinessReceivableEntity.class);
        	if (list.size()>0&&list.get(0)!=null) {
        		return false;
    		}
		}
    	return true;
	}

	@Override
	public Message searchList(String userName) {
		// TODO Auto-generated method stub
		CompanyUserEntity user = getUserEntityByUserName(userName);
		CompanyChainEntity companyChainEntity = new CompanyChainEntity();
		companyChainEntity.setOwerId(user.getEnterpriseId());
		companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
		return this.selectByChain(companyChainEntity);
	}

	@Override
	public Message inviteAgency(Long id, String userName) {
		CompanyChainEntity cmpanyChainEntity = new CompanyChainEntity();
		cmpanyChainEntity.setPid(id);
		cmpanyChainEntity.setApplyState("1");
		cmpanyChainEntity.setUpdateAt(new Date());
		cmpanyChainEntity.setUpdateBy(userName);
		return companyChainCore.updateChain(cmpanyChainEntity);
	}

	/**
     * 校验公司名称及手机号在其他核心企业上传的供应商中是否一致
     * @param name 添加公司名称
     * @param mobile 手机号
     * @return true 一致 false 不一致
     */
	private boolean identicalNameAndMobile(String  name, String mobile){
		CompanyChainEntity chain = new CompanyChainEntity();
        chain.setMobile(mobile);
        chain.setStatus(Constants.DATA_STATUS_NOL);
        Message msg = this.selectByChain(chain);
        List<CompanyChainEntity> list = JSONUtil.toList(msg.getMessage(),CompanyChainEntity.class);
        if (!(null == list || 0 == list.size() || null == list.get(0))){
            if (!name.equals(list.get(0).getCompanyName())){
                return false;
            }
        }

        chain.setMobile(null);
        chain.setCompanyName(name);
        Message msg1 = this.selectByChain(chain);
        List<CompanyChainEntity> list1 = JSONUtil.toList(msg1.getMessage(),CompanyChainEntity.class);
        if (!(null == list1 || 0 == list1.size() || null == list1.get(0))){
            if (!mobile.equals(list1.get(0).getMobile())){
                return false;
            }
        }
		return true;
	}
	/**
	 * 校验公司名称及手机号在其他核心企业上传的供应商中是否一致
	 * @param name 添加公司名称
	 * @param mobile 手机号
	 * @return true 一致 false 不一致
	 */
	private boolean identicalUpdateNameAndMobile(String  name,long pid, String mobile){
		CompanyChainEntity chain = new CompanyChainEntity();
		chain.setMobile(mobile);
		chain.setStatus(Constants.DATA_STATUS_NOL);
		Message msg = this.selectByChain(chain);
		List<CompanyChainEntity> list = JSONUtil.toList(msg.getMessage(),CompanyChainEntity.class);
		if (!(null == list || 0 == list.size() || null == list.get(0))){
			long returnId = list.get(0).getPid();
			if (!name.equals(list.get(0).getCompanyName()) && returnId != pid){
				return false;
			}
		}
		
		chain.setMobile(null);
		chain.setCompanyName(name);
		Message msg1 = this.selectByChain(chain);
		List<CompanyChainEntity> list1 = JSONUtil.toList(msg1.getMessage(),CompanyChainEntity.class);
		if (!(null == list1 || 0 == list1.size() || null == list1.get(0))){
			long returnId = list1.get(0).getPid();
			if (!mobile.equals(list1.get(0).getMobile()) && returnId != pid){
				return false;
			}
		}
		return true;
	}

    /**
     * 判断手机号是否为核心企业已注册的手机号
     * @param mobile 手机号
     * @return true 是 false 否
     */
    private boolean mobileIsEnterprise(String mobile){
        List<CompanyUserEntity> msgList = fcCompanyUserCore.findCompanyUserByNameAndEnterpriseType(mobile, "0");
        if (null == msgList || 0 == msgList.size() || null == msgList.get(0)) {
            return false;
        }
       return true;
    }


}

