package cn.fintecher.supply.finance.loan.manager.pm.bff.commodity.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.util.PagedResponse;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackAdminForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.request.WarehouseUnpackForm;
import cn.fintecher.supply.finance.loan.manager.common.warehouse.response.WarehouseUnpackResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.commodity.service.WarehouseUnpackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 9:59 2018/8/23
 */
@Api(value = "仓单解押管理接口", tags = "仓单解押管理接口")
@RestController
@RequestMapping("/warehouse/pledge")
public class WarehouseUnpackController {
    @Autowired
    private WarehouseUnpackService warehouseUnpackService;

    @ApiOperation(value="查看前台仓单解押列表", notes="查看前台仓单解押列表")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchWarehouseUnpackList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message<PagedResponse<List<WarehouseUnpackResponse>>> searchWarehouseUnpackList(@RequestBody WarehouseUnpackForm warehouseUnpackForm,Principal principal){
        warehouseUnpackForm.setUserName(principal.getName());
        try {
            return  warehouseUnpackService.searchWarehouseUnpackList(warehouseUnpackForm);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"commodityStock",e.getMessage());
        }
    }

    @ApiOperation(value="查看后台仓单解押列表", notes="查看后台仓单解押列表")
    @RequestMapping(value ="/searchAdminWarehouseUnpackList", method = RequestMethod.POST)
    public Message<PagedResponse<List<WarehouseUnpackResponse>>> searchAdminWarehouseUnpackList(@RequestBody WarehouseUnpackAdminForm warehouseUnpackAdminForm){
        try {
            return warehouseUnpackService.searchAdminWarehouseUnpackList(warehouseUnpackAdminForm);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(MessageType.MSG_ERROR,"commodityStock",e.getMessage());
        }
    }

    @ApiOperation(value="查看后台仓单解押详情", notes="查看后台仓单解押详情")
    @RequestMapping(value ="/searchAdminWarehouseUnpackDetail", method = RequestMethod.GET)
    public Message searchAdminWarehouseUnpackDetail(@RequestParam(value="pid") Long pid){
        try {
            return warehouseUnpackService.searchAdminWarehouseUnpackDetail(pid);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"commodityStock",e.getMessage());
        }
    }

    @ApiOperation(value="查看前台仓单解押详情", notes="查看前台仓单解押详情")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchWarehouseUnpackDetail", method = RequestMethod.GET)
    public Message searchWarehouseUnpackDetail(@RequestParam(value="pid") Long pid,Principal principal){
        try {
            return warehouseUnpackService.searchWarehouseUnpackDetail(pid,principal.getName());
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"commodityStock",e.getMessage());
        }
    }

    @ApiOperation(value="后台申请解押", notes="后台申请解押")
    @RequestMapping(value ="/submitWarehouseUnpack", method = RequestMethod.GET)
    public Message submitWarehouseUnpack(@RequestParam(value="pid") Long pid){
        try {
            return warehouseUnpackService.submitWarehouseUnpack(pid);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"commodityStock",e.getMessage());
        }
    }


    @ApiOperation(value="前台申请解押", notes="前台申请解押")
    @RequestMapping(value ="/submitFrontWarehouseUnpack", method = RequestMethod.GET)
    public Message submitFrontWarehouseUnpack(@RequestParam(value="pid") Long pid,@RequestParam("time") String time){
        try {
            return warehouseUnpackService.submitFrontWarehouseUnpack(pid,time);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"commodityStock",e.getMessage());
        }
    }


}
