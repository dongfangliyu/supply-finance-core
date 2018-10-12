package cn.fintecher.supply.finance.loan.manager.core.company.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CompanyChainFrom;

import java.util.List;

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
	Integer insertChain(CompanyChainEntity companyChainEntity);
	
	/**
	 * 查询链属名单
	 * @param 
	 * @return
	 */
	List<CompanyChainEntity> selectByChain(CompanyChainEntity companyChainEntity);
	
	/**
	 * 修改链属名单
	 * @param 
	 */
	Integer updateChain(CompanyChainEntity companyChainEntity);
	
	/**
	 * 根据主键查询链属名单
	 * @param 
	 * @return
	 */
	CompanyChainEntity queryChainByPid(String pid);

    Integer queryPageCount(CompanyChainFrom companyChainFrom);

    List<CompanyChainEntity> queryPageList(CompanyChainFrom companyChainFrom);

    List<CompanyChainEntity> searchDownLoadList(CompanyChainFrom companyChainFrom);

	CompanyChainEntity getByCompanyName(String companyName);
}
