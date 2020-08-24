package com.bosssoft.ecds.controller;

import com.bosssoft.ecds.util.JsonUtils;
import com.bosssoft.ecds.entity.vo.WriteOffResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hujierong
 * @date 2020-8-24
 */
@RestController
public class TestController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/redis/save")
    public void save() {
//        User user = new User();
//        user.setId("1001");
//        user.setName("老王");
//        user.setAge(23);
//        redisTemplate.opsForValue().set(user.getId(), JsonUtils.objectToJson(user));
        WriteOffResultVO writeOffResultVO = new WriteOffResultVO();
        writeOffResultVO.setfAgenIdCode("1");
        writeOffResultVO.setfNo("1001");
        writeOffResultVO.setRes("pass");
        redisTemplate.opsForValue().set(writeOffResultVO.getfAgenIdCode()+writeOffResultVO.getfNo(), JsonUtils.objectToJson(writeOffResultVO));
    }

    @PostMapping("/redis/get")
    public WriteOffResultVO get(){
        return JsonUtils.jsonToPojo(redisTemplate.opsForValue().get("11001"), WriteOffResultVO.class);
    }

    class User {
        private String id;
        private String name;
        private int age;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
