package cn.fintecher.supply.finance.loan.manager.pm.bff.credit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.TableValidateForm;
import cn.fintecher.supply.finance.loan.manager.pm.bff.Base.controller.BaseController;
import cn.fintecher.supply.finance.loan.manager.pm.bff.credit.service.TableValidateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author WuTianJuan
 * @Date Created in 10:20 2018/6/26
 */
@RestController
@RequestMapping("/file/table")
@Api("表格验证")
public class TableValidateController extends BaseController {

    @Autowired
    private TableValidateService tableValidateService;

    @ApiOperation(value = "验证文件是否为null", notes = "验证文件是否为null")
    @ResponseBody
    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public Message fileTableValidate(@RequestBody TableValidateForm tableValidateForm) {
        return tableValidateService.fileTableValidate(tableValidateForm);
    }
}
