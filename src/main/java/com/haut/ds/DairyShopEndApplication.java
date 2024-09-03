package com.haut.ds;

import com.haut.ds.config.MyBatisPlusConfig;
import com.haut.ds.config.SpringConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan(basePackages = {"com.haut.ds.mapper"})
@Import({MyBatisPlusConfig.class, SpringConfig.class})
public class DairyShopEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(DairyShopEndApplication.class, args);
    }

}
