package likelion.univ.domain.like.postlike.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostLikeDeleteServiceDto {
    private Long postLikeId;
    private Long loginUserId;

}
