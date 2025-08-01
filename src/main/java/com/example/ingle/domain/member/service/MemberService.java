package com.example.ingle.domain.member.service;

import com.example.ingle.domain.member.Member;
import com.example.ingle.domain.member.dto.req.UpdateMemberRequestDto;
import com.example.ingle.domain.member.dto.res.MyPageResponseDto;
import com.example.ingle.domain.member.repository.MemberRepository;
import com.example.ingle.global.exception.CustomException;
import com.example.ingle.global.exception.ErrorCode;
import com.example.ingle.global.jwt.MemberDetail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MyPageResponseDto getMyPage(MemberDetail memberDetail) {

        Member loginMember = memberDetail.getMember();

        log.info("[마이페이지 조회 요청] 회원 ID: {}", loginMember.getId());

        Member member = memberRepository.findById(loginMember.getId())
                .orElseThrow(() -> {
                    log.warn("[마이페이지 조회 실패] 회원 ID: {}", loginMember.getId());
                    return new CustomException(ErrorCode.MEMBER_NOT_FOUND);
                });

        log.info("[마이페이지 조회 성공] 회원 ID: {}", member.getId());

        return MyPageResponseDto.builder().member(member).build();
    }

    @Transactional
    public MyPageResponseDto updateMyPage(MemberDetail memberDetail, @Valid UpdateMemberRequestDto updateMemberRequestDto) {

        Member loginMember = memberDetail.getMember();

        log.info("[마이페이지 수정 요청] 회원 ID: {}", loginMember.getId());

        Member member = memberRepository.findById(loginMember.getId())
                .orElseThrow(() -> {
                    log.warn("[마이페이지 수정 실패] 회원 ID: {}", loginMember.getId());
                    return new CustomException(ErrorCode.MEMBER_NOT_FOUND);
                });

        member.updateMember(updateMemberRequestDto);

        log.info("[마이페이지 수정 성공] 회원 ID: {}", member.getId());

        return MyPageResponseDto.builder().member(member).build();
    }
}
