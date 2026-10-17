package com.suguna.breeder_revamp.repositories;

import com.suguna.breeder_revamp.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSession, String> {
}
