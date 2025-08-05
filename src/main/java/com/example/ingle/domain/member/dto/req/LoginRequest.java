package com.example.ingle.domain.member.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "로그인 요청 DTO")
public class LoginRequest {

    @NotBlank(message = "학번이 비어있습니다.")
    @Schema(description = "사용자의 학번", example = "202301452")
    private String studentId;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    @Schema(description = "사용자의 비밀번호", example = "password123")
    private String password;
}