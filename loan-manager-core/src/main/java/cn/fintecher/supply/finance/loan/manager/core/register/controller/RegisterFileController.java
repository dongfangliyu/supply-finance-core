package cn.fintecher.supply.finance.loan.manager.core.register.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import cn.fintecher.supply.finance.loan.manager.core.register.service.RegisterFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gonghebin
 * @date 2018/6/27 0027下午 3:08
 */
@RestController
@RequestMapping("/register/fileCore")
public class RegisterFileController {

    @Autowired
    private RegisterFileService registerFileService;

    /**
     * 添加
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveRegisterFile", method = RequestMethod.POST)
    public Message saveRegisterFile(@RequestBody RegisterFileEntity registerFileEntity){
        Message message = new Message();
        message = registerFileService.saveRegisterFile(registerFileEntity);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }

    /**
     * 查看
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchRegisterFile", method = RequestMethod.GET)
    public RegisterFileEntity searchRegisterFile(@RequestParam(value = "type") String type,@RequestParam(value = "ownerId") String ownerId){
        return registerFileService.searchRegisterFile(type,ownerId);
    }

    /**
     * 根据ownerId查询文件
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchRegisterFileByOwnerId", method = RequestMethod.GET)
    public List<RegisterFileEntity> searchRegisterFileByOwnerId(@RequestParam(value = "ownerId") String ownerId){
        return registerFileService.searchRegisterFileByOwnerId(ownerId);
    }

    /**
     * 更新
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateRegisterFile", method = RequestMethod.POST)
    public void updateRegisterFile(@RequestBody RegisterFileEntity fileEntity){
        registerFileService.updateRegisterFile(fileEntity);
    }

    /**
     * 根据pid查询文件
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchRegisterFileByPid", method = RequestMethod.GET)
    public RegisterFileEntity searchRegisterFileByPid(@RequestParam(value = "pid") Long pid){
        return registerFileService.searchRegisterFileByPid(pid);
    }

    /**
     * 根据category查询模板
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchBaseTemplateDictionaryByCode", method = RequestMethod.GET)
    public BaseTemplateDictionaryEntity searchBaseTemplateDictionaryByCode(@RequestParam(value = "category") String category){
        return registerFileService.searchBaseTemplateDictionaryByCode(category);
    }
}
