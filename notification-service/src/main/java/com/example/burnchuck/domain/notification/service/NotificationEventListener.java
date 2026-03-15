package com.example.burnchuck.domain.notification.service;

import com.example.burnchuck.common.event.notification.CommentNotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class NotificationEventListener {

    private final NotificationService notificationService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @EventListener
    public void createCommentNotification(CommentNotificationEvent event) {

        notificationService.notifyCommentRequest(event.getMeeting());
    }
}
