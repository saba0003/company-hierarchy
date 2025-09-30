package com.solvd.companyhierarchy.functionals;

import com.solvd.companyhierarchy.company.meetingroom.MeetingRoom;

@FunctionalInterface
public interface MeetingNotifier {
    void notify(MeetingRoom room);
}