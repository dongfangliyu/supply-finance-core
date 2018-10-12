package cn.fintecher.supply.finance.loan.manager.pm.bff.company.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.supply.finance.loan.manager.common.company.request.CompanyChainFrom;
import cn.fintecher.supply.finance.loan.manager.common.constant.ReturnMsg;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.company.service.CompanyChainService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.util.DownLoadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-11 13:34:43
 */
@Api(value = "链属名单管理", tags = "链属名单管理")
@RestController
@RequestMapping("/company/chain")
public class CompanyChainController {

	@Autowired
	private CompanyChainService companyChainService;


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="链属名单列表", notes="链属名单列表")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
	@RequestMapping(value ="/searchListChain", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message<PagedResponse<List<CompanyChainEntity>>> searchListChain(@RequestBody CompanyChainFrom companyChainFrom,Principal principa){
		Message<PagedResponse<List<CompanyChainEntity>>> msg = new Message<>(MessageType.MSG_SUCCESS,"company",null);
		try {
			String userName = getUserName(principa);
			companyChainFrom.setUserName(userName);

			Message<PagedResponse<List<CompanyChainEntity>>> message =companyChainService.searchListChain(companyChainFrom);
			msg.setCode(message.getCode());
			msg.setMessage(message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"company",ReturnMsg.FAILED_CURRENCY);
		}
		return msg;

	}

	/**
	 * 链属名单列表-保兑仓
	 * @author tym
	 * @date 2018-8-28
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="链属名单列表-保兑仓", notes="链属名单列表-保兑仓")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
	@RequestMapping(value ="/searchListChainBSC", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message<PagedResponse<List<CompanyChainEntity>>> searchListChainBSC(@RequestBody CompanyChainFrom companyChainFrom,Principal principa){
		Message<PagedResponse<List<CompanyChainEntity>>> msg = new Message<>(MessageType.MSG_SUCCESS,"company",null);
		try {
			String userName = getUserName(principa);
			companyChainFrom.setUserName(userName);

			Message<PagedResponse<List<CompanyChainEntity>>> message =companyChainService.searchListChain(companyChainFrom);
			msg.setCode(message.getCode());
			msg.setMessage(message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"company",ReturnMsg.FAILED_CURRENCY);
		}
		return msg;

	}

	/**
	 * 邀请经销商-保兑仓
	 * @author tym
	 * @date 2018-8-28
	 */
	@ApiOperation(value="邀请经销商", notes="邀请经销商")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
	@RequestMapping(value ="/inviteAgency", method = RequestMethod.GET)
	public Message inviteAgency(@RequestParam(value="id")Long id,Principal principa){
		Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
		try {
			Message message =companyChainService.inviteAgency(id,this.getUserName(principa));
			msg.setCode(message.getCode());
			msg.setMessage(message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;

	}

	/**
	 * 批量邀请经销商-保兑仓
	 * @author tym
	 * @date 2018-8-29
	 */
	@ApiOperation(value="批量邀请经销商", notes="批量邀请经销商")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
	@RequestMapping(value ="/inviteAgencyBatch", method = RequestMethod.GET)
	public Message inviteAgencyBatch(@RequestParam(value = "ids") String ids,Principal principa){
		Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
		try {
			if(!ChkUtil.isEmpty(ids)&&ids.split(",").length>0){
				List<String> resultList= new ArrayList<String>(Arrays.asList(ids.split(",")));
				Message message =companyChainService.inviteAgencyBatch(resultList,this.getUserName(principa));
				msg.setCode(message.getCode());
				msg.setMessage(message.getMessage());
			}else{
				msg.setCode(MessageType.MSG_ERROR);
				msg.setMessage("传参为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;

	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="增加链属名单", notes="增加链属名单")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
	@RequestMapping(value ="/submitChain", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message submitChain(@RequestBody  CompanyChainEntity companyChainEntity,Principal principa){
		Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
		try {
			String userName = getUserName(principa);
			companyChainEntity.setCreateBy(userName);
			Message message =companyChainService.submitChain(companyChainEntity);
			msg.setCode(message.getCode());
			msg.setMessage(message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="批量上传链属名单", notes="批量上传链属名单")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
	@RequestMapping(value ="/submitUploadChain", consumes = "multipart/form-data", method = RequestMethod.POST)
	public Message submitUploadChain(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("file") MultipartFile file,Principal principa){
		Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
		try {
			if(file.isEmpty()){
				return new Message(MessageType.MSG_ERROR,"company","文件不存在");
			}  
			Message message =companyChainService.submitUploadChain(file,getUserName(principa));
			msg.setCode(message.getCode());
			msg.setMessage(message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="保存编辑链属名单", notes="保存编辑链属名单")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
	@RequestMapping(value ="/submitUpdateChain", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message submitUpdateChain(@RequestBody  CompanyChainEntity companyChainEntity,Principal principa){
		Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
		try {
			if (ChkUtil.isEmpty(companyChainEntity.getPid())) {
				return new Message(MessageType.MSG_ERROR,"company",ReturnMsg.FAILED_CURRENCY);
			}
			companyChainEntity.setUpdateBy(getUserName(principa));
			Message message =companyChainService.submitUpdateChain(companyChainEntity);
			msg.setCode(message.getCode());
			msg.setMessage(message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="删除链属名单", notes="删除链属名单")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
	@RequestMapping(value ="/deleteChain", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message deleteChain(@ApiParam(required = true, name = "id", value = "链属名单id")@RequestParam("id") String id,Principal principa){
		Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
		try {
			Message message =companyChainService.deleteChain(id,getUserName(principa));
			msg.setCode(message.getCode());
			msg.setMessage(message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;

	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value="下载链属列表", notes="下载链属列表")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
	@RequestMapping(value ="/downLoad", method = RequestMethod.GET)
	public void  downLoad(HttpServletResponse response, HttpServletRequest request,
			@ApiParam(required = false, name = "companyName", value = "公司名称") @RequestParam(name = "companyName", required = false) String companyName,
			@ApiParam(required = false, name = "state", value = "注册状态") @RequestParam(name = "state", required = false) String state,
			@ApiParam(required = false, name = "timeStart", value = "开始时间") @RequestParam(name = "timeStart", required = false) String timeStart,
			@ApiParam(required = true, name = "paramAuth", value = "权限校验") @RequestParam(name = "paramAuth", required = true) String paramAuth,
			@ApiParam(required = true, name = "ed", value = "权限校验") @RequestParam(name = "ed", required = true) Long ed,
			@ApiParam(required = false, name = "timeEnd", value = "结束时间") @RequestParam(name = "timeEnd", required = false) String timeEnd){
		try {
			CompanyChainFrom companyChainFrom = new CompanyChainFrom();
			companyChainFrom.setCompanyName(companyName);
			companyChainFrom.setTimeEnd(timeEnd);
			companyChainFrom.setTimeStart(timeStart);
			companyChainFrom.setState(state);
			companyChainFrom.setOwerId(ed);
			companyChainFrom.setUserName(paramAuth);
			XSSFWorkbook wb  =companyChainService.downLoad(companyChainFrom);
			if (wb == null){
				return ;
			}
			//将文件存到指定位置  
			try {  
				setResponseHeader(response, "chain_"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xlsx");
				OutputStream os = response.getOutputStream();  
				wb.write(os);  
				os.flush();  
				os.close();  
			} catch (Exception e) {  
				e.printStackTrace();  
			}  
		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}


	private String getUserName(Principal principa){
		return principa.getName();
}

	private void setResponseHeader(HttpServletResponse response, String fileName) {  
		try {  
			try {  
				fileName = new String(fileName.getBytes(),"ISO8859-1");  
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}  
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename="+ fileName);  
			response.addHeader("Pargam", "no-cache");  
			response.addHeader("Cache-Control", "no-cache");  
		} catch (Exception ex) {  
			ex.printStackTrace();  
		}  
	} 

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value="链属名单下拉选项", notes="链属名单下拉选项")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
	@RequestMapping(value ="/searchList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Message searchList(Principal principa){
		Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
		try {
			String userName = getUserName(principa);
			Message message =companyChainService.searchList(userName);
			msg.setCode(message.getCode());
			msg.setMessage(message.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"company",ReturnMsg.FAILED_CURRENCY);
		}
		return msg;

	}

}
