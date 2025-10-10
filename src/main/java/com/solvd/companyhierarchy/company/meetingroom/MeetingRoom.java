package com.solvd.companyhierarchy.company.meetingroom;

import com.solvd.companyhierarchy.company.Schedulable;
import com.solvd.companyhierarchy.company.employee.Identifiable;
import com.solvd.companyhierarchy.functionals.MeetingNotifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

import static com.solvd.companyhierarchy.utils.DateTimeUtils.*;

public record MeetingRoom(String roomName, int capacity) implements Identifiable, Schedulable, MeetingNotifier {

    private static final Logger log = LogManager.getLogger(MeetingRoom.class);

    @Override
    public String getIdentifier() {
        return "Room: " + roomName;
    }

    @Override
    public void schedule(LocalDateTime dateAndTime) {
        notify(this, dateAndTime);
    }

    @Override
    public void notify(MeetingRoom room, LocalDateTime dateAndTime) {
        log.info("{} scheduled for {}", room.getIdentifier(), formateDateAndTime(dateAndTime));
    }

    @Override
    public String toString() {
        return getIdentifier() + " | capacity: " + capacity;
    }
}
