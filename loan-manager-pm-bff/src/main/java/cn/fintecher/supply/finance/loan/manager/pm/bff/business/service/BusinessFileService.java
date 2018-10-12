package cn.fintecher.supply.finance.loan.manager.pm.bff.business.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-13 08:56:11
 */
public interface BusinessFileService {    
	
    public Message upload( MultipartFile file,String type,String registerCode,Long id);
	
    public Message delete(Integer pid,String userName);
	
    public ResponseEntity<byte[]> search(Integer pid);

	public BusinessFileEntity queryFileByPid(Integer pid);
   

}
