package com.example.burnchuck.domain.chat.dto.response;

import com.example.burnchuck.domain.entity.ChatMessage;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatMessageResponse {

    private final String id;
    private final Long roomId;
    private final Long senderId;
    private final String content;
    private final String senderNickname;
    private final String senderProfile;
    private final Long sequence;
    private final LocalDateTime createdDatetime;

    public static ChatMessageResponse from(ChatMessage message) {
        return new ChatMessageResponse(
                message.getId(),
                message.getRoomId(),
                message.getSenderId(),
                message.getContent(),
                message.getSenderNickname(),
                message.getSenderProfile(),
                message.getSequence(),
                message.getCreatedDatetime()
        );
    }
}
