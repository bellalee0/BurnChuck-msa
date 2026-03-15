package com.example.burnchuck.domain.auth.service;

import com.example.burnchuck.common.event.kafka.UserDeleteEventMessage;
import com.example.burnchuck.domain.auth.repository.UserRefreshRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AuthKafkaEventHandler {

    private final UserRefreshRepository userRefreshRepository;

    @Transactional
    public void handleUserDeleteEvent(UserDeleteEventMessage event) {

        userRefreshRepository.deleteByUserId(event.getUserId());
    }
}
