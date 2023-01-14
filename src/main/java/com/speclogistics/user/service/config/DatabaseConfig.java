package com.speclogistics.user.service.config;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
//@Configuration
public class DatabaseConfig extends AbstractR2dbcConfiguration {
    @Override
//    @Bean
    public ConnectionFactory connectionFactory() {
        return MySqlConnectionFactory.from(MySqlConnectionConfiguration.builder()
                .username("root")
                .password("root")
                .host("localhost")
                .port(3306)
                .database("speclogistics_userservice")
                .build());
    }

//    @Override
//    protected List<Object> getCustomConverters() {
//        return List.of(
//                new PostReadingConverter(),
//                new PostStatusWritingConverter()
//        );
//    }
}
