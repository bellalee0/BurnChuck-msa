package com.example.burnchuck.domain.meeting.event;

import com.example.burnchuck.common.entity.Meeting;
import com.example.burnchuck.common.enums.MeetingStatus;
import com.example.burnchuck.common.event.meeting.MeetingStatusChangeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publishMeetingStatusChangeEvent(Meeting meeting, MeetingStatus status) {

        MeetingStatusChangeEvent event = new MeetingStatusChangeEvent(meeting, status);
        publisher.publishEvent(event);
    }
}
