package com.study.springbootaws.service;

import com.study.springbootaws.domain.posts.Posts;
import com.study.springbootaws.domain.posts.PostsRepository;
import com.study.springbootaws.web.dto.PostsResponseDto;
import com.study.springbootaws.web.dto.PostsSaveRequestDto;
import com.study.springbootaws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor    //final or NonNull 옵션 필드를 전부 포함한 생성자 만들어줌
@Service
public class PostsService {
    //@Autowired 사용 지양됨 -> @RequiredArgsConstructor 로 생성되는 생성자로 주입받기 위해 final 붙임.
    private final PostsRepository postsRepository;

    @Transactional  //db 트랜잭션 자동으로 commit 해줌
    public Long save(PostsSaveRequestDto requestDto) {
        //dto 를 entity 화 해서 repository 의 save 메소드를 통해 db 에 저장.
        //저장 후 생성한 id 반환해줌
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        //JPA 의 영속성 컨텍스트 덕분에 entity 객체의 값만 변경하면 자동으로 변경사항 반영함!
        //따라서 repository.update 를 쓰지 않아도 됨.
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }
}
