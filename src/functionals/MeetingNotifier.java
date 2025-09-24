package functionals;

import company.meetingroom.MeetingRoom;

@FunctionalInterface
public interface MeetingNotifier {
    void notify(MeetingRoom room);
}