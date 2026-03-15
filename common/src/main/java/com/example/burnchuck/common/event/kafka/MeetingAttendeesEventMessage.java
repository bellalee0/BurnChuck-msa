package com.example.burnchuck.common.event.kafka;

import com.example.burnchuck.common.event.meeting.MeetingAttendeesChangeEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MeetingAttendeesEventMessage {

    private final Long meetingId;
    private final Long userId;
    private final String notificationType;

    public static MeetingAttendeesEventMessage from(MeetingAttendeesChangeEvent event) {
        return new MeetingAttendeesEventMessage(
            event.getMeeting().getId(),
            event.getUser().getId(),
            event.getType().name()
        );
    }
}
