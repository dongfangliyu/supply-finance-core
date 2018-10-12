package cn.fintecher.supply.finance.loan.manager.core.company.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CompanyChainFrom;

/**
 * 链属名单
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-11 13:34:43
 */
@Mapper
public interface CompanyChainDao{
	
	Integer insertChain(CompanyChainEntity companyChainEntity);
	
	List<CompanyChainEntity> selectByChain(CompanyChainEntity companyChainEntity);
	
	Integer updateChain(CompanyChainEntity companyChainEntity);
	
	CompanyChainEntity queryChainByPid(String pid);

    Integer queryPageCount(CompanyChainFrom companyChainFrom);

    List<CompanyChainEntity> queryPageList(CompanyChainFrom companyChainFrom);

    List<CompanyChainEntity> searchDownLoadList(CompanyChainFrom companyChainFrom);

    CompanyChainEntity getByCompanyName(String companyName);
}
