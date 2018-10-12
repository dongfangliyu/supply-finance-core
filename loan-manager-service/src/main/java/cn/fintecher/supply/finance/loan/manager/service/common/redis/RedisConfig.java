package cn.fintecher.supply.finance.loan.manager.service.common.redis;


import cn.fintecher.supply.finance.loan.manager.common.util.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
@Component
public class RedisConfig extends CachingConfigurerSupport {

	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.password}")
	private String password;
	@Value("${spring.redis.timeout}")
	private int timeout;
	/**
	 * Redis配置信息
	 * @return
	 */
    @Bean
    public JedisPoolConfig getJedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        config.setMaxWaitMillis(-1);
        config.setMaxTotal(100);
        config.setMinIdle(0);
        return config;  
    }  
    
    /**
     * Redis连接工厂
     * @return
     */
	@Bean
	public JedisConnectionFactory getConnectionFactory(){
		JedisConnectionFactory factory = new JedisConnectionFactory();
        JedisPoolConfig config = getJedisPoolConfig();
        factory.setPoolConfig(config);  
        factory.setDatabase(0);
        factory.setHostName(host);
        factory.setPort(port);
        //生产放开
		factory.setPassword(password);
        factory.setTimeout(timeout);
        factory.setUsePool(true);
		return factory;
	}
	
	/**
	 * RedisTemplate缓存操作类;
	 * 
	 * @param factory
	 * @return
	 */
	@Bean(name="shiroRedisTemplate")
	public RedisTemplate<String, Object> shiroRedisTemplate(RedisConnectionFactory factory){
		RedisTemplate<String,Object> template = new RedisTemplate<String,Object>();
		template.setConnectionFactory(getConnectionFactory());
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringRedisSerializer);
		return template;
	}
	
	@Bean(name="redisTemplate")
	public RedisTemplate<String, String> redisTemplate(){
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(getConnectionFactory());
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
		template.setKeySerializer(stringRedisSerializer);
		template.setValueSerializer(genericJackson2JsonRedisSerializer);
		template.setHashKeySerializer(stringRedisSerializer);
		//template.setHashValueSerializer(genericJackson2JsonRedisSerializer);
		return template;
	}
	
	/**
	 * 缓存管理器.
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisTemplate<String, Object> template) {
		CacheManager cacheManager = new RedisCacheManager(redisTemplate());
		return cacheManager;
	}
	
	/**
	 * 默认redis key生成器
	 * @return
	 */
	@Bean
	public KeyGenerator defaultKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return Constants.REDIS_KEY_PREFIX + sb.toString();
			}
		};
	}
	
	/**
	 * 模块列表redis key生成器
	 * @return
	 */
	@Bean
	public KeyGenerator moduleListKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				return Constants.REDIS_KEY_PREFIX + "ModuleListCacheKey";
			}
		};
	}
	
	@Bean
	public static ConfigureRedisAction configureRedisAction() { return ConfigureRedisAction.NO_OP; }}









