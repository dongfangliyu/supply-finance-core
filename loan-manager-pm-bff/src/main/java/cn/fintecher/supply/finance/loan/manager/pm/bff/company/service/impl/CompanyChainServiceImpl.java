package cn.fintecher.supply.finance.loan.manager.pm.bff.company.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CompanyChainFrom;
import cn.fintecher.supply.finance.loan.manager.common.constant.ReturnMsg;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.company.core.CompanyChainCore;
import cn.fintecher.supply.finance.loan.manager.pm.bff.company.service.CompanyChainService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.util.ExcelUtil;
import cn.fintecher.supply.finance.loan.manager.pm.bff.util.ImportExcelUtil;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-11 13:34:43
 */
@Service("companyChainService")
public class CompanyChainServiceImpl implements CompanyChainService {

	@Autowired
    private CompanyChainCore companyChainCore;
	
	
    public Message<PagedResponse<List<CompanyChainEntity>>> searchListChain(CompanyChainFrom companyChainFrom){
    	return companyChainCore.searchListChain(companyChainFrom);
    };
	
    public Message submitChain(CompanyChainEntity companyChainEntity){
    	return companyChainCore.submitChain(companyChainEntity);
    };
	
    public Message submitUploadChain(MultipartFile file,String userName){
    	InputStream in =null;  
		Map<String ,Object> map = null;
        List<List<Object>> listobj = null;//exl数据
		try {
			in = file.getInputStream();
			String fileName = file.getOriginalFilename();
			if (ChkUtil.isEmpty(fileName)) {
				return new Message(MessageType.MSG_ERROR,"company",ReturnMsg.FILE_TYPE_ERROR);
			}else{
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
				if (!"xls".equals(suffix)&&!"xlsx".equals(suffix)) {
					return new Message(MessageType.MSG_ERROR,"company",ReturnMsg.FILE_TYPE_ERROR);
				}
				
				map = ImportExcelUtil.getDataByExcel(in,fileName );
				
			}
		}catch (Exception e) {
			return new Message(MessageType.MSG_ERROR,"company",ReturnMsg.FILE_UPLOAD_ERROR);
		}finally {
			try {  
				in.close();
            } catch (IOException e) {  
            	return new Message(MessageType.MSG_ERROR,"company",ReturnMsg.FILE_UPLOAD_ERROR);
            }  
		}
		int successNum = 0;
		int errorNum = 0;
		String errorMsg = "";
		int flag = 0;
			try {
				
				listobj = (List<List<Object>>) map.get("sheetData");
				for (int i = 0; i < listobj.size(); i++) {
					if (listobj.get(i).size() ==1) {
						continue;
					}
					List<Object> list = listobj.get(i);
					/*if (!entNameIsRepeat(list.get(0).toString())) {
						continue;
					}*/
					CompanyChainEntity entity = new CompanyChainEntity();
					if (list.size()!=4) {
						flag++;
						errorNum++;
						errorMsg +="第"+flag+"行，数据不完整;"; 
						continue;
					}
					entity.setCompanyName(list.get(0).toString());
					entity.setLinkMan(list.get(1).toString());
					entity.setMobile(list.get(2).toString());
					entity.setEmail(list.get(3).toString());
					entity.setCreateBy(userName);
					Message msg = this.submitChain(entity);
					flag++;
					if (msg.getCode() == MessageType.MSG_ERROR) {
						errorNum++;
						errorMsg +="第"+flag+"行，"+msg.getMessage()+";"; 
					}else if (msg.getCode() == MessageType.MSG_SUCCESS) {
						successNum++;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Message<>(MessageType.MSG_ERROR,"company",ReturnMsg.FAILED_CURRENCY);
			}
			String resultMsg = "";
			if (successNum == flag) {
				resultMsg = "上传共"+flag+"条，其中成功共"+successNum+"条，失败共"+errorNum+"条";
			}else{
				resultMsg = "上传共"+flag+"条，其中成功共"+successNum+"条，失败共"+errorNum+"条，失败行数及原因为："+errorMsg;
			}
    	return new Message<>(MessageType.MSG_SUCCESS,"company",resultMsg);
    };
	
    public Message submitUpdateChain(CompanyChainEntity companyChainEntity){
    	return companyChainCore.submitUpdateChain(companyChainEntity);
    };
	
    public Message deleteChain(String id,String userName){
    	return companyChainCore.deleteChain(id,userName);
    };
    
    private Boolean entNameIsRepeat(String name){
    	CompanyChainEntity companyChainEntity = new CompanyChainEntity();
    	companyChainEntity.setCompanyName(name);
    	companyChainEntity.setStatus(Constants.DATA_STATUS_NOL);
    	Message msg = companyChainCore.selectByChain(companyChainEntity);
    	if (msg.getMessage() == null) {
			return true;
		}else {
			List<CompanyChainEntity> list = JSONUtil.toList(msg.getMessage(), CompanyChainEntity.class);
			if (!ChkUtil.isEmpty(list)) {
				return true;
			}
			return false;
		}
    }

	@Override
	public XSSFWorkbook downLoad(CompanyChainFrom companyChainFrom) {
		Message msg = companyChainCore.searchDownLoadList(companyChainFrom);
		List<CompanyChainEntity> list = JSONUtil.toList(msg.getMessage(), CompanyChainEntity.class);
		if (list != null && list.size() > 0){
			String sheetName = "企业列表";//sheet名
			String []title = new String[]{"企业名称","联系人","手机号码","邮箱","添加时间","注册状态"};//标题
           String[][] values = new String[list.size()][];
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        for(int i=0;i<list.size();i++){
		        	CompanyChainEntity chain = list.get(i);
		            values[i] = new String[title.length];
		            values[i][0] = chain.getCompanyName();
		            values[i][1] = chain.getLinkMan();
		            values[i][2] = chain.getMobile();
		            values[i][3] = chain.getEmail();
		            values[i][4] = sdf.format(chain.getCreateAt());
		            if (Constants.COMPANY_CHAIN_STATE_NOT.equals(chain.getState())) {
		            	values[i][5] = "未注册";
					}else  if (Constants.COMPANY_CHAIN_STATE_YES.equals(chain.getState())){
						values[i][5] = "已注册";
					}
		            
		        }
		        
		        return ExcelUtil.getXSSFWorkbook(sheetName, title, values, null);
		}
		return null;
	}

	@Override
	public Message searchList(String userName) {
		// TODO Auto-generated method stub
		return companyChainCore.searchList(userName);
	}

	@Override
	public Message inviteAgency(Long id,String userName) {
		return companyChainCore.inviteAgency(id,userName);
	}

	@Override
	public Message inviteAgencyBatch( List<String> ids,String userName) {
		for (String id: ids) {
			if(!ChkUtil.isEmpty(id)){
				companyChainCore.inviteAgency(Long.valueOf(id),userName);
			}
		}
    	return   new Message<>(MessageType.MSG_SUCCESS,"company",ReturnMsg.SSUCCESS_CURRENCY);

	}
}
