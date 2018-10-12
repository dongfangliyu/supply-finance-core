package cn.fintecher.supply.finance.loan.manager.common.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * @author huanglei
 */
public class GsonUtil {

    /**
     * 将json字符串转化成JsonObject对象
     * @param str
     * @return
     */
    public static JsonObject parseJsonStr(String str){
        JsonObject jsonObject = null;
        JsonParser jsonParser = new JsonParser();
        if(!ChkUtil.isEmpty(str)){
            jsonObject = jsonParser.parse(str).getAsJsonObject();
        }
        return jsonObject;
    }

    /**
     * 将对象转化成json字符串
     * @param obj
     * @return
     */
    public static String parseObjectToJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * 将json字符串转化成对象
     * @param jsonStr
     * @param cls
     * @return
     */
    public static <T> T parseJsonToObject(String jsonStr, Class<T> cls){
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, cls);
    }

    /**
     * 将json字符串转化成list
     * @param jsonStr
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> parseJsonToList(String jsonStr, Class<T> t){
        Gson gson = new Gson();
        List<T> objList = gson.fromJson(jsonStr, new TypeToken<List<T>>(){}.getType());
        return objList;
    }

}
