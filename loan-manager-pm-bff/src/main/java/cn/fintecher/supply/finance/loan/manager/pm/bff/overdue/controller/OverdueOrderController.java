package cn.fintecher.supply.finance.loan.manager.pm.bff.overdue.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRecordForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRepaymentForm;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.pm.bff.overdue.service.OverdueOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 17:36:53
 */
@Api(value = "逾期管理接口", tags = "逾期管理接口")
@RestController
@RequestMapping("/overdue/overdueOrder")
public class OverdueOrderController {

    @Autowired
    private OverdueOrderService overdueOrderService;
    

	@ApiOperation(value="逾期信息列表", notes="逾期信息列表")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchOrderList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchOrderList(@RequestBody OverdueOrderForm overdueOrderForm,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
        try {
        	overdueOrderForm.setUserId(getUserName(principa));
            Message message =overdueOrderService.searchOrderList(overdueOrderForm);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="逾期详情", notes="逾期详情")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchOrderDetail", method = RequestMethod.GET)
    public Message searchOrderDetail(@RequestParam("id")Long id){
        Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
        try {
            Message message =overdueOrderService.searchOrderDetail(id);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="逾期还款提交", notes="逾期还款提交")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/submitRepaymentRecord", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitRepaymentRecord(@RequestBody OverdueOrderRepaymentForm overdueOrderRepaymentForm,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
        try {
        	if (ChkUtil.isEmpty(overdueOrderRepaymentForm.getRepaymentPrice())) {
        		return new  Message<>(MessageType.MSG_ERROR,"overdue","还款金额不可为空");
			}
        	if (ChkUtil.isEmpty(overdueOrderRepaymentForm.getRepaymentTime())) {
        		return new  Message<>(MessageType.MSG_ERROR,"overdue","还款时间不可为空");
        	}
        	if (ChkUtil.isEmpty(overdueOrderRepaymentForm.getId())) {
        		return new  Message<>(MessageType.MSG_ERROR,"overdue","还款id不可为空");
        	}
        	overdueOrderRepaymentForm.setUserId(getUserName(principa));
            Message message =overdueOrderService.submitRepaymentRecord(overdueOrderRepaymentForm);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="逾期还款记录列表", notes="逾期还款记录列表")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchRepaymenList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchRepaymenList(@RequestBody OverdueOrderRecordForm overdueOrderRecordForm,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
        try {
        	if (ChkUtil.isEmpty(overdueOrderRecordForm.getId())) {
				return new  Message<>(MessageType.MSG_ERROR,"overdue","id不可为空");
			}
        	overdueOrderRecordForm.setUserId(getUserName(principa));
            Message message =overdueOrderService.searchRepaymenList(overdueOrderRecordForm);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="下载excel", notes="下载excel")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/downLoadOrder", method = RequestMethod.GET)
    public void downLoadOrder(HttpServletResponse response, HttpServletRequest request,
			@ApiParam(required = false, name = "supplierName", value = "供应商公司名称") @RequestParam(name = "supplierName", required = false) String supplierName,
			@ApiParam(required = false, name = "overdueStartDay", value = "逾期开始天数") @RequestParam(name = "overdueStartDay", required = false) Integer overdueStartDay,
			@ApiParam(required = false, name = "overdueEndDay", value = "逾期结束天数") @RequestParam(name = "overdueEndDay", required = false) Integer overdueEndDay,
			@ApiParam(required = false, name = "state", value = "结清状态  0未结清 1已结清") @RequestParam(name = "state", required = false) String state){
        Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
        try {
        	OverdueOrderForm overdueOrderForm = new OverdueOrderForm();
        	overdueOrderForm.setSupplierName(supplierName);
        	overdueOrderForm.setState(state);
        	overdueOrderForm.setOverdueStartDay(overdueStartDay);
        	overdueOrderForm.setOverdueEndDay(overdueEndDay);
        	XSSFWorkbook wb  =overdueOrderService.downLoadOrder(overdueOrderForm);
        	if (wb == null) {
				return;
			}
        	try {  
				setResponseHeader(response, "overdue_"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xlsx");  
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
			response.setContentType("application/octet-stream;charset=ISO8859-1");  
			response.setHeader("Content-Disposition", "attachment;filename="+ fileName);  
			response.addHeader("Pargam", "no-cache");  
			response.addHeader("Cache-Control", "no-cache");  
		} catch (Exception ex) {  
			ex.printStackTrace();  
		}  
	} 
   

}
