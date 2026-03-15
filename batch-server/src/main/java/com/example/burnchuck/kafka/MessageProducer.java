package com.example.burnchuck.kafka;

import static com.example.burnchuck.common.constants.KafkaTopic.TOPIC_COMMENT_NOTIFICATION;

import com.example.burnchuck.common.event.kafka.CommentNotificationEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j(topic = "Kafka-producer")
@Service
@RequiredArgsConstructor
public class MessageProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendCommentNotificationMessage(CommentNotificationEventMessage message) {
        kafkaTemplate.send(TOPIC_COMMENT_NOTIFICATION, message)
            .whenComplete((result, ex) -> {
                if (ex != null) {
                    log.error("[Kafka] 전송 실패 - topic: {}, message: {}", TOPIC_COMMENT_NOTIFICATION, message, ex);
                }
            });
    }
}
