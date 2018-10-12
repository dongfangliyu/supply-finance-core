package cn.fintecher.supply.finance.loan.manager.service.business.core;

import cn.fintecher.common.utils.basecommon.message.Message;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-service", url = "${file.service.url}")
public interface BusinessFastFileCore {

    /**
     * 文件上传
     * @param file  文件
     * @return
     * @throws Exception
     */
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/fast/file/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Message> fileUpload(MultipartFile file);

    /**
     * 文件下载
     * @param fullPath  文件上传返回的文件保存完整地址
     * @param fileName  文件名称
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/fast/file/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> fileDownLoad(@RequestParam(value = "fullPath") String fullPath, @RequestParam(value = "fileName") String fileName);

    /**
     * 文件删除
     * @param fullPath  文件上传返回的文件保存完整地址
     * @return
     */
    @Headers("Accept2: application/json")
    @ResponseBody
    @RequestMapping(value = "/fast/file/delete", method = RequestMethod.GET)
    public ResponseEntity<Message> fileDelete(@RequestParam(value = "fullPath") String fullPath);

}
