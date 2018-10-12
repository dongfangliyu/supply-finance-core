package cn.fintecher.supply.finance.loan.manager.service.register.feign;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.model.RegisterFileEntity;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gonghebin
 * @date 2018/6/27 0027下午 1:26
 */
@FeignClient(name = "file-service" , url = "${file.service.url}")
public interface FCFileCore {
    /**
     * 文件上传
     * @param file  文件
     * @return
     * @throws Exception
     */
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/fast/file/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Message> fileUpload(@RequestPart MultipartFile file);


    /**
     * 文件下载
     * @param fullPath  文件上传返回的文件保存完整地址
     * @param fileName  文件名称
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/fast/file/download", method = RequestMethod.GET,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<byte[]> fileDownLoad(@RequestParam(value = "fullPath") String fullPath, @RequestParam(value = "fileName") String fileName);



}
