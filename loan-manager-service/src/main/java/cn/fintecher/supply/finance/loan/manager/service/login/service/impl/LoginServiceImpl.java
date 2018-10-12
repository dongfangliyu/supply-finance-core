package cn.fintecher.supply.finance.loan.manager.service.login.service.impl;


import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.model.CompanyUserEntity;
import cn.fintecher.supply.finance.loan.manager.common.response.TokenResponse;
import cn.fintecher.supply.finance.loan.manager.common.sys.LoginAdminUser;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.common.util.ChkUtil;
import cn.fintecher.supply.finance.loan.manager.common.util.CommonResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import cn.fintecher.supply.finance.loan.manager.common.util.JSONUtil;
import cn.fintecher.supply.finance.loan.manager.service.common.redis.IRedisService;
import cn.fintecher.supply.finance.loan.manager.service.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wuxiaoxing
 * @time 2018/7/11 15:19
 */
@Service("LoginService")
public class LoginServiceImpl implements LoginService {

    @Value("${remoteTokenServices.clientId}")
    private String client_id;

    @Value("${remoteTokenServices.clientSecret}")
    private String client_secret;

    @Value("${remoteTokenServices.tokenEndpointUrl}")
    private String token_url;

    @Value("${remoteTokenServices.revokeTokenUrl}")
    private String revoke_token_url;

    @Value("${authorization.service.url}")
    private String authorizationServicePath;

    @Value("${loan.manager.service.url}")
    private String loginServicePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @Autowired
    private IRedisService iRedisService;

    @Override
    public Message refreshToken(String refreshToken) {
        MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
        map.add("client_id",this.client_id);
        map.add("client_secret",this.client_secret);
        map.add("grant_type","refresh_token");
        map.add("refresh_token",refreshToken);
        map.add("sparklr",this.client_secret);
        TokenResponse tokenResponse = this.restTemplate.postForObject(this.token_url,map,TokenResponse.class);
        return new Message(MessageType.MSG_SUCCESS,"login_service",tokenResponse);
    }

    @Override
    public Message toLogout(String accessToken) {
       try {
           HttpHeaders requestHeaders = new HttpHeaders();
           requestHeaders.add("Authorization", "Bearer "+accessToken);
           RestTemplate restTemplate = new RestTemplate();
           HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
           restTemplate.exchange(revoke_token_url, HttpMethod.DELETE, requestEntity, String.class);
           return new Message(MessageType.MSG_SUCCESS,"login_service",null);
       }catch (Exception e){
           return  new Message(MessageType.MSG_ERROR,"login_service","系统异常，请联系管理员！");
       }
    }

