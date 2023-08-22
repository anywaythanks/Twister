package com.github.anywaythanks.twisterresource.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Properties;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Configuration
public class HibernateConfig {
    @Target({CONSTRUCTOR, FIELD,
            METHOD, TYPE, PARAMETER})
    @Retention(RUNTIME)
    @Qualifier
    @interface HibernateProps {

    }

    @Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, @HibernateProps Properties props) {
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(dataSource);
        sfb.setPackagesToScan("com.github");
        sfb.setHibernateProperties(props);
        return sfb;
    }

    @Profile({"development", "qa"})
    @HibernateProps
    @Bean
    public Properties propertiesDevQA() {
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        props.setProperty("hibernate.use_sql_comments", "true");
       props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        return props;
    }

    @Profile("production")
    @HibernateProps
    @Bean
    public Properties propertiesProd() {
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return props;
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
