package com.FashionblogMbadady.FashionBlog.dto.response;

public class JwtAuthResponseDto {
    private String accessKey;
    private String tokenType = "Bearer";

    public JwtAuthResponseDto(String accessKey) {
        this.accessKey = accessKey;
    }

    public JwtAuthResponseDto(String accessKey, String tokenType) {
        this.accessKey = accessKey;
        this.tokenType = tokenType;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

//    private String accessToken;
//    private String tokenType;
//
//    public JwtAuthResponseDto(String accessToken, String tokenType) {
//        this.accessToken = accessToken;
//        this.tokenType = tokenType;
//    }
//
//    public String getAccessToken() {
//        return accessToken;
//    }
//
//    public void setAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }
//
//    public String getTokenType() {
//        return tokenType;
//    }
//
//    public void setTokenType(String tokenType) {
//        this.tokenType = tokenType;
//    }
}


