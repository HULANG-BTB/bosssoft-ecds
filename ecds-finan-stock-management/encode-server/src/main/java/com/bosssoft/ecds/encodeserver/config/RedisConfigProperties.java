package com.bosssoft.ecds.encodeserver.config;

import java.util.List;

/**
 * @Author 黄杰峰
 * @Date 2020/8/11 0011 11:08
 * @Description
 */
//@Component
//@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigProperties {
    private String password;
    private cluster cluster;

    public static class cluster {
        private List<String> nodes;

        public List<String> getNodes() {
            return nodes;
        }

        public void setNodes(List<String> nodes) {
            this.nodes = nodes;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RedisConfigProperties.cluster getCluster() {
        return cluster;
    }

    public void setCluster(RedisConfigProperties.cluster cluster) {
        this.cluster = cluster;
    }
}
