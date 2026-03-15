package com.example.burnchuck.domain.meetingLike.service;

import com.example.burnchuck.common.event.kafka.UserDeleteEventMessage;
import com.example.burnchuck.domain.meetingLike.repository.MeetingLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MeetingLikeKafkaEventHandler {

    private final MeetingLikeRepository meetingLikeRepository;

    @Transactional
    public void handleUserDeleteEvent(UserDeleteEventMessage event) {

        meetingLikeRepository.deleteByUserId(event.getUserId());
    }
}
