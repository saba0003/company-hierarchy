package com.solvd.companyhierarchy.company.meetingroom;

import com.solvd.companyhierarchy.company.Schedulable;
import com.solvd.companyhierarchy.company.employee.Identifiable;
import com.solvd.companyhierarchy.functionals.MeetingNotifier;

import java.time.LocalDateTime;

import static com.solvd.companyhierarchy.utils.DateTimeUtils.*;

public record MeetingRoom(String roomName, int capacity) implements Identifiable, Schedulable, MeetingNotifier {

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
        System.out.println(room.getIdentifier() + " scheduled for " + formateDateAndTime(dateAndTime));
    }

    @Override
    public String toString() {
        return getIdentifier() + " | capacity: " + capacity;
    }
}
