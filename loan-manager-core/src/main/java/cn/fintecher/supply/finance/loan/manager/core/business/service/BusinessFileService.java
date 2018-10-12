package cn.fintecher.supply.finance.loan.manager.core.business.service;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import java.util.List;

/**
 * 
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-13 08:56:11
 */
public interface BusinessFileService{

    /**
	 * 新增
	 * @param 
	 * @return
	 */
	Integer insertFile(BusinessFileEntity businessFileEntity);
	
	/**
	 * 查询
	 * @param 
	 * @return
	 */
	List<BusinessFileEntity> selectByFile(BusinessFileEntity businessFileEntity);
	
	/**
	 * 修改
	 * @param 
	 */
	Integer updateFile(BusinessFileEntity businessFileEntity);
	
	/**
	 * 根据主键查询
	 * @param 
	 * @return
	 */
	BusinessFileEntity queryFileByPid(String pid);

    List<BusinessFileEntity> queryEnterFileList(String orderCode);

	List<BusinessFileEntity> querySuppFileList(String suppCode);

    Integer countByCodeAndType(String category, String ownerId);

	List<BusinessFileEntity> getListByCodeAndType(String category, String ownerId);
}
