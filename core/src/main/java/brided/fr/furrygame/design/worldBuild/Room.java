package brided.fr.furrygame.design.worldBuild;

import brided.fr.furrygame.design.assetry.Tile;
import brided.fr.furrygame.design.assetry.TileSet;

public class Room {
    private final String roomName;
    private TileSet tileSet;

    private final int width;
    private final int height;

    private TileMap background;
    private TileMap walls;
    private TileMap hazards;

    public Room(String roomName, TileSet tileSet, int width, int height) {
        this.roomName = roomName;
        this.tileSet = tileSet;

        this.width = width;
        this.height = height;

        this.background = new TileMap(width, height);
        this.walls = new TileMap(width, height);
        this.hazards = new TileMap(width, height);
    }

    public int getWorldHeight() {
        return height * Tile.TILE_SIZE;
    }

    public int getWorldHWidth() {
        return width * Tile.TILE_SIZE;
    }
}
