package cn.fintecher.supply.finance.loan.manager.service.company.controller;

import cn.fintecher.supply.finance.loan.manager.common.company.request.BlackListFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import cn.fintecher.supply.finance.loan.manager.service.company.service.CompanyBlacklistService;
import cn.fintecher.supply.finance.loan.manager.common.company.entity.CompanyBlacklistEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-20 11:42:47
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/company/blacklist")
public class CompanyBlacklistController {

    @Autowired
    private CompanyBlacklistService companyBlacklistService;


    @ResponseBody
    @RequestMapping(value ="/selectBlackList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message selectBlackList(@RequestBody BlackListFrom blackListFrom){
        return companyBlacklistService.selectBlackList(blackListFrom);
    }

   

}
