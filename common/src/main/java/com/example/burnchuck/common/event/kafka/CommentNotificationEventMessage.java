package com.example.burnchuck.common.event.kafka;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentNotificationEventMessage {

    private final Long meetingId;
}
