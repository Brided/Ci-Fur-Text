package brided.fr.furrygame.gameLogic.navigation;

import java.util.HashMap;
import java.util.Map;

public class RoomsLayout {
    public Map<Room, String> rooms;

    public RoomsLayout(Map<Room, String> rooms) {
        this.rooms = new HashMap<>();
    }

    public void addRoom(String roomName, String textureName) {
        Room added = new Room(roomName, textureName);

        this.rooms.put(added, roomName);
    }
}
