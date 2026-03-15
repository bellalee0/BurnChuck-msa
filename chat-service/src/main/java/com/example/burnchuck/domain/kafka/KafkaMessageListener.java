package com.example.burnchuck.domain.kafka;

import static com.example.burnchuck.common.constants.KafkaTopic.TOPIC_MEETING_ATTENDEES;
import static com.example.burnchuck.common.constants.KafkaTopic.TOPIC_MEETING_REGISTER;

import com.example.burnchuck.common.event.kafka.MeetingAttendeesEventMessage;
import com.example.burnchuck.common.event.kafka.MeetingRegisterEventMessage;
import com.example.burnchuck.domain.chat.service.ChatKafkaEventHandler;
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
    private final ChatKafkaEventHandler chatKafkaEventHandler;

    @KafkaListener(
        topics = TOPIC_MEETING_REGISTER,
        containerFactory = "stringKafkaListenerContainerFactory"
    )
    public void consumeMeetingRegisterMessage(String message) {
        try {
            MeetingRegisterEventMessage event = objectMapper.readValue(message, MeetingRegisterEventMessage.class);
            chatKafkaEventHandler.handleMeetingRegisterEvent(event);
        } catch (JsonProcessingException e) {
            log.error("[Kafka] 역직렬화 실패 - topic: {}, message: {}", TOPIC_MEETING_REGISTER, message, e);
        }
    }

    @KafkaListener(
        topics = TOPIC_MEETING_ATTENDEES,
        containerFactory = "stringKafkaListenerContainerFactory"
    )
    public void consumeMeetingAttendeesMessage(String message) {
        try {
            MeetingAttendeesEventMessage event = objectMapper.readValue(message, MeetingAttendeesEventMessage.class);
            chatKafkaEventHandler.handleMeetingAttendeesEvent(event);
        } catch (JsonProcessingException e) {
            log.error("[Kafka] 역직렬화 실패 - topic: {}, message: {}", TOPIC_MEETING_ATTENDEES, message, e);
        }
    }
}
