package com.example.burnchuck.domain.chat.service;

import com.example.burnchuck.common.entity.User;
import com.example.burnchuck.common.enums.NotificationType;
import com.example.burnchuck.common.event.meeting.MeetingAttendeesChangeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ChatEventListener {

    private final ChatRoomService chatRoomService;

    /**
     * 모임에 사용자 참가/취소 시, 채팅방 추가 또는 삭제
     */
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    @EventListener
    public void attendeesChangeEvent(MeetingAttendeesChangeEvent event) {

        NotificationType type = event.getType();
        Long meetingId = event.getMeeting().getId();
        User user = event.getUser();

        if (type == NotificationType.MEETING_MEMBER_JOIN) {
            chatRoomService.joinGroupChatRoom(meetingId, user);
        }

        if (type == NotificationType.MEETING_MEMBER_LEFT) {
            chatRoomService.leaveChatRoomRegardlessOfStatus(meetingId, user.getId());
        }
    }
}
