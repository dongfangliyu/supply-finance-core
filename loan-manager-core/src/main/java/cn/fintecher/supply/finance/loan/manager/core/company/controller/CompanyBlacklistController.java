package cn.fintecher.supply.finance.loan.manager.core.company.controller;


import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyBlacklistEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.core.company.service.CompanyBlacklistService;



/**
 * 黑名单历史记录
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-20 11:42:47
 */
@RestController
@RequestMapping("company/blacklist")
public class CompanyBlacklistController {
    @Autowired
    private CompanyBlacklistService companyBlacklistService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertBlacklist", method = RequestMethod.POST)
    public Message insertBlacklist(@RequestBody CompanyBlacklistEntity companyBlacklistEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
    	try {
    		msg.setMessage(companyBlacklistService.insertBlacklist(companyBlacklistEntity));
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
    @RequestMapping(value = "/updateBlacklist", method = RequestMethod.POST)
    public Message updateBlacklist(@RequestBody CompanyBlacklistEntity companyBlacklistEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
    	try {
    		msg.setMessage(companyBlacklistService.updateBlacklist(companyBlacklistEntity));
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
    @RequestMapping(value = "/selectByBlacklist", method = RequestMethod.POST)
    public Message<List<CompanyBlacklistEntity>> selectByBlacklist(@RequestBody CompanyBlacklistEntity companyBlacklistEntity){
    	Message<List<CompanyBlacklistEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"company",null);
    	try {
    		msg.setMessage(companyBlacklistService.selectByBlacklist(companyBlacklistEntity));
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
    @RequestMapping(value = "/queryBlacklistByPid", method = RequestMethod.GET)
    public Message<CompanyBlacklistEntity> queryBlacklistByPid(@RequestParam("pid") String pid){
    	Message<CompanyBlacklistEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"company",null);
    	try {
    		msg.setMessage(companyBlacklistService.queryBlacklistByPid(pid));
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
	@RequestMapping(value = "/selectBlackList", method = RequestMethod.POST)
	public List<CompanyBlacklistEntity> selectBlackList(@RequestBody BlackListFrom blackListFrom){
		return companyBlacklistService.selectBlackList(blackListFrom);
	}

	/**
	 * 根据主键查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectBlackListCount", method = RequestMethod.POST)
	public Integer selectBlackListCount(@RequestBody BlackListFrom blackListFrom){
		return companyBlacklistService.selectBlackListCount(blackListFrom);
	}
}