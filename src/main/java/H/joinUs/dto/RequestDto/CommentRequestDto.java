package H.joinUs.dto.RequestDto;

import H.joinUs.domain.User;
import lombok.*;

public class CommentRequestDto {
    @Builder
    @Getter
    @AllArgsConstructor(access= AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateComment {
        private String content;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access= AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateComment {
        private String content;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access= AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateRecomment {
        private String content;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access= AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateRecomment {
        private String content;
    }
}
