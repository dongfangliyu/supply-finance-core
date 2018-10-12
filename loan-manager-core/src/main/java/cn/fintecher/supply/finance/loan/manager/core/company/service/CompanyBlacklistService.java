package cn.fintecher.supply.finance.loan.manager.core.company.service;

import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyBlacklistEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;

import java.util.List;

/**
 * 黑名单历史记录
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-20 11:42:47
 */
public interface CompanyBlacklistService{

    /**
	 * 新增黑名单历史记录
	 * @param 
	 * @return
	 */
	Integer insertBlacklist(CompanyBlacklistEntity companyBlacklistEntity);
	
	/**
	 * 查询黑名单历史记录
	 * @param 
	 * @return
	 */
	List<CompanyBlacklistEntity> selectByBlacklist(CompanyBlacklistEntity companyBlacklistEntity);
	
	/**
	 * 修改黑名单历史记录
	 * @param 
	 */
	Integer updateBlacklist(CompanyBlacklistEntity companyBlacklistEntity);
	
	/**
	 * 根据主键查询黑名单历史记录
	 * @param 
	 * @return
	 */
	CompanyBlacklistEntity queryBlacklistByPid(String pid);


	List<CompanyBlacklistEntity> selectBlackList(BlackListFrom blackListFrom);

	Integer selectBlackListCount(BlackListFrom blackListFrom);

}
