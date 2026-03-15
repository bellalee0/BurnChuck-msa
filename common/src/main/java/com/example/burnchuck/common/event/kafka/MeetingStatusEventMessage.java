package com.example.burnchuck.common.event.kafka;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MeetingStatusEventMessage {

    private final Long meetingId;
    private final String meetingStatus;
}
