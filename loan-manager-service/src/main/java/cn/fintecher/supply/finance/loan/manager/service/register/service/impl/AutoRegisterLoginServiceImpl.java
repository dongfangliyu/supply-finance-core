package cn.fintecher.supply.finance.loan.manager.service.register.service.impl;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.constant.MsgCodeConstant;
import cn.fintecher.supply.finance.loan.manager.common.form.AutoRegisterForm;
import cn.fintecher.supply.finance.loan.manager.common.sys.TokenResponse;
import cn.fintecher.supply.finance.loan.manager.service.register.service.AutoRegisterLoginService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * @author gonghebin
 * @date 2018/6/20 0020下午 5:52
 */
@Service
public class AutoRegisterLoginServiceImpl implements AutoRegisterLoginService {

    @Value("${authorization.service.url}")
    private String authorizationServicePath;

    @Value("${remoteTokenServices.clientId}")
    private String client_id;

    @Value("${remoteTokenServices.clientSecret}")
    private String client_secret;

    @Value("${remoteTokenServices.tokenEndpointUrl}")
    private String token_url;


    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }


    @Override
    public Message addOrUpdateUser() {
        Message message = new Message();
        String userName = "register" + UUID.randomUUID().toString().substring(0,11);
        message = save(userName);
        TokenResponse token = getToken(userName);
        AutoRegisterForm autoRegisterForm = new AutoRegisterForm();
        autoRegisterForm.setCode(userName);
        autoRegisterForm.setToken(token.getAccess_token());
        message.setMessage(autoRegisterForm);
        message.setCode(MsgCodeConstant.ERR_MSG_SUCCESS);
        return message;
    }


    /**
     * 临时注册
     * @return
     */
    public Message save(String userName){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",userName);
        jsonObject.put("password",encoder.encode("123456"));
        Message message = this.restTemplate.postForObject(authorizationServicePath+"/oauth/createUser",jsonObject,Message.class);
        return message;
    }

    public TokenResponse getToken(String username) {
        MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
        map.add("client_id",this.client_id);
        map.add("client_secret",this.client_secret);
        map.add("grant_type","password");
        map.add("username",username);
        map.add("password","123456");
        return this.restTemplate.postForObject(this.token_url,map,TokenResponse.class);
    }


}
