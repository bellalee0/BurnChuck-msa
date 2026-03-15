package com.example.burnchuck.common.event.kafka;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDeleteEventMessage {

    private final Long userId;
}
