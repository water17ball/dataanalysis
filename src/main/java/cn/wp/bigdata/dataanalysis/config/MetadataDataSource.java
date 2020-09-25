package cn.wp.bigdata.dataanalysis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.xa.DruidXADataSource;
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
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import javax.sql.XADataSource;
import java.util.Properties;

/**
 * 元数据 datasource
 */
@Configuration
@ConfigurationProperties(prefix = "multi-datasource.metadata.mybatis")
@MapperScan(basePackages = "cn.enn.bigdata.dataanalysis.dao.metadata", sqlSessionTemplateRef = "metadata-sessionTemplate")
public class MetadataDataSource {

    @Setter
    private String mapperLocation;

    @Value("${multi-datasource.metadata.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${multi-datasource.metadata.datasource.url}")
    private String url;

    @Value("${multi-datasource.metadata.datasource.username}")
    private String username;

    @Value("${multi-datasource.metadata.datasource.password}")
    private String password;


    /**
     * 分页属性配置
     *
     * @return
     */
    @Bean(name = "metadata-pagehelper-properties")
    @ConfigurationProperties(prefix = "multi-datasource.metadata.pagehelper")
    public Properties getPageHelperProperties() {
        return new Properties();
    }

    @Bean(name = "metadata-datasource")
    @ConfigurationProperties(prefix = "multi-datasource.metadata.datasource")
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);

//        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
//        XADataSource xaDataSource  = new DruidXADataSource();
//        atomikosDataSourceBean.setXaDataSource(xaDataSource);
        return dataSource;
    }

    @Bean(name = "metadata-sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("metadata-datasource") DataSource dataSource,
                                                  @Qualifier("metadata-pagehelper-properties") Properties properties
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

    @Bean(name = "metadata-sessionTemplate")
    public SqlSessionTemplate getSessionTemplate(@Qualifier("metadata-sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }
}