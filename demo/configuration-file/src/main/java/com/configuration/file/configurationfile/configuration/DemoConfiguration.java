package com.configuration.file.configurationfile.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DemoConfiguration {
    @Value("${com.demo.userName}")
    private String userName;

    @Value("${com.demo.msg}")
    private String msg;
    @Value("${com.demo.knowledge}")
    private String knowledge;
    @Value("${com.demo.bookName}")
    private String bookName;
    @Value("${com.demo.price}")
    private String price;
    @Value("${com.demo.author}")
    private String author;


    @Value("${com.demo.stringValue}")
    private  String stringValue;
    @Value("${com.demo.intValue}")
    private int intValue;
    @Value("${com.demo.longValue}")
    private long longValue;
    @Value("${com.demo.intRageValue}")
    private int intRageValue;
    @Value("${com.demo.intRageValue2}")
    private int getIntRageValue2;


    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public long getLongValue() {
        return longValue;
    }

    public void setLongValue(long longValue) {
        this.longValue = longValue;
    }

    public int getIntRageValue() {
        return intRageValue;
    }

    public void setIntRageValue(int intRageValue) {
        this.intRageValue = intRageValue;
    }

    public int getGetIntRageValue2() {
        return getIntRageValue2;
    }

    public void setGetIntRageValue2(int getIntRageValue2) {
        this.getIntRageValue2 = getIntRageValue2;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
