package com.solvd.companyhierarchy.functionals;

import com.solvd.companyhierarchy.company.meetingroom.MeetingRoom;

import java.time.LocalDateTime;

@FunctionalInterface
public interface MeetingNotifier {

    void notify(MeetingRoom room, LocalDateTime dateAndTime);

}
