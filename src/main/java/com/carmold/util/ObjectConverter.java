package com.carmold.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ObjectConverter {
    private static Log log = LogFactory.getLog(ObjectConverter.class);


    public static <T> T convert(Object persistence, Class<T> class1) {
        T newInstance = null;
        if (persistence != null) {
            try {
                newInstance = class1.newInstance();
                BeanUtils.copyProperties(persistence, newInstance);
            } catch (Exception e) {
                log.error("error", e);
            }
        }
        return newInstance;
    }

    public static <T> List<T> convertList(List<?> objects, Class<T> class1) {
        List<T> pojos = null;
        if (objects != null) {
            pojos = new ArrayList<T>();
            for (Object obj : objects) {
                T object = convert(obj, class1);
                if (object != null) {
                    pojos.add(object);
                }
            }
        }
        return pojos;
    }

}
