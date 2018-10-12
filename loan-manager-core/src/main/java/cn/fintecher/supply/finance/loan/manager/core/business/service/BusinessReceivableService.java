package cn.fintecher.supply.finance.loan.manager.core.business.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableFrom;

import java.util.List;

/**
 * 应收账款
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
public interface BusinessReceivableService{

    /**
	 * 新增应收账款
	 * @param 
	 * @return
	 */
	Integer insertReceivable(BusinessReceivableEntity businessReceivableEntity);
	
	/**
	 * 查询应收账款
	 * @param 
	 * @return
	 */
	List<BusinessReceivableEntity> selectByReceivable(BusinessReceivableEntity businessReceivableEntity);
	
	/**
	 * 修改应收账款
	 * @param 
	 */
	Integer updateReceivable(BusinessReceivableEntity businessReceivableEntity);
	
	/**
	 * 根据主键查询应收账款
	 * @param 
	 * @return
	 */
	BusinessReceivableEntity queryReceivableByPid(String pid);

	Integer queryPageCount(BusinessReceivableFrom businessReceivableFrom);

	List<BusinessReceivableEntity> queryPageList(BusinessReceivableFrom businessReceivableFrom);

	String queryNewAccountNo();
}
