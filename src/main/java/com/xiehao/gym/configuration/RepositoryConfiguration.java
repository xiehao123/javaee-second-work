package com.xiehao.gym.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.xiehao.gym.domain"})
@EnableJpaRepositories(basePackages = {"com.xiehao.gym.repository","com.xiehao.gym.Dao"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
