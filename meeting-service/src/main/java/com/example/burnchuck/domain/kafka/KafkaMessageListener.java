package com.example.burnchuck.domain.kafka;

import static com.example.burnchuck.common.constants.KafkaTopic.TOPIC_MEETING_STATUS;
import static com.example.burnchuck.common.constants.KafkaTopic.TOPIC_USER_DELETE;

import com.example.burnchuck.common.event.kafka.MeetingStatusEventMessage;
import com.example.burnchuck.common.event.kafka.UserDeleteEventMessage;
import com.example.burnchuck.domain.meeting.service.MeetingKafkaEventHandler;
import com.example.burnchuck.domain.meetingLike.service.MeetingLikeKafkaEventHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j(topic = "kafka-listener")
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

    private final ObjectMapper objectMapper;
    private final MeetingKafkaEventHandler meetingEventHandler;
    private final MeetingLikeKafkaEventHandler meetingLikeEventHandler;

    @KafkaListener(
        topics = TOPIC_USER_DELETE,
        containerFactory = "stringKafkaListenerContainerFactory"
    )
    public void consumeMeetingRegisterMessage(String message) {
        try {
            UserDeleteEventMessage event = objectMapper.readValue(message, UserDeleteEventMessage.class);
            meetingEventHandler.handleUserDeleteEvent(event);
            meetingLikeEventHandler.handleUserDeleteEvent(event);
        } catch (JsonProcessingException e) {
            log.error("[Kafka] 역직렬화 실패 - topic: {}, message: {}", TOPIC_USER_DELETE, message, e);
        }
    }

    @KafkaListener(
        topics = TOPIC_MEETING_STATUS,
        containerFactory = "stringKafkaListenerContainerFactory"
    )
    public void consumeMeetingStatusMessage(String message) {
        try {
            MeetingStatusEventMessage event = objectMapper.readValue(message, MeetingStatusEventMessage.class);
            meetingEventHandler.handleMeetingStatusEvent(event);
        } catch (JsonProcessingException e) {
            log.error("[Kafka] 역직렬화 실패 - topic: {}, message: {}", TOPIC_MEETING_STATUS, message, e);
        }
    }
}
