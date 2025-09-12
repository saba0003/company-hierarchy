package company;

import company.employee.Identifiable;

import java.time.LocalDateTime;

public class MeetingRoom implements Identifiable, Schedulable {

    private String roomName;
    private int capacity;

    public MeetingRoom(String roomName, int capacity) {
        this.roomName = roomName;
        this.capacity = capacity;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String getIdentifier() {
        return "Room " + roomName;
    }

    @Override
    public void schedule(LocalDateTime dateAndTime) {
        System.out.println("Meeting room " + roomName + " scheduled for " + dateAndTime + " for " + capacity + " people.");
    }

    @Override
    public String toString() {
        return getIdentifier() + " | capacity: " + capacity;
    }
}
