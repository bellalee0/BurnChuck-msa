package com.example.burnchuck.domain.chat.service;

import com.example.burnchuck.common.enums.MeetingTaskType;
import com.example.burnchuck.common.event.kafka.MeetingRegisterEventMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatKafkaEventHandler {

    private final ChatRoomService chatRoomService;

    public void handleMeetingRegisterEvent(MeetingRegisterEventMessage event) {

        MeetingTaskType type = MeetingTaskType.valueOf(event.getTaskType());

        if (type == MeetingTaskType.CREATE) {
            chatRoomService.createGroupChatRoom(event.getMeetingId(), event.getHostUserId());
        }
    }
}
