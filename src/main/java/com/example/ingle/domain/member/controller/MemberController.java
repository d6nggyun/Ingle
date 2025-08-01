package com.example.ingle.domain.member.controller;

import com.example.ingle.domain.member.dto.req.UpdateMemberRequestDto;
import com.example.ingle.domain.member.dto.res.MyPageResponseDto;
import com.example.ingle.domain.member.service.MemberService;
import com.example.ingle.global.jwt.MemberDetail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController implements MemberApiSpecification{

    private final MemberService memberService;

    // 마이페이지 조회
    @GetMapping
    public ResponseEntity<MyPageResponseDto> getMyPage(@AuthenticationPrincipal MemberDetail memberDetail) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMyPage(memberDetail));
    }

    // 마이페이지 수정
    @PutMapping
    public ResponseEntity<MyPageResponseDto> updateMyPage(
            @AuthenticationPrincipal MemberDetail memberDetail,
            @Valid @RequestBody UpdateMemberRequestDto updateMemberRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.updateMyPage(memberDetail, updateMemberRequestDto));
    }
}
