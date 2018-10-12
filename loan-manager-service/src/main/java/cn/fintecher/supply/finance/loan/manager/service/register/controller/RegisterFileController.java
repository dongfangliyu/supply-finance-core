package cn.fintecher.supply.finance.loan.manager.service.register.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.BaseTemplateDictionaryEntity;
import cn.fintecher.supply.finance.loan.manager.service.register.service.RegisterFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gonghebin
 * @date 2018/6/27 0027下午 1:24
 */
@RestController
@RequestMapping("/register/file")
public class RegisterFileController {

    @Autowired
    private RegisterFileService registerFileService;

    @RequestMapping(value ="/uploadIdentityFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Message uploadIdentityFile(@RequestPart("file") MultipartFile file, @RequestParam(value = "type") String type,@RequestParam(value = "registerCode") String registerCode){
        return registerFileService.uploadIdentityFile(file,type,registerCode);
    }

    @RequestMapping(value ="/searchRegisterFile", method = RequestMethod.GET)
    public Message searchRegisterFile(@RequestParam(value = "type") String type,@RequestParam(value = "registerCode") String registerCode){
        return registerFileService.searchRegisterFile(type,registerCode);
    }

    @ResponseBody
    @RequestMapping(value ="/deleteRegisterFile", method = RequestMethod.GET)
    public Message deleteRegisterFile(@RequestParam(value = "type") String type,@RequestParam(value = "registerCode") String registerCode){
        return registerFileService.deleteRegisterFile(type,registerCode);
    }

    @ResponseBody
    @RequestMapping(value ="/donwloadEntrustment", method = RequestMethod.GET)
    public ResponseEntity<byte[]> donwloadEntrustment(@RequestParam(value = "category") String category){
        return registerFileService.donwloadEntrustment(category);
    }

    @ResponseBody
    @RequestMapping(value ="/donwloadRegisterIDCard", method = RequestMethod.GET)
    public ResponseEntity<byte[]> donwloadRegisterIDCard(@RequestParam(value = "pid") Long pid){
        return registerFileService.donwloadRegisterIDCard(pid);
    }

    @ResponseBody
    @RequestMapping(value ="/searchBaseTemplateDictionaryByCode", method = RequestMethod.GET)
    BaseTemplateDictionaryEntity searchBaseTemplateDictionaryByCode(@RequestParam(value = "category") String category){
        return registerFileService.searchBaseTemplateDictionaryByCode(category);
    }
}
