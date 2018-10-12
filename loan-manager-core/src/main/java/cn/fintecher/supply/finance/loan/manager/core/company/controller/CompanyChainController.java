package cn.fintecher.supply.finance.loan.manager.core.company.controller;


import cn.fintecher.supply.finance.loan.manager.common.company.request.CompanyChainFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyChainEntity;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.core.company.service.CompanyChainService;
import io.swagger.annotations.ApiOperation;



/**
 * 链属名单
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-11 13:34:43
 */
@RestController
@RequestMapping("company/chain")
public class CompanyChainController {
    @Autowired
    private CompanyChainService companyChainService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertChain", method = RequestMethod.POST)
    public Message insertChain(@RequestBody CompanyChainEntity companyChainEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
    	try {
    		msg.setMessage(companyChainService.insertChain(companyChainEntity));
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
    @RequestMapping(value = "/updateChain", method = RequestMethod.POST)
    public Message updateChain(@RequestBody CompanyChainEntity companyChainEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
    	try {
    		msg.setMessage(companyChainService.updateChain(companyChainEntity));
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
    @RequestMapping(value = "/selectByChain", method = RequestMethod.POST)
    public Message selectByChain(@RequestBody CompanyChainEntity companyChainEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
    	try {
    		msg.setMessage(companyChainService.selectByChain(companyChainEntity));
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
    @RequestMapping(value = "/queryChainByPid", method = RequestMethod.GET)
    public Message queryChainByPid(@RequestParam("pid") String pid){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
    	try {
    		msg.setMessage(companyChainService.queryChainByPid(pid));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
	/**
	 * 分页查询条数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPageCount", method = RequestMethod.POST)
	public Message queryPageCount(@RequestBody CompanyChainFrom companyChainFrom){
		Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
		try {
			msg.setMessage(companyChainService.queryPageCount(companyChainFrom));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;
	}

	/**
	 * 分页查询List内容
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPageList", method = RequestMethod.POST)
	public Message queryPageList(@RequestBody CompanyChainFrom companyChainFrom){
		Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
		try {
			msg.setMessage(companyChainService.queryPageList(companyChainFrom));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
		return msg;
	}
    
	@ApiOperation(value="查询下载链属列表", notes="查询下载链属列表")
    @RequestMapping(value ="/searchDownLoadList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchDownLoadList(@RequestBody CompanyChainFrom companyChainFrom){
        Message msg = new Message(MessageType.MSG_SUCCESS,"company",null);
        try {
            msg.setMessage(companyChainService.searchDownLoadList(companyChainFrom));
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }

	@ResponseBody
	@RequestMapping(value = "/getByCompanyName", method = RequestMethod.GET)
	public Message<CompanyChainEntity> getByCompanyName(@RequestParam(value = "companyName") String companyName){
		try {
			return new Message(MessageType.MSG_SUCCESS,"companyChain_core",companyChainService.getByCompanyName(companyName));
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(MessageType.MSG_ERROR,"companyChain_core",e.getMessage());
		}

	}

}