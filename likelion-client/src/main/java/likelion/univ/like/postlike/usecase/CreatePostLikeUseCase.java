package likelion.univ.like.postlike.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.like.postlike.dto.PostLikeCreateServiceDto;
import likelion.univ.domain.like.postlike.dto.PostLikeResponseDto;
import likelion.univ.domain.like.postlike.service.PostLikeDomainService;
import likelion.univ.like.postlike.dto.PostLikeRequestDto;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreatePostLikeUseCase {
    private final PostLikeDomainService postLikeDomainService;
    private final AuthentiatedUserUtils userUtils;

    public PostLikeResponseDto execute(PostLikeRequestDto postIdDto) {
        return postLikeDomainService.createLikePost(serviceDtoBy(postIdDto));
    }

    private PostLikeCreateServiceDto serviceDtoBy(PostLikeRequestDto postIdDto) {
        return PostLikeCreateServiceDto.builder()
                .postId(postIdDto.getPostId())
                .authorId(userUtils.getCurrentUserId())
                .build();
    }


}
