package cn.fintecher.supply.finance.loan.manager.service.register.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/27 0027下午 1:54
 */
@FeignClient(name = "loan-manager-core")
public interface FCRegisterFileCore {

    /**
     * 添加
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/fileCore/saveRegisterFile", method = RequestMethod.POST)
    Message saveRegisterFile(@RequestBody RegisterFileEntity registerFileEntity);

    /**
     * 查看
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/fileCore/searchRegisterFile", method = RequestMethod.GET)
    RegisterFileEntity searchRegisterFile(@RequestParam(value = "type") String type,@RequestParam(value = "ownerId") String ownerId);

    /**
     * 根据ownerId查询文件
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/fileCore/searchRegisterFileByOwnerId", method = RequestMethod.GET)
    List<RegisterFileEntity> searchRegisterFileByOwnerId(@RequestParam(value = "ownerId") String ownerId);

    /**
     * 更新
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/fileCore/updateRegisterFile", method = RequestMethod.POST)
    void updateRegisterFile(@RequestBody RegisterFileEntity fileEntity);

    /**
     * 根据pid查询文件
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/fileCore/searchRegisterFileByPid", method = RequestMethod.GET)
    RegisterFileEntity searchRegisterFileByPid(@RequestParam(value = "pid") Long pid);

    /**
     * 根据category查询模板
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register/fileCore/searchBaseTemplateDictionaryByCode", method = RequestMethod.GET)
    BaseTemplateDictionaryEntity searchBaseTemplateDictionaryByCode(@RequestParam(value = "category") String category);
}
