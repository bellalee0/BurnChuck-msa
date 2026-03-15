package com.example.burnchuck.common.event.kafka;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MeetingAttendeesEventMessage {

    private final Long meetingId;
    private final Long userId;
    private final String notificationType;
}
