package com.its.library.config.oauth.provider;

public interface Oauth2UserInfo {
    String getProviderId();

    String getProvider();

    String getEmail();

    String getName();
}
