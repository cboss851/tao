package com.tao.space;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

/**
 * @Author：cboss
 * @Date：2023/4/1
 */
@Slf4j
public class GeneratorCode {
    public static void main(String[] args) {
        log.info("GeneratorCode start");
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis_example?useUnicode=true&allowMultiQueries=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false&maxReconnects=10&useSSL=false&serverTimezone=Asia/Shanghai&&allowPublicKeyRetrieval=true",
                        "root", "ea8dc0d1fe7e4073ad0296ea2eacc651")
                .globalConfig(builder -> {
                    builder.author("cboss") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\workspace\\my\\tao\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.tao.space") // 设置父包名
                            .moduleName("example") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\workspace\\my\\tao\\src\\main\\java")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("example") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}