package likelion.univ.user.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.user.dto.response.UserPagePostsDto;
import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.like.postlike.adaptor.PostLikeAdaptor;
import likelion.univ.post.dao.PostCountInfoRedisDao;
import likelion.univ.post.entity.PostCountInfo;
import likelion.univ.post.service.PostCountInfoRedisService;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class GetPostsCommentedByMeUseCase {
    private final AuthentiatedUserUtils authentiatedUserUtils;
    private final PostAdaptor postAdaptor;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;

    public PageResponse<UserPagePostsDto> execute(Long userId, Pageable pageable){
        Long currentUserId = authentiatedUserUtils.getCurrentUserId();
        Page<Post> posts = postAdaptor.findByCommentAuthorId(userId, pageable);
        return PageResponse.of(posts.map(p-> UserPagePostsDto.of(p, currentUserId, getOrCreatePostCountInfoProcessor.execute(p.getId()))));
    }
}
