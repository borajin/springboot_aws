package com.study.springbootaws.config.auth;

import com.study.springbootaws.config.auth.dto.OAuthAttributes;
import com.study.springbootaws.config.auth.dto.SessionUser;
import com.study.springbootaws.domain.user.User;
import com.study.springbootaws.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;


/*
 * 구글 로그인 이후 가져온 사용자의 정보를 기반으로
 * 가입, 정보수정, 세션 저장 등의 기능을 지원하는 클래스
 */
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //registraionId : 현재 로그인 진행중인 서비스 구분하는 코드. 구글인지 네이버 로그인인지 카카오인지 구분함.
        String registraionId = userRequest.getClientRegistration().getRegistrationId();
        //userNameAttrbuteName : OAuth2 로그인 시 key 가 되는 필드값. 구글의 기본 코드는 'sub' 이고 네이버, 카카오 등은 기본 지원 x. 이후 네이버, 카카오를 동시 지원할 때 필요함.
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        //OAuth2User 의 attribute 를 담는 클래스.
        OAuthAttributes attributes = OAuthAttributes.of(registraionId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));    //SettionUser : user entity 의 직렬화 dto

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }

}
