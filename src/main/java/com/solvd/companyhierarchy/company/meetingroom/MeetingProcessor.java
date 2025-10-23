package com.solvd.companyhierarchy.company.meetingroom;

import com.solvd.companyhierarchy.functionals.MeetingNotifier;

import java.time.LocalDateTime;

public class MeetingProcessor {

    public void processMeeting(MeetingRoom room, LocalDateTime dateTime, MeetingNotifier notifier) {
        notifier.notify(room, dateTime);
    }
}
