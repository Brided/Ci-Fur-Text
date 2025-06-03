package brided.fr.furrygame.design.worldBuild;

import brided.fr.furrygame.design.assetry.Tile;
import brided.fr.furrygame.design.assetry.TileSet;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileMap {
    private final Tile[][] mapping;
    private final int height;
    private final int width;

    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;

        this.mapping = new Tile[width][height];
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < width * height; i++) {
            int x = i % width;  // Column
            int y = i / width;

            if (mapping[x][y] != null) {
                mapping[x][y].render(batch, x, y);
            }
        }
    }

    public void setTile(Tile tile, int x, int y) { mapping[x][y] = tile; }

    public void fillAll(Tile tile) {
        for (int i = 0; i < width * height; i++) {
            int x = i % width;  // Column
            int y = i / width;

            mapping[x][y] = tile;
        }
    }

    public int getWorldHeight() { return height * Tile.TILE_SIZE; }

    public int getWorldHWidth() { return width * Tile.TILE_SIZE; }

    public static TileMap createGridMap(Tile tile, int width, int height) {
        TileMap tileMap = new TileMap(width, height);
        tileMap.fillAll(tile);
        return tileMap;
    }
}
