package company.meetingroom;

import company.Schedulable;
import company.employee.Identifiable;
import functionals.MeetingNotifier;

import java.time.LocalDateTime;

import static utils.DateTimeUtils.*;

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
