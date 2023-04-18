package com.tao.space;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

/**
 * @Author：cboss
 * @Date：2023/4/1
 */
public class AutoGeneratorTest {
    public static void main(String[] args) {
        DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
                .Builder("jdbc:mysql://localhost:3306/mybatis_example?useUnicode=true&allowMultiQueries=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false&maxReconnects=10&useSSL=false&serverTimezone=Asia/Shanghai&&allowPublicKeyRetrieval=true",
                "root", "ea8dc0d1fe7e4073ad0296ea2eacc651")
//                .schema("baomidou")
                .build();
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(new StrategyConfig.Builder().build());
        generator.global(new GlobalConfig.Builder().build());
        generator.execute();
    }
}
