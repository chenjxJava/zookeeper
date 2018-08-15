package com.chenjx.utils;

import com.chenjx.config.ZkConfig;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * create by chenjx 2018/8/15
 */
@Component
public class ZkUtils {
    @Autowired
    private ZkConfig zkConfig;

    private ZooKeeper zk;

    @Bean("zk")
    public ZooKeeper getZookeeper() throws IOException {
        if(null != zk) {
            return zk;
        }
        zk = new ZooKeeper(zkConfig.getConnectString(), zkConfig.getSessionTimeout(), new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 收到watch通知后的回调函数
                System.out.println("事件类型:【" + event.getType() + "】，路径：【" + event.getPath()+ "】");

                //因为监听器只会监听一次，这样可以一直监听,且只监听"/"目录
                try {
                    zk.getChildren("/", true);
                } catch (Exception e) {
                    // TODO Auto-generated catch

                    e.printStackTrace();
                }
            }
        });
        return zk;
    }
}
