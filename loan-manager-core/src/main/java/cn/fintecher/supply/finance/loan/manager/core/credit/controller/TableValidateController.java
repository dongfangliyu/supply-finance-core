package cn.fintecher.supply.finance.loan.manager.core.credit.controller;

import cn.fintecher.supply.finance.loan.manager.core.credit.service.TableValidateService;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyFileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WuTianJuan
 * @Date Created in 14:06 2018/6/26
 */
@RestController
@RequestMapping("/financial/file")
public class TableValidateController {

    @Autowired
    private TableValidateService tableValidateService;

    @ResponseBody
    @RequestMapping(value = "/searchAllFile", method = RequestMethod.GET)
    public List<CompanyFileEntity> searchAllFile(@RequestParam(value ="pid") Long pid){
        return tableValidateService.searchAllFile(pid);
    }
}
