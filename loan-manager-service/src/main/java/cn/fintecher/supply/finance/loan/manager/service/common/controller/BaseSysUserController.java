package cn.fintecher.supply.finance.loan.manager.service.common.controller;

import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.StringEscapeEditor;
import cn.fintecher.supply.finance.loan.manager.service.common.redis.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wuxiaoxing
 * @time 2018/6/6 10:18
 */
public class BaseSysUserController {

    @Autowired
    private IRedisService redisService;

    @Value("${loan.manager.service.url}")
    private String userServicePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }


    /**
     * 获取当前用户
     * */
    public SysUserAdminEntity getSysUser(String userName){
        SysUserAdminEntity user =redisService.get(userName,SysUserAdminEntity.class);
        if(user!=null){
            return  user;
        }else{
            CommonResponse<SysUserAdminEntity> response = this.restTemplate.getForObject(userServicePath+"/sysUser/userInfoByName/{userName}",CommonResponse.class,userName);
            user = response.getData();
            redisService.set(userName,user);
            return  user;
        }

    }

    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     */
    public String getRemoteHost(HttpServletRequest request) {
        String ip = "";
        try{
            ip = request.getHeader("X-Forwarded-For");

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("WL-Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_CLIENT_IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                }
            } else if (ip.length() > 15) {
                String[] ips = ip.split(",");
                for (int index = 0; index < ips.length; index++) {
                    String strIp = (String) ips[index];
                    if (!("unknown".equalsIgnoreCase(strIp))) {
                        ip = strIp;
                        break;
                    }
                }
            }
        }catch(Throwable e){

        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }

    /**
     * 获取当前从多少条开始查询
     * @author huanglei
     */
    public Integer getStartIndex(int startIndex, int pageSize, int pageNo){
        Integer index = 0;
        if(startIndex == 0){
            index = (pageNo - 1) * pageSize;
        }else{
            index = startIndex;
        }
        return index;
    }

}
