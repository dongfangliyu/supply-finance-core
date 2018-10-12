package cn.fintecher.supply.finance.loan.manager.core.overdue.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.overdue.entity.OverdueRepaymentRecordEntity;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderForm;
import cn.fintecher.supply.finance.loan.manager.common.overdue.request.OverdueOrderRecordForm;
import cn.fintecher.supply.finance.loan.manager.core.overdue.service.OverdueRepaymentRecordService;



/**
 * 逾期还款记录表
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-23 13:24:15
 */
@RestController
@RequestMapping("overdue/repaymentRecord")
public class OverdueRepaymentRecordController {
    @Autowired
    private OverdueRepaymentRecordService overdueRepaymentRecordService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertRepaymentRecord", method = RequestMethod.POST)
    public Message insertRepaymentRecord(@RequestBody OverdueRepaymentRecordEntity overdueRepaymentRecordEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
    	try {
    		msg.setMessage(overdueRepaymentRecordService.insertRepaymentRecord(overdueRepaymentRecordEntity));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
     /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/updateRepaymentRecord", method = RequestMethod.POST)
    public Message updateRepaymentRecord(@RequestBody OverdueRepaymentRecordEntity overdueRepaymentRecordEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"overdue",null);
    	try {
    		msg.setMessage(overdueRepaymentRecordService.updateRepaymentRecord(overdueRepaymentRecordEntity));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    

    /**
     * 查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectByRepaymentRecord", method = RequestMethod.POST)
    public Message<List<OverdueRepaymentRecordEntity>> selectByRepaymentRecord(@RequestBody OverdueRepaymentRecordEntity overdueRepaymentRecordEntity){
    	Message<List<OverdueRepaymentRecordEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"overdue",null);
    	try {
    		msg.setMessage(overdueRepaymentRecordService.selectByRepaymentRecord(overdueRepaymentRecordEntity));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    /**
     * 根据主键查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryRepaymentRecordByPid", method = RequestMethod.GET)
    public Message<OverdueRepaymentRecordEntity> queryRepaymentRecordByPid(@RequestParam("pid") String pid){
    	Message<OverdueRepaymentRecordEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"overdue",null);
    	try {
    		msg.setMessage(overdueRepaymentRecordService.queryRepaymentRecordByPid(pid));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    /**
     * 分页查询list
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryListByRecordForm", method = RequestMethod.POST)
    public Message<List<OverdueRepaymentRecordEntity>> queryListByRecordForm(@RequestBody OverdueOrderRecordForm overdueOrderForm){
    	Message<List<OverdueRepaymentRecordEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"overdue",null);
    	try {
    		msg.setMessage(overdueRepaymentRecordService.queryListByRecordForm(overdueOrderForm));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    /**
     * 分页查询总数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryCountByRecordForm", method = RequestMethod.POST)
    public Message<Integer> queryCountByRecordForm(@RequestBody OverdueOrderRecordForm overdueOrderForm){
    	Message<Integer> msg = new Message<>(MessageType.MSG_SUCCESS,"overdue",null);
    	try {
    		msg.setMessage(overdueRepaymentRecordService.queryCountByRecordForm(overdueOrderForm));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    

}