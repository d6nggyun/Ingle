package com.example.ingle.domain.member.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtTokenRequestDto {

    @NotBlank(message = "Refresh Token이 비어있습니다.")
    @Schema(description = "Refresh Token", example = "qwerqwesdhfaifo")
    private String refreshToken;
}
