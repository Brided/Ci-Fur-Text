package brided.fr.furrygame.design.assetry;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.HashMap;

public class TileSet {
    private HashMap<String, Tile> tiles;
    private String textureSheetLocation;
    protected String setName;

    private transient Texture tileSheet;

    public TileSet(String textureSheetLocation, String setName) {
        this.tiles = new HashMap<>();
        this.textureSheetLocation = textureSheetLocation;
        this.setName = setName;

        this.tileSheet = new Texture(textureSheetLocation);
        tileSheet.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
    }

    public void addTile(String name, Tile tile) {
        tiles.put(name, tile);
    }

    public Tile getTile(String name) { return tiles.get(name); }

    public ArrayList<String> getKeySet() {
        return new ArrayList<>(tiles.keySet());
    }

    public Tile getAt (int index) {
        String indexed = getKeySet().get(index);
        return getTile(indexed);
    }

    public void load() {
        TextureRegion[][] regions = TextureRegion.split(tileSheet, Tile.TILE_SIZE, Tile.TILE_SIZE);

        for (int row = 0; row < regions.length; row++) {
            for (int col = 0; col < regions[0].length; col++) {
                String name = setName + "_" + row + "_" + col;
                Tile tile = new Tile(name, setName, regions[row][col]);

                this.addTile(name, tile);
            }
        }
    }
}
