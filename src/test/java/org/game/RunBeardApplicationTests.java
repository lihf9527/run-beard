package org.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RunBeardApplicationTests {
//    @Autowired
//    private ValueOperations<String, String> valueOperations;

    @Test
    public void contextLoads() {

    }

    @Test
    public void redisTest() {
//        valueOperations.set("hello", "你好", 1, TimeUnit.MINUTES);
//        System.out.println(valueOperations.get("hello"));
    }

    public static void main(String[] args) {
        String token = "Bearer eyJ1c2VyTmFtZSI6";
        System.out.println(token.replaceFirst("[B|b][E|e][A|a][R|r][E|e][R|r]", "").trim());
    }

}

