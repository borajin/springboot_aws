package com.study.springbootaws.web.dto;

import com.study.springbootaws.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //resquest dto 로 받은 Posts 객체를 entity 화하여 저장하는 용도
    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).build();
    }
}