package ru.easyjava.spring.data.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring root context configuration.
 */
@Configuration
@ComponentScan("ru.easyjava.spring.data.jpa")
@EnableJpaRepositories
@EnableTransactionManagement
public class ContextConfiguration {

    /**
     * Instantiate JPA bean.
     * @return emf wrapper bean.
     */
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean result =
                new LocalEntityManagerFactoryBean();
        result.setPersistenceUnitName("ru.easyjava.spring.data.jpa");
        return result;
    }

    /**
     * Instantiate transaction manager.
     * @return PlatformTransactionManager bean for JPA.
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager result = new JpaTransactionManager();
        result.setEntityManagerFactory(entityManagerFactory().getObject());
        return result;
    }

}
