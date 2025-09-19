package company.meetingroom;

import java.time.LocalDateTime;

import static utils.DateTimeUtils.*;

public class MeetingRoomSession implements AutoCloseable {

    private final MeetingRoom room;
    private final LocalDateTime startTime;

    public MeetingRoomSession(MeetingRoom room, LocalDateTime startTime) {
        this.room = room;
        this.startTime = startTime;
        System.out.printf("Meeting room - %s booked at %s", room.getRoomName(), formatTime(startTime));
    }

    public void holdMeeting(String topic) {
        System.out.printf("%nMeeting held in %s at %s. Reason: '%s'.%n", room.getRoomName(), formatTime(startTime), topic);
    }

    @Override
    public void close() {
        System.out.printf("Meeting room %s released.", room.getRoomName());
    }
}
