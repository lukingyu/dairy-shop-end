package com.haut.ds;

import com.haut.ds.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class DairyShopEndApplicationTests {

    @Test
    void testJwtUtil() {
        String jwtStr = JwtUtil.generateJwtStr("zhangsan");
        System.out.println(jwtStr);
        //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InpoYW5nc2FuIiwic3ViIjoibG9naW4tdGVzdCIsImV4cCI6MTcxMzI5Mzc5OCwianRpIjoiNjdhOTE5YjItYzQ4NC00YjA5LWJjYjctMzY2OTZjZGM2OTdiIn0.UOCDetIaSk2PjIy6OpUEt1L90aps1f1mNC0S95eZggg
    }
    @Test
    void testJwtParse(){
        String username = JwtUtil.parseAndGetUsername("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InpoYW5nc2FuIiwic3ViIjoibG9naW4tdGVzdCIsImV4cCI6MTcxMzI5Mzc5OCwianRpIjoiNjdhOTE5YjItYzQ4NC00YjA5LWJjYjctMzY2OTZjZGM2OTdiIn0.UOCDetIaSk2PjIy6OpUEt1L90aps1f1mNC0S95eZggg");
        System.out.println(username);
    }

}
