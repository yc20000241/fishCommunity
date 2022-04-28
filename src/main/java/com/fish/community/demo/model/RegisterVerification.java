package com.fish.community.demo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class RegisterVerification {
    private Long id;

    private String emailToken;

    private String emailVerification;

    private String currentTimeMillis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailToken() {
        return emailToken;
    }

    public void setEmailToken(String emailToken) {
        this.emailToken = emailToken;
    }

    public String getEmailVerification() {
        return emailVerification;
    }

    public void setEmailVerification(String emailVerification) {
        this.emailVerification = emailVerification;
    }

    public String getCurrentTimeMillis() {
        return currentTimeMillis;
    }

    public void setCurrentTimeMillis(String currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", emailToken=").append(emailToken);
        sb.append(", emailVerification=").append(emailVerification);
        sb.append(", currentTimeMillis=").append(currentTimeMillis);
        sb.append("]");
        return sb.toString();
    }
}