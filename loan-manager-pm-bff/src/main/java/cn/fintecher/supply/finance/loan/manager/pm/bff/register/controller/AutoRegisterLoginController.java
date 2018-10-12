package cn.fintecher.supply.finance.loan.manager.pm.bff.register.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.pm.bff.register.service.AutoRegisterLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gonghebin
 * @date 2018/6/19 0019下午 1:35
 */
@RestController
@RequestMapping("/auto/login")
@Api(tags = "自动注册接口")
public class AutoRegisterLoginController {

    @Autowired
    private AutoRegisterLoginService autoRegisterLoginService;

    @ApiOperation(value = "静默注册用户", notes = "静默注册用户")
    @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.GET)
    public Message addOrUpdateUser() {
        Message message = autoRegisterLoginService.addOrUpdateUser();
        return message;
    }



}
