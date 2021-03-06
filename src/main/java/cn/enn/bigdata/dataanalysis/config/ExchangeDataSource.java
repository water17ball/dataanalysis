package cn.enn.bigdata.dataanalysis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import lombok.Setter;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 本地 datasource
 */
@Configuration
@ConfigurationProperties(prefix = "multi-datasource.exchange.mybatis")
@MapperScan(basePackages = "cn.enn.bigdata.dataanalysis.dao.exchange", sqlSessionTemplateRef = "exchange-sessionTemplate")
public class ExchangeDataSource {

    @Setter
    private String mapperLocation;

    @Value("${multi-datasource.exchange.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${multi-datasource.exchange.datasource.url}")
    private String url;

    @Value("${multi-datasource.exchange.datasource.username}")
    private String username;

    @Value("${multi-datasource.exchange.datasource.password}")
    private String password;


    /**
     * 分页属性配置
     *
     * @return
     */
    @Bean(name = "exchange-pagehelper-properties")
    @ConfigurationProperties(prefix = "multi-datasource.exchange.pagehelper")
    public Properties getPageHelperProperties() {
        return new Properties();
    }

    @Bean(name = "exchange-datasource")
    @ConfigurationProperties(prefix = "multi-datasource.exchange.datasource")
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "exchange-sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("exchange-datasource") DataSource dataSource,
                                                  @Qualifier("exchange-pagehelper-properties") Properties properties
    ) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocation));

        // 分页插件
        Interceptor interceptor = new PageInterceptor();
        interceptor.setProperties(properties);
        bean.setPlugins(new Interceptor[]{interceptor});

        return bean.getObject();
    }

    @Bean(name = "exchange-sessionTemplate")
    public SqlSessionTemplate getSessionTemplate(@Qualifier("exchange-sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }
}