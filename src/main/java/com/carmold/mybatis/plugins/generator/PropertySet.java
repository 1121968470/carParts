package com.carmold.mybatis.plugins.generator;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class PropertySet {

    private Set<ProPertyStrategyMapper> propertys = new HashSet<ProPertyStrategyMapper>();

    private Class<?> entity;

    @SuppressWarnings("unused")
    private PropertySet() {

    }

    public PropertySet(Class<?> entity) {
        this.entity = entity;
        this.build();
    }

    public Set<ProPertyStrategyMapper> getPropertys() {
        return propertys;
    }

    public void setPropertys(Set<ProPertyStrategyMapper> propertys) {
        this.propertys = propertys;
    }

    public PropertySet build() {
        Field[] fields = entity.getDeclaredFields();
        for (Field field : fields) {
            if ("serialVersionUID".equals(field.getName()))
                continue;

            //注解在属性上
            if (field.isAnnotationPresent(Id.class)) {
                Id id = field.getAnnotation(Id.class);
                if (id.strategy() == null) {
                    continue;
                }
                Class<?> generator = id.strategy();
                Object object = null;
                try {
                    object = generator.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (!(object instanceof IdGenerator)) {
                    continue;
                }
                IdGenerator idGenerator = (IdGenerator) object;
                ProPertyStrategyMapper proPertyStrategyMapper = new ProPertyStrategyMapper(field.getName(),
                        idGenerator);
                propertys.add(proPertyStrategyMapper);
                break;
            }

            //注解在方法上
            PropertyDescriptor propertyDescriptor = null;
            try {
                propertyDescriptor = new PropertyDescriptor(field.getName(), entity);
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
            if (propertyDescriptor == null)
                continue;
            // 获取类的get方法
            Method method = propertyDescriptor.getReadMethod();
            if (method == null) {
                continue;
            }
            if (method.isAnnotationPresent(Id.class)) {
                Id id = method.getAnnotation(Id.class);
                if (id.strategy() == null) {
                    continue;
                }
                Class<?> generator = id.strategy();
                Object object = null;
                try {
                    object = generator.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (!(object instanceof IdGenerator)) {
                    continue;
                }
                IdGenerator idGenerator = (IdGenerator) object;
                ProPertyStrategyMapper proPertyStrategyMapper = new ProPertyStrategyMapper(field.getName(),
                        idGenerator);
                propertys.add(proPertyStrategyMapper);
                break;
            }
        }
        return this;
    }
}
