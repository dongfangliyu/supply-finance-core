package cn.fintecher.supply.finance.loan.manager.service.commodity.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackAdminForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.response.WarehouseUnpackResponse;
import cn.fintecher.supply.finance.loan.manager.service.commodity.service.WarehouseUnpackService;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseCompanyUserController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 9:59 2018/8/23
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/warehouse/pledge")
public class WarehouseUnpackController extends BaseCompanyUserController {
    @Autowired
    private WarehouseUnpackService warehouseUnpackService;

    @ApiOperation(value="查看前台仓单解押列表", notes="查看前台仓单解押列表")
    @ResponseBody
    @RequestMapping(value ="/searchWarehouseUnpackList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchWarehouseUnpackList(@RequestBody WarehouseUnpackForm warehouseUnpackForm){
        CompanyUserEntity user = getCompanyUser(warehouseUnpackForm.getUserName());
        //CompanyUserEntity user = new CompanyUserEntity();
        return warehouseUnpackService.searchWarehouseUnpackList(warehouseUnpackForm,user);

    }

    @ApiOperation(value="查看后台仓单解押列表", notes="查看后台仓单解押列表")
    @ResponseBody
    @RequestMapping(value = "/searchAdminWarehouseUnpackList", method = RequestMethod.POST)
    public Message searchAdminWarehouseUnpackList(@RequestBody WarehouseUnpackAdminForm warehouseUnpackAdminForm){
        return warehouseUnpackService.searchAdminWarehouseUnpackList(warehouseUnpackAdminForm);
    }

    @ApiOperation(value="查看后台仓单解押详情", notes="查看后台仓单解押详情")
    @RequestMapping(value ="/searchAdminWarehouseUnpackDetail", method = RequestMethod.GET)
    @ResponseBody
    public Message searchAdminWarehouseUnpackDetail(@RequestParam(value="pid") Long pid){
        return warehouseUnpackService.searchAdminWarehouseUnpackDetail(pid);
    }


    @ResponseBody
    @RequestMapping(value = "/submitWarehouseUnpack", method = RequestMethod.GET)
    public  Message submitWarehouseUnpack(@RequestParam(value = "pid") Long pid){
        return warehouseUnpackService.submitWarehouseUnpack(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/submitFrontWarehouseUnpack", method = RequestMethod.GET)
    public  Message submitFrontWarehouseUnpack(@RequestParam(value = "pid") Long pid, @RequestParam(value="time") String time){
        return warehouseUnpackService.submitFrontWarehouseUnpack(pid,time);
    }

    @ResponseBody
    @RequestMapping(value = "/searchWarehouseUnpackDetail", method = RequestMethod.GET)
    public Message searchWarehouseUnpackDetail(@RequestParam(value = "pid") Long pid, @RequestParam(value = "userName") String userName){
        CompanyUserEntity user = getCompanyUser(userName);
        return warehouseUnpackService.searchWarehouseUnpackDetail(pid,user);
    }
}
