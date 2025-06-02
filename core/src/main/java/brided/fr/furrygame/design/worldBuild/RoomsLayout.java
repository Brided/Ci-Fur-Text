package brided.fr.furrygame.design.worldBuild;

import java.util.HashMap;
import java.util.Map;

public class RoomsLayout {
    public Map<Room, String> rooms;

    public RoomsLayout(Map<Room, String> rooms) {
        this.rooms = new HashMap<>();
    }

    public void addRoom(String roomName, String textureName) {
        // TODO: rooms
        Room added = new Room(roomName, null);

        this.rooms.put(added, roomName);
    }
}
