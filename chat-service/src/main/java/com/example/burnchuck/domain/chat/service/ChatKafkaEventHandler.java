package com.example.burnchuck.domain.chat.service;

import com.example.burnchuck.common.enums.MeetingTaskType;
import com.example.burnchuck.common.enums.NotificationType;
import com.example.burnchuck.common.event.kafka.MeetingAttendeesEventMessage;
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

    public void handleMeetingAttendeesEvent(MeetingAttendeesEventMessage event) {

        NotificationType type = NotificationType.valueOf(event.getNotificationType());
        Long meetingId = event.getMeetingId();
        Long userId = event.getUserId();

        switch (type) {
            case MEETING_MEMBER_JOIN -> chatRoomService.joinGroupChatRoom(meetingId, userId);
            case MEETING_MEMBER_LEFT -> chatRoomService.leaveChatRoomRegardlessOfStatus(meetingId, userId);
        }
    }
}
