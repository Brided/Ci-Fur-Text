package brided.fr.furrygame.design.assetry;

import java.util.HashMap;

public class TileSet {
    private HashMap<String, Tile> tiles;
    private String textureSheetLocation;

    public TileSet(String textureSheetLocation) {
        this.tiles = new HashMap<>();
        this.textureSheetLocation = textureSheetLocation;
    }

    public void addTile(String name, String type) {
        Tile tile = new Tile(name, type);
        tiles.put(name, tile);
    }

    public Tile getTile(String name) {
        return tiles.get(name);
    }
}

/*for (int row = 0; row < regions.length; row++) {
    for (int col = 0; col < regions[0].length; col++) {
        Tile tile = new Tile(regions[row][col], "tile_" + row + "_" + col);

        String name = "tile_" + row + "_" + col;
        System.out.println(name);
        this.addTile(name, tile);
    }
}*/
