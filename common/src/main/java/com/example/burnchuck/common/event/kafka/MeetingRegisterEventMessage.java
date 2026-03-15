package com.example.burnchuck.common.event.kafka;

import com.example.burnchuck.common.event.meeting.MeetingEvent;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MeetingRegisterEventMessage {

    private final Long meetingId;
    private final String taskType;
    private final Long hostUserId;
    private final LocalDateTime meetingDateTime;

    public static MeetingRegisterEventMessage from(MeetingEvent event) {
        return new MeetingRegisterEventMessage(
            event.getMeeting().getId(),
            event.getType().name(),
            event.getUser().getId(),
            event.getMeeting().getMeetingDateTime()
        );
    }
}
