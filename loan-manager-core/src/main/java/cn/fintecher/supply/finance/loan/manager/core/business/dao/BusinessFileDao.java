package cn.fintecher.supply.finance.loan.manager.core.business.dao;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-13 08:56:11
 */
@Mapper
public interface BusinessFileDao{
	
	Integer insertFile(BusinessFileEntity businessFileEntity);
	
	List<BusinessFileEntity> selectByFile(BusinessFileEntity businessFileEntity);
	
	Integer updateFile(BusinessFileEntity businessFileEntity);
	
	BusinessFileEntity queryFileByPid(String pid);

    List<BusinessFileEntity> queryEnterFileList(String orderCode);

	List<BusinessFileEntity> querySuppFileList(String suppCode);

    Integer countByCodeAndType(Map<String,Object> map);

	List<BusinessFileEntity> searchFileByCommodityId(String pid);

    List<BusinessFileEntity> getListByCodeAndType(Map<String,Object> map);
}