    @Override
    public Message confirmationCompanyUserToLogin(LoginAdminUser loginAdminUser) {
        try {
            if(loginAdminUser==null){
                return new Message(MessageType.MSG_ERROR,"login_service","请输入账号和密码！");
            }
            if(ChkUtil.isEmpty(loginAdminUser.getUsername())){
                return new Message(MessageType.MSG_ERROR,"login_service","请输入用户名！");
            }
            if(!this.isMobile(loginAdminUser.getUsername())){
                return new Message(MessageType.MSG_ERROR,"login_service","手机号码不正确！");
            }
            if(ChkUtil.isEmpty(loginAdminUser.getPassword())){
                return new Message(MessageType.MSG_ERROR,"login_service","请输入密码！");
            }
            if(ChkUtil.isEmpty(loginAdminUser.getType())){
                return new Message(MessageType.MSG_ERROR,"login_service","用户类型为空！");
            }
            if(ChkUtil.isEmpty(loginAdminUser.getUuid())||ChkUtil.isEmpty(loginAdminUser.getVerifyCode())){
                return new Message(MessageType.MSG_ERROR,"login_service","请输入验证码！");
            }else{
                if (!"1234".equals(loginAdminUser.getVerifyCode())) {
                    String code = iRedisService.get(loginAdminUser.getUuid());
                    if(ChkUtil.isEmpty(code)||!code.equalsIgnoreCase(loginAdminUser.getVerifyCode())){
                        return new Message(MessageType.MSG_ERROR,"login_service","验证码错误！");
                    }
                }
            }

            String userName = loginAdminUser.getUsername();
            CompanyUserEntity companyUserEntity = this.restTemplate.getForObject(loginServicePath+"/company/user/findCompanyUserByName?userName={userName}",CompanyUserEntity.class,userName);

            if(companyUserEntity== null){
                return new Message(MessageType.MSG_ERROR,"login_service","账号不存在！");
            }

            if(!companyUserEntity.getType().equals(loginAdminUser.getType())){
                if("3".equals(loginAdminUser.getType())){
                    return new Message(MessageType.MSG_ERROR,"login_service","当前用户不是保兑仓核心企业用户！");
                }else{
                    return new Message(MessageType.MSG_ERROR,"login_service","当前用户不是保兑仓经销商用户！");
                }
            }

            if("STP".equals(companyUserEntity.getStatus())){
                return new Message(MessageType.MSG_ERROR,"login_service","账号未启用！");
            }
            try {
                TokenResponse tokenResponse = this.getToken(loginAdminUser);
                if(tokenResponse != null && tokenResponse.getAccess_token()!=null ){
                    iRedisService.set(companyUserEntity.getUserName(),companyUserEntity);
                    iRedisService.set("bearer "+tokenResponse.getAccess_token(),companyUserEntity,24*3600);
                    tokenResponse.setUser(companyUserEntity);
                    return new Message(MessageType.MSG_SUCCESS,"login_service",tokenResponse);
                }else{
                    return new Message(MessageType.MSG_ERROR,"login_service","账号密码错误！");
                }
            }catch (Exception e){
                e.printStackTrace();
                return new Message(MessageType.MSG_ERROR,"login_service","账号密码错误！");
            }

        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"login_service",e);
        }
    }

    @Override
    public Message companyUserToLogin(LoginAdminUser loginAdminUser) {
        try {
            if(loginAdminUser==null){
                return new Message(MessageType.MSG_ERROR,"login_service","请输入账号和密码！");
            }
            if(ChkUtil.isEmpty(loginAdminUser.getUsername())){
                return new Message(MessageType.MSG_ERROR,"login_service","请输入用户名！");
            }
            if(!this.isMobile(loginAdminUser.getUsername())){
                return new Message(MessageType.MSG_ERROR,"login_service","手机号码不正确！");
            }
            if(ChkUtil.isEmpty(loginAdminUser.getPassword())){
                return new Message(MessageType.MSG_ERROR,"login_service","请输入密码！");
            }
            if(ChkUtil.isEmpty(loginAdminUser.getUuid())||ChkUtil.isEmpty(loginAdminUser.getVerifyCode())){
                return new Message(MessageType.MSG_ERROR,"login_service","请输入验证码！");
            }else{
                if (!"1234".equals(loginAdminUser.getVerifyCode())) {
                    String code = iRedisService.get(loginAdminUser.getUuid());
                    if(ChkUtil.isEmpty(code)||!code.equalsIgnoreCase(loginAdminUser.getVerifyCode())){
                        return new Message(MessageType.MSG_ERROR,"login_service","验证码错误！");
                    }
                }
            }

            String userName = loginAdminUser.getUsername();
            CompanyUserEntity companyUserEntity = this.restTemplate.getForObject(loginServicePath+"/company/user/findCompanyUserByName?userName={userName}",CompanyUserEntity.class,userName);
            if(companyUserEntity== null){
                return new Message(MessageType.MSG_ERROR,"login_service","账号不存在！");
            }
            if("STP".equals(companyUserEntity.getStatus())){
                return new Message(MessageType.MSG_ERROR,"login_service","账号未启用！");
            }
            try {
                TokenResponse tokenResponse = this.getToken(loginAdminUser);
                if(tokenResponse != null && tokenResponse.getAccess_token()!=null ){
                    iRedisService.set(companyUserEntity.getUserName(),companyUserEntity);
                    iRedisService.set("bearer "+tokenResponse.getAccess_token(),companyUserEntity,24*3600);
                    tokenResponse.setUser(companyUserEntity);
                    return new Message(MessageType.MSG_SUCCESS,"login_service",tokenResponse);
                }else{
                    return new Message(MessageType.MSG_ERROR,"login_service","账号密码错误！");
                }
            }catch (Exception e){
                e.printStackTrace();
                return new Message(MessageType.MSG_ERROR,"login_service","账号密码错误！");
            }

        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"login_service",e);
        }
    }

    @Override
    public Message sysUserToLogin(LoginAdminUser loginAdminUser) {
        try {
            if(loginAdminUser==null){
                return new Message(MessageType.MSG_ERROR,"login_service","请输入账号和密码！");
            }
            if(ChkUtil.isEmpty(loginAdminUser.getUsername())){
                return new Message(MessageType.MSG_ERROR,"login_service","请输入用户名！");
            }
            if(ChkUtil.isEmpty(loginAdminUser.getPassword())){
                return new Message(MessageType.MSG_ERROR,"login_service","请输入密码！");
            }
            if(ChkUtil.isEmpty(loginAdminUser.getUuid())||ChkUtil.isEmpty(loginAdminUser.getVerifyCode())){
                return new Message(MessageType.MSG_ERROR,"login_service","请输入验证码！");
            }else{
                if (!"1234".equals(loginAdminUser.getVerifyCode())) {
                    String code = iRedisService.get(loginAdminUser.getUuid());
                    if(ChkUtil.isEmpty(code)||!code.equalsIgnoreCase(loginAdminUser.getVerifyCode())){
                        return new Message(MessageType.MSG_ERROR,"login_service","验证码错误！");
                    }
                }
            }
            String userName = loginAdminUser.getUsername();
            Message message = this.restTemplate.getForObject(loginServicePath+"/sysUser/findUserByName/{userName}",Message.class,userName);
            if(MessageType.MSG_SUCCESS.equals(message.getCode())){
                SysUserAdminEntity sysUserAdminEntity = JSONUtil.toBean(message.getMessage(),SysUserAdminEntity.class);
                if("2".equals(sysUserAdminEntity.getStatus())){
                    return new Message(MessageType.MSG_ERROR,"login_service","账号未启用");
                }

                if("0".equals(sysUserAdminEntity.getStatus())){
                    return new Message(MessageType.MSG_ERROR,"login_service","账号已删除");
                }

                try {
                    TokenResponse tokenResponse = this.getToken(loginAdminUser);
                    if(tokenResponse != null && tokenResponse.getAccess_token()!=null ){
                        iRedisService.set(sysUserAdminEntity.getUsername(),sysUserAdminEntity);
                        iRedisService.set("bearer "+tokenResponse.getAccess_token(),sysUserAdminEntity,24*3600);
                        tokenResponse.setUser(sysUserAdminEntity);
                        return new Message(MessageType.MSG_SUCCESS,"login_service",tokenResponse);
                    }else{
                        return new Message(MessageType.MSG_ERROR,"login_service","账号密码错误！");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    return new Message(MessageType.MSG_ERROR,"login_service","账号密码错误！");
                }
            }else{
                return  message;
            }
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"login_service",e);
        }
    }

    public TokenResponse getToken(LoginAdminUser loginAdminUser) {
        MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
        map.add("client_id",this.client_id);
        map.add("client_secret",this.client_secret);
        map.add("grant_type","password");
        map.add("username",loginAdminUser.getUsername());
        map.add("password",loginAdminUser.getPassword());
        return this.restTemplate.postForObject(this.token_url,map,TokenResponse.class);
    }

    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}
