package brided.fr.furrygame.design.assetry;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class TileSet {
    private final HashMap<String, Tile> tiles;

    public TileSet(Texture tileSheet) {
        tiles = new HashMap<>();

        TextureRegion[][] regions = TextureRegion.split(tileSheet, Tile.TILE_SIZE, Tile.TILE_SIZE);

        for (int row = 0; row < regions.length; row++) {
            for (int col = 0; col < regions[0].length; col++) {
                Tile tile = new Tile(regions[row][col], "tile_" + row + "_" + col);

                String name = "tile_" + row + "_" + col;
                System.out.println(name);
                this.addTile(name, tile);
            }
        }
    }

    public void addTile(String name, Tile tile) {
        tiles.put(name, tile);
    }

    public Tile getTile(String name) {
        return tiles.get(name);
    }
}
