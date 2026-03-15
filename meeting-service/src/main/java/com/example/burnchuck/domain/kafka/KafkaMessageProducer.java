package com.example.burnchuck.domain.kafka;

import static com.example.burnchuck.common.constants.KafkaTopic.TOPIC_MEETING_ATTENDEES;
import static com.example.burnchuck.common.constants.KafkaTopic.TOPIC_MEETING_REGISTER;

import com.example.burnchuck.common.event.kafka.MeetingAttendeesEventMessage;
import com.example.burnchuck.common.event.kafka.MeetingRegisterEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j(topic = "Kafka-producer")
@Service
@RequiredArgsConstructor
public class KafkaMessageProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMeetingRegisterMessage(MeetingRegisterEventMessage message) {
        kafkaTemplate.send(TOPIC_MEETING_REGISTER, message)
            .whenComplete((result, ex) -> {
                if (ex != null) {
                    log.error("[Kafka] 전송 실패 - topic: {}, message: {}", TOPIC_MEETING_REGISTER, message, ex);
                }
            });
    }

    public void sendMeetingAttendeesMessage(MeetingAttendeesEventMessage message) {
        kafkaTemplate.send(TOPIC_MEETING_ATTENDEES, message)
            .whenComplete((result, ex) -> {
                if (ex != null) {
                    log.error("[Kafka] 전송 실패 - topic: {}, message: {}", TOPIC_MEETING_REGISTER, message, ex);
                }
            });
    }
}
