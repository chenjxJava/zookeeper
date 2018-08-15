package com.chenjx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * create by chenjx 2018/8/15
 */
@ConfigurationProperties(prefix = "zookeeper")
@Component
public class ZkConfig {
    // zk服务器地址
    private String connectString;

    //
    private int sessionTimeout;

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }
}
