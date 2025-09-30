package com.solvd.companyhierarchy.company.meetingroom;

import com.solvd.companyhierarchy.company.Schedulable;
import com.solvd.companyhierarchy.company.employee.Identifiable;
import com.solvd.companyhierarchy.functionals.MeetingNotifier;

import java.time.LocalDateTime;

import static com.solvd.companyhierarchy.utils.DateTimeUtils.*;

public record MeetingRoom(String roomName, int capacity) implements Identifiable, Schedulable {

    @Override
    public String getIdentifier() {
        return "Room " + roomName;
    }

    @Override
    public void schedule(LocalDateTime dateAndTime) {
        MeetingNotifier notifier = room ->
                System.out.println("Meeting room - " + room.roomName + " scheduled for " + formateDateAndTime(dateAndTime) + " for " + capacity + " people.");
        notifier.notify(this);
    }

    @Override
    public String toString() {
        return getIdentifier() + " | capacity: " + capacity;
    }
}
