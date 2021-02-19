package com.carmold.mybatis.plugins.generator;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class UUIDInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object[] args = invocation.getArgs();
        if (args == null || args.length != 2 || !(args[0] instanceof MappedStatement)) {
            return invocation.proceed();
        }
        MappedStatement mappedStatement = (MappedStatement) args[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (!SqlCommandType.INSERT.equals(sqlCommandType)) {
            return invocation.proceed();
        }
        setDefultProperty(args[1]);
        return invocation.proceed();
    }

    public void setDefultProperty(Object obj) {
        // 批量插入
        if (obj instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) obj;
            Set<String> keys = map.keySet();
            for (String key : keys) {
                List<Object> list = (List<Object>) map.get(key);
                for (Object object : list) {
                    setObjectProperty(object);
                }
                break;
            }
            return;
        }
        setObjectProperty(obj);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("init UUIDInterceptor");
    }

    private void setObjectProperty(Object obj) {
        PropertySet propertySet = new PropertySet(obj.getClass());
        Set<ProPertyStrategyMapper> propers = propertySet.getPropertys();
        if (propers == null || propers.isEmpty()) {
            return;

        }
        for (ProPertyStrategyMapper pro : propers) {
            try {
                Field findField = ReflectionUtils.findField(obj.getClass(), pro.getPropertyName());
                findField.setAccessible(true);
                //findField.set(obj, pro.getGenerator().generator());
                ReflectionUtils.setField(findField, obj, pro.getGenerator().generator());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
