package com.study.springbootaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  //jpa auditing 활성화
@SpringBootApplication  //프로젝트의 시작점. 스프링부트, Bean 읽기/생성 등 자동으로 설정해주는 어노테이션
public class Application {
    public static void main(String[] args) {
        //내장 WAS 실행
        SpringApplication.run(Application.class, args);
    }
}
