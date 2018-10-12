package cn.fintecher.supply.finance.loan.manager.core.company.service.impl;

import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.core.company.dao.CompanyBlacklistDao;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyBlacklistEntity;
import cn.fintecher.supply.finance.loan.manager.core.company.service.CompanyBlacklistService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-20 11:42:47
 */
@Service("companyBlacklistService")
public class CompanyBlacklistServiceImpl implements CompanyBlacklistService {

   	@Autowired
	private CompanyBlacklistDao companyblacklistDao;
	
	@Override
	public Integer insertBlacklist(CompanyBlacklistEntity companyBlacklistEntity){
		return companyblacklistDao.insertBlacklist(companyBlacklistEntity);
	}
	
	@Override
	public List<CompanyBlacklistEntity> selectByBlacklist(CompanyBlacklistEntity companyBlacklistEntity) {
		return companyblacklistDao.selectByBlacklist(companyBlacklistEntity);
	}
	
	@Override
	public Integer updateBlacklist(CompanyBlacklistEntity companyBlacklistEntity) {
		// TODO Auto-generated method stub
		return companyblacklistDao.updateBlacklist(companyBlacklistEntity);
	}
	
	@Override
	public CompanyBlacklistEntity queryBlacklistByPid(String pid) {
		// TODO Auto-generated method stub
		return companyblacklistDao.queryBlacklistByPid(pid);
	}

	@Override
	public List<CompanyBlacklistEntity> selectBlackList(BlackListFrom blackListFrom) {
		return companyblacklistDao.selectBlackList(blackListFrom);
	}

	@Override
	public Integer selectBlackListCount(BlackListFrom blackListFrom) {
		return companyblacklistDao.selectBlackListCount(blackListFrom);
	}

}
