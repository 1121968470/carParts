package com.carmold.mybatis.supports;

import com.carmold.mybatis.BaseEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linxinqiang
 * @date 19-7-31
 */
public class EnumUtils {

    private static final String key_code = "code";
    private static final String key_title = "title";
    private static final String key_name = "name";


    public static List<Map<String, Object>> nameTitleMaps(BaseEnum... baseEnums) {
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        for (BaseEnum baseEnum : baseEnums) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(key_title, baseEnum.getTitle());
            map.put(key_name, baseEnum.getName());
            maps.add(map);
        }
        return maps;
    }

    public static List<Map<String, Object>> nameMaps(BaseEnum... baseEnums) {
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        for (BaseEnum baseEnum : baseEnums) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(key_name, baseEnum.getName());
            maps.add(map);
        }
        return maps;
    }


    public static List<Map<String, Object>> titleMaps(BaseEnum... baseEnums) {
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        for (BaseEnum baseEnum : baseEnums) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(key_title, baseEnum.getTitle());
            maps.add(map);
        }
        return maps;
    }


    public static List<Map<String, Object>> maps(BaseEnum... baseEnums) {
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        for (BaseEnum baseEnum : baseEnums) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(key_code, baseEnum.getCode());
            map.put(key_title, baseEnum.getTitle());
            map.put(key_name, baseEnum.getName());
            maps.add(map);
        }
        return maps;
    }


    public static <T extends BaseEnum> List<Map<String, Object>> nameMaps(Class<T> clazz) {
        if (!clazz.isEnum()) {
            return null;
        }
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        T[] ts = (T[]) clazz.getEnumConstants();
        for (T t : ts) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(key_name, t.getName());
            maps.add(map);
        }
        return maps;
    }

    public static <T extends BaseEnum> List<Map<String, Object>> titleMaps(Class<T> clazz) {
        if (!clazz.isEnum()) {
            return null;
        }
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        T[] ts = (T[]) clazz.getEnumConstants();
        for (T t : ts) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(key_title, t.getTitle());
            maps.add(map);
        }
        return maps;
    }

    public static <T extends BaseEnum> List<Map<String, Object>> nameTitleMaps(Class<T> clazz) {
        if (!clazz.isEnum()) {
            return null;
        }
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        T[] ts = (T[]) clazz.getEnumConstants();
        for (T t : ts) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(key_title, t.getTitle());
            map.put(key_name, t.getName());
            maps.add(map);
        }
        return maps;
    }

    public static <T extends BaseEnum> List<Map<String, Object>> maps(Class<T> clazz) {
        if (!clazz.isEnum()) {
            return null;
        }
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        T[] ts = (T[]) clazz.getEnumConstants();
        for (T t : ts) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(key_code, t.getCode());
            map.put(key_title, t.getTitle());
            map.put(key_name, t.getName());
            maps.add(map);
        }
        return maps;
    }
}
