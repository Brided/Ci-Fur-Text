package brided.fr.furrygame.gameLogic.navigation;

import java.util.HashMap;
import java.util.Map;

public class RoomsLayout {
    public Map<Room, String> rooms;

    public static Room room1;
    public static Room room2;

    public RoomsLayout(Map<Room, String> rooms) {
        this.rooms = new HashMap<>();
    }

    public void addRoom(String roomName, String textureName) {
        Room added = new Room(roomName, textureName);

        this.rooms.put(added, roomName);
    }

    static {
        room1 = new Room("door", "rooms/room1.png");
        room2 = new Room("balcony", "rooms/room2.png");

        room1.addHotspot(0, 0, 800, 500, room2);
        room2.addHotspot(0, 0, 800, 500, "View_balcony");
    }
}
