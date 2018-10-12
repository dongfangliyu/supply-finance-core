package cn.fintecher.supply.finance.loan.manager.service.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxiaoxing
 * @time 2018/5/31 19:46
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private  IRedisService redisService;

    /**
     * 设置一个对象 可以是string
     * @param key
     * @param obj
     * @param clazz
     */
    @ResponseBody
    @RequestMapping(value = "getObject",method = RequestMethod.GET)
    public <T> T getObject(final String key, Class<T> clazz){
        return redisService.get(key,clazz);
    }

    /**
     * 取一个对象
     */
    @ResponseBody
    @RequestMapping(value = "setObject",method = RequestMethod.GET)
    public <T> void setObject(final String key, final T obj){
        redisService.set(key,obj);
    }

    /**
     * 添加key value 并且设置存活时间(byte)
     *
     * @param key
     * @param value
     * @param liveTime
     */
    @ResponseBody
    @RequestMapping(value = "setString",method = RequestMethod.GET)
    public void setString(String key, String value, long liveTime){
        redisService.set(key,value,liveTime);
    }

    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getString",method = RequestMethod.GET)
    public String getString(String key){
        return  redisService.get(key);
    }


}
