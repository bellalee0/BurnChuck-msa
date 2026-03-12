package com.example.burnchuck.domain.notification.repository;

import com.example.burnchuck.common.entity.Meeting;
import com.example.burnchuck.common.entity.UserMeeting;
import com.example.burnchuck.common.enums.ErrorCode;
import com.example.burnchuck.common.exception.CustomException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMeetingRepository extends JpaRepository<UserMeeting, Long> {

    @Query("""
        SELECT um
        FROM UserMeeting um
        JOIN FETCH um.user
        JOIN FETCH um.meeting
        WHERE um.meeting = :meeting AND um.meetingRole = com.example.burnchuck.common.enums.MeetingRole.HOST
        """)
    Optional<UserMeeting> findHostByMeeting(@Param("meeting") Meeting meeting);

    @Query("""
        SELECT um
        FROM UserMeeting um JOIN FETCH um.user
        WHERE um.meeting.id = :meetingId
        """)
    List<UserMeeting> findMeetingMembers(@Param("meetingId") Long meetingId);

    default UserMeeting findHostUserMeetingByMeeting(Meeting meeting) {
        return findHostByMeeting(meeting)
            .orElseThrow(() -> new CustomException(ErrorCode.ATTENDANCE_NOT_FOUND));
    }
}
