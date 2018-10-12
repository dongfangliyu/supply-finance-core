package cn.fintecher.supply.finance.loan.manager.core.commodity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import cn.fintecher.supply.finance.loan.manager.common.commodity.entity.CommodityStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.commodity.query.CommodityStockForm;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.core.commodity.service.CommodityStockInfoService;



/**
 * 入库信息表
 *
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-18 11:01:04
 */
@RestController
@RequestMapping("commodity/stockInfo")
public class CommodityStockInfoController {
    @Autowired
    private CommodityStockInfoService commodityStockInfoService;

     /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/insertStockInfo", method = RequestMethod.POST)
    public Message insertStockInfo(@RequestBody CommodityStockInfoEntity commodityStockInfoEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"commodity",null);
    	try {
    		msg.setMessage(commodityStockInfoService.insertStockInfo(commodityStockInfoEntity));
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
    @RequestMapping(value = "/updateStockInfo", method = RequestMethod.POST)
    public Message updateStockInfo(@RequestBody CommodityStockInfoEntity commodityStockInfoEntity){
    	Message msg = new Message(MessageType.MSG_SUCCESS,"commodity",null);
    	try {
    		msg.setMessage(commodityStockInfoService.updateStockInfo(commodityStockInfoEntity));
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
    @RequestMapping(value = "/selectByStockInfo", method = RequestMethod.POST)
    public Message<List<CommodityStockInfoEntity>> selectByStockInfo(@RequestBody CommodityStockInfoEntity commodityStockInfoEntity){
    	Message<List<CommodityStockInfoEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
    	try {
    		msg.setMessage(commodityStockInfoService.selectByStockInfo(commodityStockInfoEntity));
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
    @RequestMapping(value = "/queryStockInfoByPid", method = RequestMethod.GET)
    public Message<CommodityStockInfoEntity> queryStockInfoByPid(@RequestParam("pid") String pid){
    	Message<CommodityStockInfoEntity> msg = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
    	try {
    		msg.setMessage(commodityStockInfoService.queryStockInfoByPid(pid));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    @ResponseBody
    @RequestMapping(value = "/queryPageList", method = RequestMethod.POST)
	public Message<List<CommodityStockInfoEntity>> queryPageList(@RequestBody CommodityStockForm commdityStockForm){
    	Message<List<CommodityStockInfoEntity>> msg = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
    	try {
    		msg.setMessage(commodityStockInfoService.queryPageList(commdityStockForm));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setCode(MessageType.MSG_ERROR);
		}
    	return msg;
    }
    
    @ResponseBody
    @RequestMapping(value = "/queryPageCount", method = RequestMethod.POST)
    public Message<Integer> queryPageCount(@RequestBody CommodityStockForm commdityStockForm){
    	Message<Integer> msg = new Message<>(MessageType.MSG_SUCCESS,"commodity",null);
    	try {
    		msg.setMessage(commodityStockInfoService.queryPageCount(commdityStockForm));
    	} catch (Exception e) {
    		// TODO: handle exception
    		e.printStackTrace();
    		msg.setCode(MessageType.MSG_ERROR);
    	}
    	return msg;
    }
    
    @ResponseBody
    @RequestMapping(value = "/queryNewWarehousingNo", method = RequestMethod.GET)
    public String queryNewWarehousingNo(){
    	return this.commodityStockInfoService.queryNewWarehousingNo();
    }



	@ResponseBody
	@RequestMapping(value = "/selectStockList", method = RequestMethod.POST)
	public List<CommodityStockInfoEntity> selectStockList(@RequestBody CommodityStockForm commodityStockForm){
    	return commodityStockInfoService.selectStockList(commodityStockForm);
	}

	@ResponseBody
	@RequestMapping(value = "/selectStockListCount", method = RequestMethod.POST)
	public Integer selectStockListCount(@RequestBody CommodityStockForm commodityStockForm){
		return commodityStockInfoService.selectStockListCount(commodityStockForm);
	}

	@ResponseBody
	@RequestMapping(value = "/selectCommodityStockInfo", method = RequestMethod.GET)
	public Message selectCommodityStockInfo(@RequestParam("pid") Long pid){
		return new Message(MessageType.MSG_SUCCESS,"commodityStockInfo",commodityStockInfoService.selectCommodityStockInfo(pid));
	}


}