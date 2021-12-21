package com.study.springbootaws.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void lombokTest() {
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //asseartThat 은 메소드 인자로 받은 value 비교 및 검증해줌.
        //isEqualTo 말고도 greaterThan, lessThan, hasItem, instanceOf... 등의 체이닝도 가능.
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
