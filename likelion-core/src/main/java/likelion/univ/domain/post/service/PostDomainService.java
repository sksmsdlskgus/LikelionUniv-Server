package likelion.univ.domain.post.service;

import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.dto.request.PostCreateServiceDto;
import likelion.univ.domain.post.dto.request.PostDeleteServiceDto;
import likelion.univ.domain.post.dto.request.PostUpdateServiceDto;
import likelion.univ.domain.post.dto.response.PostCommandResponseDto;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import likelion.univ.domain.post.exception.PostNoAuthorizationException;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostDomainService {

    private final PostAdaptor postAdaptor;
    private final UserAdaptor userAdaptor;


    public PostCommandResponseDto createPost(PostCreateServiceDto request) {
        Post post = createEntity(request);
        Long savedId = postAdaptor.save(post);
        return PostCommandResponseDto.builder()
                .postId(savedId)
                .build();
    }

    public PostCommandResponseDto editPost(PostUpdateServiceDto request) {
        Post post = postAdaptor.findById(request.getPostId());
        if (!(post.getAuthor().getId().equals(request.getLoginUserId()))) {
            throw new PostNoAuthorizationException();
        }
        post.edit(request);
        Long savedId = postAdaptor.save(post);
        return PostCommandResponseDto.builder()
                .postId(savedId)
                .build();
    }
    public void deletePost(PostDeleteServiceDto request) {
        Post post = postAdaptor.findById(request.getPostId());
        if (!(post.getAuthor().getId().equals(request.getLoginUserId()))) {
            throw new PostNoAuthorizationException();
        }
        postAdaptor.delete(post);
    }

    private Post createEntity(PostCreateServiceDto request) {
        return  Post.builder()
                .author(userAdaptor.findById(request.getAuthorId()))
                .title(request.getTitle())
                .body(request.getBody())
                .thumbnail(request.getThumbnail())
                .mainCategory(MainCategory.valueOf(request.getMainCategory()))
                .subCategory(SubCategory.valueOf(request.getSubCategory()))
                .build();
    }

}
