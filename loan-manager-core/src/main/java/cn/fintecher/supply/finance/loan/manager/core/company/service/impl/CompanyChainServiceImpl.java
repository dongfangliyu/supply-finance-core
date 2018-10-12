package cn.fintecher.supply.finance.loan.manager.core.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CompanyChainFrom;
import cn.fintecher.supply.finance.loan.manager.core.company.dao.CompanyChainDao;
import cn.fintecher.supply.finance.loan.manager.core.company.service.CompanyChainService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-11 13:34:43
 */
@Service("companyChainService")
public class CompanyChainServiceImpl implements CompanyChainService {

   	@Autowired
	private CompanyChainDao companychainDao;
	
	@Override
	public Integer insertChain(CompanyChainEntity companyChainEntity){
		return companychainDao.insertChain(companyChainEntity);
	}
	
	@Override
	public List<CompanyChainEntity> selectByChain(CompanyChainEntity companyChainEntity) {
		return companychainDao.selectByChain(companyChainEntity);
	}
	
	@Override
	public Integer updateChain(CompanyChainEntity companyChainEntity) {
		// TODO Auto-generated method stub
		return companychainDao.updateChain(companyChainEntity);
	}
	
	@Override
	public CompanyChainEntity queryChainByPid(String pid) {
		// TODO Auto-generated method stub
		return companychainDao.queryChainByPid(pid);
	}

	@Override
	public Integer queryPageCount(CompanyChainFrom companyChainFrom) {
		return companychainDao.queryPageCount(companyChainFrom);
	}

	@Override
	public List<CompanyChainEntity> queryPageList(CompanyChainFrom companyChainFrom) {
		return companychainDao.queryPageList(companyChainFrom);
	}

	@Override
	public List<CompanyChainEntity> searchDownLoadList(CompanyChainFrom companyChainFrom) {
		// TODO Auto-generated method stub
		return companychainDao.searchDownLoadList(companyChainFrom);
	}

	@Override
	public CompanyChainEntity getByCompanyName(String companyName) {
		return companychainDao.getByCompanyName(companyName);
	}
}
