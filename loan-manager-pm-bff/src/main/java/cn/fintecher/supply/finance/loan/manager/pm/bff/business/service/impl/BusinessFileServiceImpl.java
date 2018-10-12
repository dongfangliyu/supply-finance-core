package cn.fintecher.supply.finance.loan.manager.pm.bff.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.pm.bff.business.core.BusinessFileCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.business.service.BusinessFileService;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-13 08:56:11
 */
@Service("businessFileService")
public class BusinessFileServiceImpl implements BusinessFileService {

	@Autowired
    private BusinessFileCore businessFileCore;
	
    public Message upload(MultipartFile file,String type,String registerCode,Long id){
    	return businessFileCore.upload(file,type,registerCode,id);
    };
	
    public Message delete(Integer pid,String userName){
    	return businessFileCore.delete(pid,userName);
    };
	
    public ResponseEntity<byte[]> search(Integer pid){
    	return businessFileCore.search(pid);
    }

	@Override
	public BusinessFileEntity queryFileByPid(Integer pid) {
		Message msg =businessFileCore.queryFileByPid(pid+"");
		if (msg.getCode() == MessageType.MSG_ERROR) {
			return null;
		}
		return JSONUtil.toBean(msg.getMessage(), BusinessFileEntity.class);
	};

}
