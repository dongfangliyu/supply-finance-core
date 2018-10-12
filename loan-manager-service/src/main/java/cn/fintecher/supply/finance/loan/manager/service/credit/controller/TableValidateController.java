package cn.fintecher.supply.finance.loan.manager.service.credit.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.form.TableValidateForm;
import cn.fintecher.supply.finance.loan.manager.service.credit.service.TableValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**

 * @Author WuTianJuan
 * @Date Created in 10:41 2018/6/26
 */
@RestController
@RequestMapping("/file/table")
public class TableValidateController {

    @Autowired
    private TableValidateService tableValidateService;

    @ResponseBody
    @RequestMapping(value ="/fileTableValidate", method = RequestMethod.GET)
    public Message fileTableValidate(@RequestBody TableValidateForm tableValidateForm){
        return tableValidateService.fileTableValidate(tableValidateForm);
    }
}
