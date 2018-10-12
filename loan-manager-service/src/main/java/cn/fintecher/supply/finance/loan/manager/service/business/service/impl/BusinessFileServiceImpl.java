package cn.fintecher.supply.finance.loan.manager.service.business.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.constant.ReturnMsg;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessFastFileCore;
import cn.fintecher.supply.finance.loan.manager.service.business.core.BusinessFileCore;
import cn.fintecher.supply.finance.loan.manager.service.business.service.BusinessFileService;

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
	
	@Autowired
	private BusinessFastFileCore businessFastFileCore;
    
    @Override
	public Message insertFile(BusinessFileEntity businessFileEntity){
		return businessFileCore.insertFile(businessFileEntity);
	}
	
	@Override
	public Message selectByFile(BusinessFileEntity businessFileEntity) {
		return businessFileCore.selectByFile(businessFileEntity);
	}
	
	@Override
	public Message updateFile(BusinessFileEntity businessFileEntity) {
		return businessFileCore.updateFile(businessFileEntity);
	}
    
	@Override
	public Message queryFileByPid(String pid) {
		return businessFileCore.queryFileByPid(pid);
	}
	
    public Message upload(MultipartFile file,String type,String registerCode,Long id){

         if("voucher".equals(type)){
            Message<Integer> countMessage = businessFileCore.countByCodeAndType(type,registerCode);
            if(MessageType.MSG_SUCCESS.equals(countMessage.getCode())){
                if(countMessage.getMessage()>= 3){
                    return new Message(MessageType.MSG_ERROR,"business_service","凭证最多上传三张！");
                }
            }else{
                return countMessage;
            }
         }

    	 Message message = new Message(MessageType.MSG_SUCCESS,"business",ReturnMsg.SSUCCESS_CURRENCY);
    	 ResponseEntity<Message> entity = businessFastFileCore.fileUpload(file);
    	 message = entity.getBody();
         HashMap<String, String> params = (HashMap<String, String>) message.getMessage();
         if (message.getCode() == 0){
        	 BusinessFileEntity fileEntity = new BusinessFileEntity();
        	 fileEntity.setFileName(file.getOriginalFilename());
             fileEntity.setCategory(type);
             fileEntity.setCommodityId(id);
             fileEntity.setOwnerId(registerCode);
             fileEntity.setPath(params.get("path"));
             fileEntity.setFullPath(params.get("fullPath"));
             fileEntity.setGroup(params.get("group"));
             fileEntity.setStatus(Constants.DATA_STATUS_NOL);
             fileEntity.setCreateAt(new Date());
             fileEntity.setUpdateAt(new Date());
             try {
                 message = businessFileCore.insertFile(fileEntity);
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
         return message;
    	 
    };
	
    public Message delete(Integer pid,String userName){
    	BusinessFileEntity fileEntity = new BusinessFileEntity();
    	fileEntity.setStatus(Constants.DATA_STATUS_DEL);
    	fileEntity.setUpdateAt(new Date());
    	fileEntity.setUpdateBy(userName);
    	fileEntity.setPid(new Long(pid));
    	return this.updateFile(fileEntity);
    };
	
    public ResponseEntity<byte[]> search(Integer pid){
    	Message msg = this.queryFileByPid(pid+"");
    	BusinessFileEntity businessFileEntity = JSONUtil.toBean(msg.getMessage(), BusinessFileEntity.class);
    	String fullPath = businessFileEntity.getFullPath();
        ResponseEntity<byte[]> responseEntity = fileDownLoad(fullPath, "image");
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, getContentType(fullPath));
        return new ResponseEntity(responseEntity.getBody(),headers,HttpStatus.OK);
    };
    /**
     * 文件下载
     *
     * @param fullPath  文件上传返回的文件保存完整地址
     * @param fileName  文件名称
     * @return
     */
    public ResponseEntity<byte[]> fileDownLoad(String fullPath, String fileName) {
        return businessFastFileCore.fileDownLoad(fullPath, fileName);
    }

    /**
     获取文件MIME类型
     @param fileName
     @return*/

    public static String getContentType(String fileName){
        String type = null;
        Path path = Paths.get(fileName);
        try {
            type = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return type;
    }
}

