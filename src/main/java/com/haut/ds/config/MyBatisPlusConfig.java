package com.haut.ds.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;
import java.util.Date;

@Configuration
@EnableTransactionManagement
public class MyBatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor pii = new PaginationInnerInterceptor();
        pii.setDbType(DbType.MYSQL);
        pii.setMaxLimit(500L);
        interceptor.addInnerInterceptor(pii);
        return interceptor;
    }

    //MyBatisPlus自动填充字段功能
    @Bean
    public MetaObjectHandler metaObjectHandler() {

        return new MetaObjectHandler() {

            @Override
            public void insertFill(MetaObject metaObject) {

                // 数据类型要与fileName一致
                this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
                //this.strictInsertFill(metaObject, "createBy", String.class, "currentUser");

            }

            @Override
            public void updateFill(MetaObject metaObject) {

            }

        };
    }


}

