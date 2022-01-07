package com.study.springbootaws.config.auth.dto;

import com.study.springbootaws.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/*
 * 세션에 저장하기 위해서는 직렬화가 필요하다.
 * User 클래스는 Jpa Entity 클래스이기 때문에 다른 Entity 와 연계될 수도 있다.
 * OneToMany, ManyToMany 등 자식 클래스를 갖게 되면 직렬화 대상에 자식 클래스도 포함되므로
 * 직렬화 기능을 가진 Dto 를 따로 만드는 게 유지보수 및 운영에 있어서 좋다.
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    //User entity 정보를 SettionUser 에 담습니다.
    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
