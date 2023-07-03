package qidong.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "qidong.mapper.MysqlMapper",sqlSessionFactoryRef = "MysqlSqlSessionFactory")
public class MysqlSourceConfig {
    static final String MAPPER_LOCATION = "classpath:mapper/MysqlMapper/*.xml";
    /**
     * 创建DataSource
     * @return
     */
    @Primary
    @Bean("MysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.mysql")
    public DataSource getDb1DataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建sqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Primary
    @Bean("MysqlSqlSessionFactory")
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("MysqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return bean.getObject();
    }

//    /**
//     * 创建事务管理器
//     * @param dataSource
//     * @return
//     */
//    @Bean(name = "MysqlTransactionManager")
//    public DataSourceTransactionManager orderTransactionManager(@Qualifier("MysqlDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

    /**
     * 创建sqlSession模板
     * @param sqlSessionFactory
     * @return
     */
    @Primary
    @Bean("MysqlSqlSessionTemplate")
    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("MysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

