package com.pre_38.pre_project.member.service;

import com.pre_38.pre_project.member.dto.MemberLoginDto;
import com.pre_38.pre_project.member.dto.MemberSignupRequestDto;
import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Member signup(MemberSignupRequestDto signupDto) {
        return null;
    }

    public Map<String, Object> login(MemberLoginDto loginDto) {
        return null;
    }
    //멤버 찾기용
    public Member findMember(String memberName){
        return null;
    }
}
