package H.joinUs.dto.RequestDto;

import lombok.*;

public class PostRequestDto {
    @Builder
    @Getter
    @AllArgsConstructor(access= AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreatePost {
        private String title;
        private String content;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access= AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdatePost {
        private String title;
        private String content;
    }


}