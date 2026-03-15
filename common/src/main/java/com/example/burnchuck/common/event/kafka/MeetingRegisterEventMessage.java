package com.example.burnchuck.common.event.kafka;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MeetingRegisterEventMessage {

    private final Long meetingId;
    private final String taskType;
    private final Long hostUserId;
    private final LocalDateTime meetingDateTime;
}
