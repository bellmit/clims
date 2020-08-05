package com.bazl.clims.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroTime {
    public static Long time;

    @Value("${shTime}")
    public void setHuahua(Long shTime) {
        this.time = shTime;
        ShiroTime.time = shTime;
    }
}
