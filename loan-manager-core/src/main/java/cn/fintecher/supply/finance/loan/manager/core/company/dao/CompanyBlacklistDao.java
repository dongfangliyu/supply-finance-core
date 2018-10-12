package cn.fintecher.supply.finance.loan.manager.core.company.dao;

import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyBlacklistEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 黑名单历史记录
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-20 11:42:47
 */
@Mapper
public interface CompanyBlacklistDao{
	
	Integer insertBlacklist(CompanyBlacklistEntity companyBlacklistEntity);
	
	List<CompanyBlacklistEntity> selectByBlacklist(CompanyBlacklistEntity companyBlacklistEntity);
	
	Integer updateBlacklist(CompanyBlacklistEntity companyBlacklistEntity);
	
	CompanyBlacklistEntity queryBlacklistByPid(String pid);

	List<CompanyBlacklistEntity> selectBlackList(BlackListFrom blackListFrom);

	Integer selectBlackListCount(BlackListFrom blackListFrom);
}
