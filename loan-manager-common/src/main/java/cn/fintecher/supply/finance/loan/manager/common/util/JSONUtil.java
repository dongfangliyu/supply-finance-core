package cn.fintecher.supply.finance.loan.manager.common.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class JSONUtil {

	public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * JSON格式化时间的默认格式
	 */
	private static final DateFormat DEFAULT_DATETIME_FORMAT = new SimpleDateFormat(DATE_TIME_FORMAT);

	private static final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 设置对象的默认属性
	 */
	static {
		// 设置输出格式
		objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
				.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false)
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(Feature.WRITE_NUMBERS_AS_STRINGS, true);

		// 设置输出格式
		objectMapper.setSerializationInclusion(Include.NON_NULL);

		// 设置日期格式
		objectMapper.setDateFormat(DEFAULT_DATETIME_FORMAT);

//		// 设置支持的注解类型
//		AnnotationIntrospector introspector = AnnotationIntrospector.pair(
//				new JacksonAnnotationIntrospector(),
//				new JaxbAnnotationIntrospector(TypeFactory.defaultInstance())
//		);

//		objectMapper.setAnnotationIntrospector(introspector);
	}

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	/**
	 * 把带有注解的请求/响应对象转换成键值映射对象
	 *
	 * @param object 请求/响应对象
	 * @return 返回键值映射对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String, String> toMap(T object) {
		try {
			String content = objectMapper.writeValueAsString(object);

			return objectMapper.readValue(content, Map.class);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 把普通JAVABEAN对象，转换成JSON格式字符串
	 *
	 * @param object 转换的JAVABEAN对象
	 * @return 返回转换后的JSON格式字符串
	 */
	public static String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 把JSON格式字符串转换成对象
	 *
	 * @param json  JSON格式字符串
	 * @param clazz 类
	 * @return 返回对象
	 */
	public static <T> T toBean(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 把JSON格式字符串转换成对象
	 *
	 * @param obj   JSON格式字符串
	 * @param clazz 类
	 * @return 返回对象
	 */
	public static <T> T toBean(Object obj, Class<T> clazz) {
		try {
			return objectMapper.readValue(toJson(obj), clazz);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * map  转JavaBean
	 *
	 * @param map
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T mapToBean(Map map, Class<T> clazz) {
		return objectMapper.convertValue(map, clazz);
	}

	/**
	 * 把JSON格式字符串转换成集合
	 * @param json
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> toList(String json, Class<T> clazz) {
		JavaType javaType = getCollectionType(ArrayList.class, clazz);
		try {
			List<T> lst = (List<T>) objectMapper.readValue(json, javaType);
			return lst;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把JSON格式字符串转换成集合
	 * @param obj
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> toList(Object obj, Class<T> clazz) {
		JavaType javaType = getCollectionType(ArrayList.class, clazz);
		try {
			List<T> lst = (List<T>) objectMapper.readValue(toJson(obj), javaType);
			return lst;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取泛型的Collection Type
	 *
	 * @param collectionClass 泛型的Collection
	 * @param elementClasses  元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
	
}
