package com.example.burnchuck.domain.notification.service;

import com.example.burnchuck.common.enums.MeetingTaskType;
import com.example.burnchuck.common.event.kafka.MeetingRegisterEventMessage;
import com.example.burnchuck.common.event.kafka.UserDeleteEventMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationKafkaEventHandler {

    private final EmitterService emitterService;
    private final NotificationService notificationService;

    public void handleUserDeleteEvent(UserDeleteEventMessage event) {

        emitterService.disconnectAllEmittersByUserId(event.getUserId());
    }

    public void handleMeetingRegisterEvent(MeetingRegisterEventMessage event) {

        MeetingTaskType type = MeetingTaskType.valueOf(event.getTaskType());

        if (type == MeetingTaskType.CREATE) {
            notificationService.notifyNewFollowerPost(event.getMeetingId());
        }
    }
}
