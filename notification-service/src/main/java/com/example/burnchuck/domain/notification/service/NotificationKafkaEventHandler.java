package com.example.burnchuck.domain.notification.service;

import com.example.burnchuck.common.event.kafka.UserDeleteEventMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationKafkaEventHandler {

    private final EmitterService emitterService;

    public void handleUserDeleteEvent(UserDeleteEventMessage event) {

        emitterService.disconnectAllEmittersByUserId(event.getUserId());
    }
}
