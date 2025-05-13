package org.example.jspspring.config;

import org.example.jspspring.Controller.HelloServlet;
import org.example.jspspring.Controller.ProfController;
import org.example.jspspring.Repository.ProfRepo;
import org.example.jspspring.Repository.StudentRepoimpl;
import org.example.jspspring.Service.ProfService;
import org.example.jspspring.Service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.access.SecurityConfig;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.example.jspspring.Repository")
@ComponentScan(basePackages = "org.example.jspspring")
@Import(org.example.jspspring.security.Securing.class) // Import security configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/ng");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("org.example.jspspring.Model"); // Correct this case - "Model" not "model"

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        em.setJpaProperties(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
    @Bean
    public StudentService studentService(StudentRepoimpl studentRepo) {
        return new StudentService(studentRepo);
    }
    @Bean
    public HelloServlet helloServlet(StudentService studentService) {
        return new HelloServlet(studentService);
    }
    @Bean
    public ProfService profService(ProfRepo profRepo) {
        return new ProfService(profRepo);
    }
    @Bean
    public ProfController profController(ProfService profService) {
        return new ProfController(profService);
    }
}