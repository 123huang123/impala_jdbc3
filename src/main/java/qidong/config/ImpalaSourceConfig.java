package qidong.config;

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
@MapperScan(basePackages = "qidong.mapper.ImpalaMapper",sqlSessionFactoryRef = "ImpalaSqlSessionFactory")
public class ImpalaSourceConfig {
    // mapper的xml形式文件位置必须要配置，不然将报错：no statement （这种错误也可能是mapper的xml中，namespace与项目的路径不一致导致）
    static final String MAPPER_LOCATION = "classpath:mapper/ImpalaMapper/*.xml";

    @Bean("ImpalaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.impala")//读取application.yml中的配置参数映射成为一个对象
    public DataSource getDb1DataSource(){
        return DataSourceBuilder.create().build();
    }



    @Bean("ImpalaSqlSessionFactory")
//    @Qualifier表示查找Spring容器中名字为test1DataSource的对象
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("ImpalaDataSource") DataSource dataSource) throws Exception {
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
//    @Bean(name = "ImpalaTransactionManager")
//    public DataSourceTransactionManager orderTransactionManager(@Qualifier("ImpalaDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }



    @Bean("ImpalaSqlSessionTemplate")
    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("ImpalaSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

