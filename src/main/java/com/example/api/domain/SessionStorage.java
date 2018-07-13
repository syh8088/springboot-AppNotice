package com.example.api.domain;

import com.example.api.constant.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 요청 세션 저장소 Domain
 *
 * @author
 */
@NoArgsConstructor
@Data
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "request")
public class SessionStorage {


    private String requestId;

    private String requestPath;

    private String requestIpAddress;

    private LocalDateTime requestDateTime;

    // 회원 요청일 경우 회원 오브젝트, 비회원 요청일 경우 null
    //private User loginUser;

    // 회원/비회원 IP 주소 - login주소 또는 게시판 비회원 IP정보
    private String ipAddress;

    // 회원/비회원 UserAgent
    private String userAgent;

    // 헤더에 들어오는 http-user-agent
    private String httpUserAgent;

    private String appVersion;

    private String appOsName;

    private String appOsVersion;

    private String apiName;

    private String apiKey;

    private String token;

    public boolean isValidNamedRequest() {

        if (StringUtils.isNotEmpty(requestId) && StringUtils.isNotEmpty(requestPath)) {
            return true;
        }

        return false;
    }

/*    public boolean isGuestRequest() {

        if (this.loginUser == null) {
            return true;
        }
        if (StringUtils.isEmpty(this.loginUser.getId())) {
            return true;
        }

        return false;
    }*/

 /*   public HttpHeaders getHttpHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.API_REQUEST_ID, this.requestId);

        return headers;
    }*/

    public String toLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("requestId: ").append(this.requestId).append(", ");
        sb.append("requestPath: ").append(this.requestPath).append(", ");
        sb.append("apiName: ").append(this.apiName).append(", ");
        sb.append("appOsName: ").append(this.appOsName).append(", ");
        sb.append("appVersion: ").append(this.appVersion).append(", ");
        sb.append("appOsVersion: ").append(this.appOsVersion).append(", ");
        return sb.toString();
    }
}
