package cn.fintecher.supply.finance.loan.manager.service.register.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.service.register.service.AutoRegisterLoginService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gonghebin
 * @date 2018/6/20 0020下午 1:59
 */
@RestController
@RequestMapping("/auto/register")
public class AutoRegisterLoginController {

    @Autowired
    private AutoRegisterLoginService autoRegisterLoginService;

    @ResponseBody
    @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.GET)
    public Message addOrUpdateUser() {
        return autoRegisterLoginService.addOrUpdateUser();
    }

}
