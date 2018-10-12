package cn.fintecher.supply.finance.loan.manager.core.business.dao;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessOrderEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessFinancingRequest;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessOrderFrom;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 订单
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-14 14:59:16
 */
@Mapper
public interface BusinessOrderDao{
	
	Integer insertOrder(BusinessOrderEntity businessOrderEntity);
	
	List<BusinessOrderEntity> selectByOrder(BusinessOrderEntity businessOrderEntity);
	
	Integer updateOrder(BusinessOrderEntity businessOrderEntity);
	
	BusinessOrderEntity queryOrderByPid(String pid);

	Integer queryPageCount(BusinessOrderFrom businessOrderFrom);

	List<BusinessOrderEntity> queryPageList(BusinessOrderFrom businessOrderFrom);

    List<BusinessOrderEntity> queryFinancingPageList(BusinessFinancingRequest businessFinancingRequest);

	Integer queryFinancingPageCount(BusinessFinancingRequest businessFinancingRequest);

	BusinessOrderEntity searchBusinessOrederByOwnerId(String ownerId);
}
