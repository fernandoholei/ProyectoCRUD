package com.mycompany.crud.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.mycompany.crud")
@EnableTransactionManagement
public class AppConfig {
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/database");
        ds.setUsername("fho");
        ds.setPassword("Digitech2025$");
        return ds;
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.mycompany.crud.modelo");
        factory.setHibernateProperties(hibernateProperties());
        return factory;
    }

    private Properties hibernateProperties() {
            
        Properties prop = new Properties();
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.hbm2ddl.auto", "update");
        return prop;
    }
    
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}