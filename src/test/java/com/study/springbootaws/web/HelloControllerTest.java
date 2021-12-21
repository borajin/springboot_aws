package com.study.springbootaws.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//Springboot의 모든 빈을 로드하지 않고 controller 관련 코드만 테스트할 때 사용. 단, Service, Component, Repository는 사용x
@WebMvcTest(HelloController.class)
public class HelloControllerTest {
    @Autowired  //Bean 자동 주입
    private MockMvc mvc;    //웹 API 테스트(GET, POST 등)시 사용. 스프링 MVC 테스트의 시작점

    @Test
    public void returnHello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))      // /hello 주소로 get 요청
                .andExpect(status().isOk())         //결과 중 HTTP Header 의 status code 검증.
                .andExpect(content().string(hello));    //결과 중 본문 검증.
    }

    @Test
    public void returnHelloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))      //$ => 루트, .name => ['name'] 하위요소
                .andExpect(jsonPath("$.amount").value(amount));
    }
}
