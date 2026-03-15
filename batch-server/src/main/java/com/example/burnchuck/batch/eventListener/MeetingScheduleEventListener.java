package com.example.burnchuck.batch.eventListener;

import com.example.burnchuck.batch.repository.MeetingRepository;
import com.example.burnchuck.batch.service.MeetingSchedulingService;
import com.example.burnchuck.common.entity.Meeting;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "ScheduleEventHandler")
public class MeetingScheduleEventListener {

    private final MeetingSchedulingService schedulingService;
    private final MeetingRepository meetingRepository;

    /**
     * 어플리케이션 재시작 후, 삭제된 이벤트 복구
     */
    @EventListener(ApplicationReadyEvent.class)
    public void restoreSchedules() {

        List<Meeting> meetingList = meetingRepository.findActivateMeetingsForSchedules();

        meetingList.forEach(meeting -> {
            schedulingService.scheduleMeetingStatusComplete(meeting.getId());
            schedulingService.scheduleNotification(meeting.getId());
        });

        LocalDate today = LocalDate.now();
        LocalDateTime startDate = today.minusDays(1).atStartOfDay();
        LocalDateTime endDate = today.plusDays(1).atStartOfDay();

        List<Meeting> requireNotificationList = meetingRepository.findActivateMeetingsForNotification(startDate, endDate);

        requireNotificationList.forEach(meeting ->
            schedulingService.scheduleNotification(meeting.getId())
        );
    }
}
