package cn.fintecher.supply.finance.loan.manager.pm.bff.credit.controller;

/**
 * @Author WuTianJuan
 * @Date Created in 20:51 2018/7/2
 */

import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service.BaseTemplateDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/base/enterprise")
@Api(tags = "模板下载接口")

public class BaseTemplateDictionaryController {

    @Autowired
    private BaseTemplateDictionaryService baseTemplateDictionaryService;

    @ApiOperation(value = "模板下载", notes = "模板下载")
    @ResponseBody
    @RequestMapping(value ="/downloadDocTemplate", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadDocTemplate(){
        String code = "creditModel";
        return baseTemplateDictionaryService.downloadDocTemplate(code);
    }
}
