package com.datasource.druid.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MyMsgModel implements  java.io.Serializable{
    private Long msgId;
    private String msgName;
    private String msgDetail;
}
