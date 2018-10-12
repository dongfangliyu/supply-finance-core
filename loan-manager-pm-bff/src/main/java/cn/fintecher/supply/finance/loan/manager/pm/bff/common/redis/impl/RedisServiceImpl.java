package cn.fintecher.supply.finance.loan.manager.pm.bff.common.redis.impl;


import cn.fintecher.supply.finance.loan.manager.pm.bff.common.redis.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("redisService")
public class RedisServiceImpl implements IRedisService {

    @Value("${loan.manager.service.url}")
    private String userServicePath;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate() {
        this.restTemplate = new  RestTemplate();
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        return this.restTemplate.getForObject(this.userServicePath+"/redis/getObject?key={key}?clazz={clazz}",clazz,key,clazz);
    }

    @Override
    public <T> void setObject(String key, T obj) {
        this.restTemplate.getForObject(this.userServicePath+"/redis/setObject?key={key}&obj={obj}",Void.class,key,obj);
    }

    @Override
    public void setString(String key, String value, long liveTime) {
        this.restTemplate.getForObject(this.userServicePath+"/redis/setString?key={key}&value={value}&liveTime={liveTime}",Void.class,key,value,liveTime);
    }

    @Override
    public String getString(String key) {
        return this.restTemplate.getForObject(this.userServicePath+"/redis/getString?key={key}",String.class,key);
    }
}
