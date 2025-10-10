package com.solvd.companyhierarchy.company.meetingroom;

import com.solvd.companyhierarchy.enums.MeetingType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

import static com.solvd.companyhierarchy.utils.DateTimeUtils.*;

public record MeetingRoomSession(MeetingRoom room, LocalDateTime startTime, MeetingType meetingType) implements AutoCloseable {

    private static final Logger log = LogManager.getLogger(MeetingRoomSession.class);

    public MeetingRoomSession(MeetingRoom room, LocalDateTime startTime, MeetingType meetingType) {
        this.room = room;
        this.startTime = startTime;
        this.meetingType = meetingType;
        log.info("Meeting room - {} booked at {}", room.roomName(), formatTime(startTime));
    }

    public void holdMeeting(String topic) {
        log.info("Meeting held in {} at {}. Reason: '{}'.", room.roomName(), formatTime(startTime), topic);
    }

    @Override
    public void close() {
        log.info("Meeting room {} released.", room.roomName());
    }
}
