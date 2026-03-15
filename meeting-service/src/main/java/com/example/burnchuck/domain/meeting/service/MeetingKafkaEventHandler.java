package com.example.burnchuck.domain.meeting.service;

import com.example.burnchuck.common.enums.MeetingStatus;
import com.example.burnchuck.common.event.kafka.MeetingStatusEventMessage;
import com.example.burnchuck.common.event.kafka.UserDeleteEventMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingKafkaEventHandler {

    private final AttendanceService attendanceService;
    private final MeetingService meetingService;
    private final ElasticsearchService elasticsearchService;

    public void handleUserDeleteEvent(UserDeleteEventMessage event) {

        Long userId = event.getUserId();

        meetingService.deleteAllHostedMeetingsAfterUserDelete(userId);
        attendanceService.cancelAllAttendanceAfterDeleteUser(userId);
    }

    public void handleMeetingStatusEvent(MeetingStatusEventMessage event) {

        MeetingStatus status = MeetingStatus.valueOf(event.getMeetingStatus());
        Long meetingId = event.getMeetingId();

        switch (status) {
            case OPEN, CLOSED -> elasticsearchService.updateMeetingStatus(meetingId, status);
            case COMPLETED -> elasticsearchService.deleteMeeting(meetingId);
        }
    }
}
