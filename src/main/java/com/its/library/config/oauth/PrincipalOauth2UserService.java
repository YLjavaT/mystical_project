package com.its.library.config.oauth;

import com.its.library.config.auth.PrincipalDetails;
import com.its.library.config.oauth.provider.*;
import com.its.library.dto.MemberDTO;
import com.its.library.entity.MemberEntity;
import com.its.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Oauth2UserInfo oauth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            oauth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            oauth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            oauth2UserInfo = new NaverUserInfo((Map) oAuth2User.getAttributes().get("response"));
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
            oauth2UserInfo = new KaKaoUserInfo((Map) oAuth2User.getAttributes().get("kakao_account"));
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("github")) {
            oauth2UserInfo = new GithubUserInfo((Map) oAuth2User.getAttributes());
        } else {
            System.out.println("?????? ?????? ????????? ?????????.");
        }

        String provider = oauth2UserInfo.getProvider();
//        String providerId = oauth2UserInfo.getProviderId();

        String loginId = oauth2UserInfo.getName() + oauth2UserInfo.getEmail();
        String password = "";
        String email = oauth2UserInfo.getEmail();
        String role = "USER";
        String memberName = oauth2UserInfo.getName() + oauth2UserInfo.getEmail();

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByLoginId(loginId);
        MemberDTO memberDTO = new MemberDTO();
        if (optionalMemberEntity.isEmpty()) {
            // ???????????? ?????? ??????
            memberDTO = MemberDTO.builder()
                    .loginId(loginId)
                    .memberEmail(email)
                    .memberPassword(password)
                    .memberName(memberName)
                    .memberPoint(500)
                    .role(role)
                    .provider(provider)
                    .build();
            MemberEntity saveEntity = MemberEntity.saveEntity(memberDTO);
            memberRepository.save(saveEntity);
        } else {
            MemberEntity memberEntity = optionalMemberEntity.get();
            memberDTO = MemberDTO.toDTO(memberEntity);
        }
        return new PrincipalDetails(memberDTO, oAuth2User.getAttributes());

    }
}
