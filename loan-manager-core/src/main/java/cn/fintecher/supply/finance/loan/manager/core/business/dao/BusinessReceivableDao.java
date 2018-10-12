package cn.fintecher.supply.finance.loan.manager.core.business.dao;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableFrom;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 应收账款
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
@Mapper
public interface BusinessReceivableDao{
	
	Integer insertReceivable(BusinessReceivableEntity businessReceivableEntity);
	
	List<BusinessReceivableEntity> selectByReceivable(BusinessReceivableEntity businessReceivableEntity);
	
	Integer updateReceivable(BusinessReceivableEntity businessReceivableEntity);
	
	BusinessReceivableEntity queryReceivableByPid(String pid);

	Integer queryPageCount(BusinessReceivableFrom businessReceivableFrom);

	List<BusinessReceivableEntity> queryPageList(BusinessReceivableFrom businessReceivableFrom);

	String queryNewAccountNo();
}
