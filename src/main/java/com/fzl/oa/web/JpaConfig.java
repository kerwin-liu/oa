package com.fzl.oa.web;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:conf/webapp.properties")
@PropertySource(value = "classpath:conf/dev.properties", ignoreResourceNotFound = true)
@EnableTransactionManagement
public class JpaConfig {

    public final static Logger log = LoggerFactory.getLogger(JpaConfig.class);

    @Autowired
    Environment env;


    @Bean(name="dataSource")
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getProperty("ds.jdbc.mysql.driver"));
        ds.setUrl(env.getProperty("ds.jdbc.mysql.url"));
        ds.setUsername(env.getProperty("ds.jdbc.mysql.username"));
        ds.setPassword(env.getProperty("ds.jdbc.mysql.password"));
        ds.setTestWhileIdle(true);
        ds.setValidationQuery(env.getProperty("ds.validate.sql"));
        ds.setTimeBetweenEvictionRunsMillis(60 * 1000L);
        return ds;

    }

    @Bean(name="jdbcTemplate")
    @Autowired
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource")DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="sessionFactory")
    @Autowired
    public LocalSessionFactoryBean sessionFactory(@Qualifier("dataSource") DataSource dataSource) {
        LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setPackagesToScan(com.fzl.oa.web.Package.class.getPackage().getName());
        bean.setHibernateProperties(hibernateProperties(env));
        return bean;
    }

    private Properties hibernateProperties(Environment environment) {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.mysql.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.connection.autocommit",
                environment.getProperty("hibernate.connection.autocommit"));
        hibernateProperties.setProperty("hibernate.connection.release_mode",
                environment.getProperty("hibernate.connection.release_mode"));
        // hibernateProperties.setProperty("hibernate.transaction.factory_class",
        // environment.getProperty("hibernate.transaction.factory_class"));
        return hibernateProperties;
    }


    @Bean(name="hibernateTemplate")
    @Autowired
    public HibernateTemplate hibernateTemplate(@Qualifier("sessionFactory")  SessionFactory sessionFactory) {
        HibernateTemplate hibernateTemplate = new HibernateTemplate();
        hibernateTemplate.setSessionFactory(sessionFactory);
        return hibernateTemplate;
    }


    /*
	 * 配置EntityManagerFactory
	 *
	 * @param dataSource
	 *
	 * @return
	 */
    @Bean(name="entityManagerFactory")
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactory.setJpaProperties(hibernateProperties(env));
        entityManagerFactory.setPackagesToScan(com.fzl.oa.web.Package.class.getPackage().getName());
        return entityManagerFactory;
    }

    @Bean(name = "transactionManager")
    @Autowired
    public JpaTransactionManager transactionManagerPostgres(@Qualifier("entityManagerFactory")EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManagerPostgres = new JpaTransactionManager();
        txManagerPostgres.setEntityManagerFactory(entityManagerFactory);
        return txManagerPostgres;
    }

    public HibernateJpaVendorAdapter jpaVendorAdapter() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }
}
