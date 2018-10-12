package cn.fintecher.supply.finance.loan.manager.service.common.redis;

public interface IRedisService {
	
    /**
     * 通过key删除
     * 
     * @param key
     */
    long del(String... keys);

    /**
     * 添加key value 并且设置存活时间(byte)
     * 
     * @param key
     * @param value
     * @param liveTime
     */
    void set(byte[] key, byte[] value, long liveTime);

    /**
     * 添加key value 并且设置存活时间
     * 
     * @param key
     * @param value
     * @param liveTime
     *            单位秒
     */
    void set(String key, String value, long liveTime);

    /**
     * 添加key value
     * 
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * 添加key value (字节)(序列化)
     * 
     * @param key
     * @param value
     */
    void set(byte[] key, byte[] value);

    /**
     * 获取redis value (String)
     * 
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 检查key是否已经存在
     * 
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * 清空redis 所有数据
     * 
     * @return
     */
    String flushDB();

    /**
     * 查看redis里有多少数据
     */
    long dbSize();

    /**
     * 检查是否连接成功
     * 
     * @return
     */
    String ping();
    
    
	/**
	 * 设置一个对象 可以是string 
	 * @param key
	 * @param obj
	 * @param clazz 
	 * @param seconds 超时时间 0 代表永不过期
	 */
	<T> void set(final String key, final T obj, final long seconds);

	/**
	 * 设置一个对象 可以是string
	 * @param key
	 * @param obj
	 * @param clazz
	 */
	<T> T get(final String key, Class<T> clazz);

	/**
	 * 取一个对象
	 */
	<T> void set(final String key, final T obj);

    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
    String getCreditNumber(String key);
}
