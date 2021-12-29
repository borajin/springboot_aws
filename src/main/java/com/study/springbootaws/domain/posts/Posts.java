package com.study.springbootaws.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor	//lombok. 기본 생성자 생성
@Entity	//JPA. 테이블과 링크될 클래스임을 나타냄
public class Posts {
    @Id	//PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)	//auto_increment
    private Long id;

    @Column(length = 500, nullable = false)	//table 의 column 설정. 굳이 어노테이션 안 달아도 나머지 필드들 자동으로 coloumn 됨. 별도의 설정 있을 경우 선언
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder	//lombok. 빌더 패턴 클래스 생성. 이렇게 생성자쪽에 붙여주면 생성자 필드만 빌더에 포함됨
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
