package com.solvd.companyhierarchy.company.meetingroom;

import com.solvd.companyhierarchy.enums.MeetingType;

import java.time.LocalDateTime;

import static com.solvd.companyhierarchy.utils.DateTimeUtils.*;

public record MeetingRoomSession(MeetingRoom room, LocalDateTime startTime, MeetingType meetingType) implements AutoCloseable {

    public MeetingRoomSession(MeetingRoom room, LocalDateTime startTime, MeetingType meetingType) {
        this.room = room;
        this.startTime = startTime;
        this.meetingType = meetingType;
        System.out.printf("Meeting room - %s booked at %s", room.roomName(), formatTime(startTime));
    }

    public void holdMeeting(String topic) {
        System.out.printf("%nMeeting held in %s at %s. Reason: '%s'.%n", room.roomName(), formatTime(startTime), topic);
    }

    @Override
    public void close() {
        System.out.printf("Meeting room %s released.", room.roomName());
    }
}
