package com.example.burnchuck.domain.notification.service;

import com.example.burnchuck.common.event.meeting.MeetingAttendeesChangeEvent;
import com.example.burnchuck.common.event.notification.CommentNotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class NotificationEventListener {

    private final NotificationService notificationService;

    @Async("customTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @EventListener
    public void createMeetingMemberNotification(MeetingAttendeesChangeEvent event) {

        notificationService.notifyMeetingMember(event.getType(), event.getMeeting(), event.getUser());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @EventListener
    public void createCommentNotification(CommentNotificationEvent event) {

        notificationService.notifyCommentRequest(event.getMeeting());
    }
}
