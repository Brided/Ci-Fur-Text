package brided.fr.furrygame.design.worldBuild;

import brided.fr.furrygame.design.assetry.TileSet;

public class Room {
    private final String roomName;
    private TileSet tileSet;

    public Room(String roomName, TileSet tileSet) {
        this.roomName = roomName;
        this.tileSet = tileSet;
    }
}
