package cn.fintecher.supply.finance.loan.manager.core.business.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.core.business.dao.BusinessReceivableDao;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessReceivableEntity;
import cn.fintecher.supply.finance.loan.manager.common.business.request.BusinessReceivableFrom;
import cn.fintecher.supply.finance.loan.manager.core.business.service.BusinessReceivableService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-12 16:01:09
 */
@Service("businessReceivableService")
public class BusinessReceivableServiceImpl implements BusinessReceivableService {

   	@Autowired
	private BusinessReceivableDao businessReceivableDao;
	
	@Override
	public Integer insertReceivable(BusinessReceivableEntity businessReceivableEntity){
		 businessReceivableDao.insertReceivable(businessReceivableEntity);
		 return businessReceivableEntity.getPid().intValue();
	}
	
	@Override
	public List<BusinessReceivableEntity> selectByReceivable(BusinessReceivableEntity businessReceivableEntity) {
		return businessReceivableDao.selectByReceivable(businessReceivableEntity);
	}
	
	@Override
	public Integer updateReceivable(BusinessReceivableEntity businessReceivableEntity) {
		// TODO Auto-generated method stub
		return businessReceivableDao.updateReceivable(businessReceivableEntity);
	}
	
	@Override
	public BusinessReceivableEntity queryReceivableByPid(String pid) {
		// TODO Auto-generated method stub
		return businessReceivableDao.queryReceivableByPid(pid);
	}

	@Override
	public Integer queryPageCount(BusinessReceivableFrom businessReceivableFrom) {
		// TODO Auto-generated method stub
		return businessReceivableDao.queryPageCount(businessReceivableFrom);
	}

	@Override
	public List<BusinessReceivableEntity> queryPageList(BusinessReceivableFrom businessReceivableFrom) {
		// TODO Auto-generated method stub
		return businessReceivableDao.queryPageList(businessReceivableFrom);
	}

	@Override
	public String queryNewAccountNo() {
		String str= businessReceivableDao.queryNewAccountNo();
		return str;
	}

}
