package functionals;

import company.meetingroom.MeetingRoom;

import java.time.LocalDateTime;

@FunctionalInterface
public interface MeetingScheduler {
    void schedule(MeetingRoom meetingRoom, LocalDateTime dateTime);
}
