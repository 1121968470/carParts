package com.carmold.mybatis;

import com.github.pagehelper.PageInterceptor;
import com.carmold.mybatis.handler.DefaultEnumTypeHandler;
import com.carmold.mybatis.plugins.generator.UUIDInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * @author linxinqiang
 * @date 19-6-27
 */
@org.springframework.context.annotation.Configuration
@MapperScan(basePackages = "com.carmold.**.dao")
@ConditionalOnClass({SqlSessionFactoryBean.class})
public class MybatisConfiguration {


    UUIDInterceptor uuidInterceptor() {
        return new UUIDInterceptor();
    }


    PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("dialect", "com.github.pagehelper.PageHelper");
        // 默认值为false，该参数对使用 RowBounds 作为分页参数时有效。当该参数设置为 true时，会将RowBounds中的 offset 参数当成 pageNum 使用，可以用页码和页面大小两个参数进行分页。 -->
        properties.setProperty("offsetAsPageNum", "false");
        //默认值为false，该参数对使用 RowBounds 作为分页参数时有效。当该参数设置为true时，使用 RowBounds分页会进行 ount 查询。
        properties.setProperty("rowBoundsWithCount", "true");
        //默认值为false，当该参数设置为true 时，如果 pageSize = 0或者 RowBounds.limit =0就会查询出全部的结果（相当于没有执行分页查询，但是返回结果仍然是Page 类型）。
        properties.setProperty("pageSizeZero", "false");
        //分页合理化参数，默认值为false。当该参数设置为 true 时，pageNum<=0时会查询第一页，pageNum>pages（超过总数时），会查询最后一页。默认false时，直接根据参数进行查询。
        properties.setProperty("reasonable", "false");
        //默认值为 true。当使用运行时动态数据源或没有设置 helperDialect属性自动获取数据库类型时，会自动获取一个数据库连接，通过该属性来设置是否关闭获取的这个连接，默认true关闭，设置为 false 后，不会关闭获取的连接，这个参数的设置要根据自己选择的数据源来决定。
        // properties.setProperty("closeConn", "true");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    Configuration configure() {
        Configuration configuration = new Configuration();
        configuration.setCacheEnabled(true);
        configuration.setLazyLoadingEnabled(true);
        configuration.setMultipleResultSetsEnabled(true);
        configuration.setUseColumnLabel(true);
        configuration.setUseGeneratedKeys(false);
        configuration.setAutoMappingBehavior(AutoMappingBehavior.PARTIAL);
        configuration.setDefaultExecutorType(ExecutorType.SIMPLE);
        configuration.setDefaultStatementTimeout(25);
        configuration.setSafeResultHandlerEnabled(false);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLocalCacheScope(LocalCacheScope.SESSION);
        configuration.setJdbcTypeForNull(JdbcType.OTHER);
        configuration.getTypeHandlerRegistry().setDefaultEnumTypeHandler(DefaultEnumTypeHandler.class);
        Set<String> methods = new HashSet<String>();
        methods.add("equals");
        methods.add("clone");
        methods.add("hashCode");
        methods.add("toString");
        configuration.setLazyLoadTriggerMethods(methods);
        return configuration;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(@Autowired DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfiguration(this.configure());
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.carmold.**.bean");
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{uuidInterceptor(), pageInterceptor()});
        return sqlSessionFactoryBean;
    }
}
