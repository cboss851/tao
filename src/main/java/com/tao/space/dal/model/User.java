package com.tao.space.dal.model;

import lombok.Data;

/**
 * @Author：cboss
 * @Date：2023/4/1
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
