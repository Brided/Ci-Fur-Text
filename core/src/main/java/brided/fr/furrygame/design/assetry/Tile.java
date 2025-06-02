package brided.fr.furrygame.design.assetry;

public class Tile{
    public static final int TILE_SIZE = 64;

    private String textureName;
    private String Type;

    public Tile(String textureName, String type) {
        this.textureName = textureName;
        Type = type;
    }

    public String getType() {
        return Type;
    }
}
