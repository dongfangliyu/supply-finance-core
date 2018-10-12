package cn.fintecher.supply.finance.loan.manager.core.business.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fintecher.supply.finance.loan.manager.core.business.dao.BusinessFileDao;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.core.business.service.BusinessFileService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-13 08:56:11
 */
@Service("businessFileService")
public class BusinessFileServiceImpl implements BusinessFileService {

   	@Autowired
	private BusinessFileDao businessfileDao;
	
	@Override
	public Integer insertFile(BusinessFileEntity businessFileEntity){
		 businessfileDao.insertFile(businessFileEntity);
		 return businessFileEntity.getPid().intValue();
	}
	
	@Override
	public List<BusinessFileEntity> selectByFile(BusinessFileEntity businessFileEntity) {
		return businessfileDao.selectByFile(businessFileEntity);
	}
	
	@Override
	public Integer updateFile(BusinessFileEntity businessFileEntity) {
		// TODO Auto-generated method stub
		return businessfileDao.updateFile(businessFileEntity);
	}
	
	@Override
	public BusinessFileEntity queryFileByPid(String pid) {
		// TODO Auto-generated method stub
		return businessfileDao.queryFileByPid(pid);
	}

	@Override
	public List<BusinessFileEntity> queryEnterFileList(String orderCode) {
		return businessfileDao.queryEnterFileList(orderCode);
	}

	@Override
	public List<BusinessFileEntity> querySuppFileList(String suppCode) {
		return businessfileDao.querySuppFileList(suppCode);
	}

	@Override
	public Integer countByCodeAndType(String category, String ownerId) {
		Map<String,Object> map = new HashMap<>();
		map.put("category",category);
		map.put("ownerId",ownerId);
		return businessfileDao.countByCodeAndType(map);
	}

	@Override
	public List<BusinessFileEntity> getListByCodeAndType(String category, String ownerId) {
		Map<String,Object> map = new HashMap<>();
		map.put("category",category);
		map.put("ownerId",ownerId);
		return businessfileDao.getListByCodeAndType(map);
	}
}
