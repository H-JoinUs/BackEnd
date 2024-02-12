package H.joinUs.dto.ResponseDto;

import lombok.*;

public class UserResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor(access= AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class BriefUserInfo {
        private Long userId;
        private String nickname;
        private String profileImg;
    }
}
