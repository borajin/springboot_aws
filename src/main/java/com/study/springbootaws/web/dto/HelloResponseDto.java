package com.study.springbootaws.web.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter     //모든 필드 getter 메소드 생성
@RequiredArgsConstructor    //final 이나 @NonNull 값인 모든 필드를 파라미터로 받는 생성자 생성
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
