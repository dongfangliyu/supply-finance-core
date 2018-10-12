package cn.fintecher.supply.finance.loan.manager.service.company.service;

import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CompanyChainFrom;

/**
 * 链属名单
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-11 13:34:43
 */
public interface CompanyChainService{

    /**
	 * 新增链属名单
	 * @param 
	 * @return
	 */
	Message insertChain(CompanyChainEntity companyChainEntity);
	
	/**
	 * 查询链属名单
	 * @param
	 * @return
	 */
	Message selectByChain(CompanyChainEntity companyChainEntity);
	
	/**
	 * 修改链属名单
	 * @param
	 */
	Message updateChain(CompanyChainEntity companyChainEntity);
	
	/**
	 * 根据主键查询链属名单
	 * @param
	 */
	Message queryChainByPid(String pid);
 
	
    Message searchListChain(CompanyChainFrom companyChainFrom);
	
    Message submitChain(CompanyChainEntity companyChainEntity,CompanyUserEntity user);
	
	
    Message submitUpdateChain(CompanyChainEntity companyChainEntity,CompanyUserEntity user);
	
    Message deleteChain(String id,String userName);

	Message searchDownLoadList(CompanyChainFrom companyChainFrom);

	Message searchList(String userName);

    Message inviteAgency(Long id, String userName);
}