package com.chenjx.arms;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkUtilsTest {
    @Resource(name = "zk")
    private ZooKeeper zk;

    /**
     * 创建节点，根据HHmmss创建
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void create() throws KeeperException, InterruptedException {
        String hHmmss = new SimpleDateFormat("HHmmss").format(new Date());
        String node = new StringBuffer("/test").append(hHmmss).toString();
        String data = new StringBuffer("data:").append(hHmmss).toString();
        String path = zk.create(node, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
        Assert.assertNotNull(path);
    }

    /**
     * 根绝路径获取数据
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getData() throws KeeperException, InterruptedException {
        byte[] data = zk.getData("/test", null, new Stat());
        System.out.println(new String(data));
        Assert.assertNotNull(data);
    }

    @Test
    public void exist() throws KeeperException, InterruptedException {
        Stat exists = zk.exists("/test184747", false);
        if (exists == null) {
            System.out.println("不存在");
        } else {
            System.out.println("存在");
        }
    }

    @Test
    public void delete() throws KeeperException, InterruptedException {
        //第二个参数为version，-1表示删除所有版本
        //它不支持删除的节点下面还有子节点，只能递归删除
        zk.delete("/test184747", -1);
    }

    @Test
    public void setData() throws KeeperException, InterruptedException {
        //修改znode的值
        zk.setData("/test001", "modify data".getBytes(), -1);

        //测试是否修改成功
        System.out.println(new String(zk.getData("/test001", false, null)));
    }

    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> childrens = zk.getChildren("/", false);
        for (String children : childrens) {
            System.out.println("子节点:"+ children+ "\n");
        }
        //阻塞，测试监听器,且只监听"/"目录
        Thread.sleep(Long.MAX_VALUE);
    }

}