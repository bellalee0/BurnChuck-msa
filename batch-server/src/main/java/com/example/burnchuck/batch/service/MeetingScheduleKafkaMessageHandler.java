package com.example.burnchuck.batch.service;

import com.example.burnchuck.common.enums.MeetingTaskType;
import com.example.burnchuck.common.event.kafka.MeetingRegisterEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j(topic = "ScheduleEventHandler")
@Component
@RequiredArgsConstructor
public class MeetingScheduleKafkaMessageHandler {

    private final MeetingSchedulingService schedulingService;

    public void handleMeetingRegisterEvent(MeetingRegisterEventMessage event) {

        MeetingTaskType type = MeetingTaskType.valueOf(event.getTaskType());
        Long meetingId = event.getMeetingId();

        try {
            switch (type) {
                case CREATE -> {
                    schedulingService.scheduleMeetingStatusComplete(meetingId);
                    schedulingService.scheduleNotification(meetingId);
                }
                case UPDATE -> {
                    schedulingService.scheduleCancel(meetingId);

                    schedulingService.scheduleMeetingStatusComplete(meetingId);
                    schedulingService.scheduleNotification(meetingId);
                }
                case DELETE -> schedulingService.scheduleCancel(meetingId);
            }
        } catch (Exception e) {
            log.error("스케줄러 생성 실패 : {}", meetingId);
        }
    }
}
