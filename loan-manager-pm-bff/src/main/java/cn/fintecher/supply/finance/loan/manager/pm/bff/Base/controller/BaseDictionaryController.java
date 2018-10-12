package cn.fintecher.supply.finance.loan.manager.pm.bff.Base.controller;

import cn.fintecher.supply.finance.loan.manager.common.response.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.response.DictionaryResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.service.BaseDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gonghebin
 * @date 2018/7/2 0002下午 1:12
 */
@RestController
@RequestMapping("/base/dictionary")
@Api("字典查询接口")
public class BaseDictionaryController {

    @Autowired
    private BaseDictionaryService baseDictionaryService;



    @ApiOperation(value="获取所有字典数据接口", notes="获取所有字典数据接口")
    @RequestMapping(value ="/allList", method = RequestMethod.GET)
    public CommonResponse<DictionaryResponse> getAllList(){
        CommonResponse<DictionaryResponse> response = baseDictionaryService.getAllList();
        return response;
    }

    @ApiOperation(value="不同类型字典数据", notes="字典接口")
    @RequestMapping(value ="/list", method = RequestMethod.GET)
    public CommonResponse<DictionaryResponse> getDictionaryByPlanApplyId(@RequestParam(value = "category") String category){
        CommonResponse<DictionaryResponse> response = baseDictionaryService.getDictionaryByPlanApplyId(category);
        return response;
    }


}
