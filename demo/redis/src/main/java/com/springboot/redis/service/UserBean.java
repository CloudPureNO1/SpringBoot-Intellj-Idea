package com.springboot.redis.service;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserBean implements java.io.Serializable {
    private Long Id;
    private String userName;
    private Integer age;
    private String passwd;
}
