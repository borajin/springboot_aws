package com.study.springbootaws.config.auth.dto;

import com.study.springbootaws.domain.user.Role;
import com.study.springbootaws.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/*
 * OAuth2UserService 를 통해 가져온 OAuth2User 의 attributes 를 담는 클래스
 */

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    //얻은 attributes 를 OAuthAttributes 로 변환. Map 형태기 때문에 하나하나 변환해야함.
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name(attributes.get("name").toString())
                .email(attributes.get("email").toString())
                .picture(attributes.get("picture").toString())
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .picture(this.picture)
                .role(Role.GUEST)
                .build();
    }
}
