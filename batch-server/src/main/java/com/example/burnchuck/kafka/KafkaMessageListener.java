package com.example.burnchuck.kafka;

import static com.example.burnchuck.common.constants.KafkaTopic.TOPIC_MEETING_REGISTER;

import com.example.burnchuck.batch.service.MeetingScheduleKafkaMessageHandler;
import com.example.burnchuck.common.event.kafka.MeetingRegisterEventMessage;
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
    private final MeetingScheduleKafkaMessageHandler meetingScheduleKafkaMessageHandler;

    @KafkaListener(
        topics = TOPIC_MEETING_REGISTER,
        containerFactory = "stringKafkaListenerContainerFactory"
    )
    public void consumeMeetingRegisterMessage(String message) {
        try {
            MeetingRegisterEventMessage event = objectMapper.readValue(message, MeetingRegisterEventMessage.class);
            meetingScheduleKafkaMessageHandler.handleMeetingRegisterEvent(event);
        } catch (JsonProcessingException e) {
            log.error("[Kafka] 역직렬화 실패 - topic: {}, message: {}", TOPIC_MEETING_REGISTER, message, e);
        }
    }
}
