package H.joinUs.dto.ResponseDto;

import H.joinUs.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;

public class CommentResponseDto {
    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateComment {
        private Long id;
        private String content;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateComment {
        private Long id;
        private String content;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class GetComment {
        private Long id;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private UserResponseDto.BriefUserInfo user;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateRecomment {
        private Long id;
        private String content;
    }
}
