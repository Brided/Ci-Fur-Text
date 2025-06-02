package brided.fr.furrygame.design.worldBuild;

import java.util.ArrayList;

public class RoomsLayout {
    private final ArrayList<Room> rooms;

    public RoomsLayout() {
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }
}
