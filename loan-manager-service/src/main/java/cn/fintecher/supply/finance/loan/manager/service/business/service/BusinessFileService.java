package cn.fintecher.supply.finance.loan.manager.service.business.service;

import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import cn.fintecher.common.utils.basecommon.message.Message;

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
	Message insertFile(BusinessFileEntity businessFileEntity);
	
	/**
	 * 查询
	 * @param quotaApply
	 * @return
	 */
	Message selectByFile(BusinessFileEntity businessFileEntity);
	
	/**
	 * 修改
	 * @param quotaApply
	 */
	Message updateFile(BusinessFileEntity businessFileEntity);
	
	/**
	 * 根据主键查询
	 * @param quotaApply
	 */
	Message queryFileByPid(String pid);
 
	
    Message upload(MultipartFile file,String type,String registerCode,Long id);
	
    Message delete(Integer pid,String userName);
	
    ResponseEntity<byte[]> search(Integer pid);
   


   

}