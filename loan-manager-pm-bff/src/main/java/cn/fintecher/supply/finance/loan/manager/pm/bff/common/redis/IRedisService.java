package cn.fintecher.supply.finance.loan.manager.pm.bff.common.redis;

public interface IRedisService {
    /**
     * 添加key value 并且设置存活时间
     * 
     * @param key
     * @param value
     * @param liveTime
     *            单位秒
     */
      void setString(String key, String value, long liveTime);


    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
    String getString(String key);

    /**
     * 设置一个对象 可以是string
     * @param key
     * @param obj
     * @param clazz
     */
     <T> T getObject(final String key, Class<T> clazz);

    /**
     * 取一个对象
     */
     <T> void setObject(final String key, final T obj);

}
