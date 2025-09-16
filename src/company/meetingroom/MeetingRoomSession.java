package company.meetingroom;

import utils.DateUtils;

import java.time.LocalDateTime;

public class MeetingRoomSession implements AutoCloseable {

    private final MeetingRoom room;
    private final String formattedTime;

    public MeetingRoomSession(MeetingRoom room, LocalDateTime startTime) {
        this.room = room;
        formattedTime = startTime.format(DateUtils.getTimeFormatter());
        System.out.printf("Meeting room %s booked at %s", room.getRoomName(), formattedTime);
    }

    public void holdMeeting(String topic) {
        System.out.printf("Meeting held in %s at %s. Reason: '%s'.%n", room.getRoomName(), formattedTime, topic);
    }

    @Override
    public void close() {
        System.out.printf("Meeting room %s released.", room.getRoomName());
    }
}
