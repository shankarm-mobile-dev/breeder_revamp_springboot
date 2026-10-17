package com.suguna.breeder_revamp.service;

import com.suguna.breeder_revamp.model.UserSession;
import com.suguna.breeder_revamp.repositories.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class UserSessionService {

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Transactional
    public String createOrReplaceSession(String userCode, String deviceId) {
        if (userCode == null || userCode.isBlank()) {
            return null;
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        UserSession session = userSessionRepository.findById(userCode.trim()).orElse(new UserSession());
        session.setUserCode(userCode.trim());
        session.setSessionToken(token);
        session.setDeviceId(deviceId);
        session.setLoginTime(new Date());
        userSessionRepository.save(session);
        return token;
    }

    public boolean isValidSession(String userCode, String sessionToken) {
        if (userCode == null || userCode.isBlank() || sessionToken == null || sessionToken.isBlank()) {
            return false;
        }
        return userSessionRepository.findById(userCode.trim())
                .map(session -> sessionToken.equals(session.getSessionToken()))
                .orElse(false);
    }
}
