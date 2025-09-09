package company;

public class MeetingRoom {

    private String roomName;
    private int capacity;

    public MeetingRoom(String roomName, int capacity) {
        this.roomName = roomName;
        this.capacity = capacity;
    }

    public void bookRoom() {
        System.out.println("Room " + roomName + " booked for " + capacity + " people.");
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
    public String toString() {
        return "Room: " + roomName + " | capacity: " + capacity;
    }
}
