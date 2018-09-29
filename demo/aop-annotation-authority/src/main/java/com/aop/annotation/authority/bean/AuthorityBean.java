package com.aop.annotation.authority.bean;

import org.springframework.beans.factory.annotation.Configurable;

import java.util.Objects;

@Configurable
public class AuthorityBean implements  java.io.Serializable{
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AuthorityBean(String value) {
        this.value = value;
    }

    public AuthorityBean() {
    }

    @Override
    public String toString() {
        return "AuthorityBean{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityBean that = (AuthorityBean) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }
}
