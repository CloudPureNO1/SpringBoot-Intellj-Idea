package com.datasource.multi.druid.model.ds1;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Data
@Setter
@Getter
public class MyUsersModel implements java.io.Serializable{
    private Long userId;
    private String userName;
    private String userPwd;
}
