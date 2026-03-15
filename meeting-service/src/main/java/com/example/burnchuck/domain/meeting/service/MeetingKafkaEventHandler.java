package com.example.burnchuck.domain.meeting.service;

import com.example.burnchuck.common.event.kafka.UserDeleteEventMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MeetingKafkaEventHandler {

    private final AttendanceService attendanceService;
    private final MeetingService meetingService;

    public void handleUserDeleteEvent(UserDeleteEventMessage event) {

        Long userId = event.getUserId();

        meetingService.deleteAllHostedMeetingsAfterUserDelete(userId);
        attendanceService.cancelAllAttendanceAfterDeleteUser(userId);
    }
}
