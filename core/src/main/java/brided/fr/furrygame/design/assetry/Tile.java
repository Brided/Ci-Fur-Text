package brided.fr.furrygame.design.assetry;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Tile implements Disposable {
    public static final int TILE_SIZE = 64;

    private String textureName;
    private String Type;

    private transient TextureRegion texture;

    public Tile(String textureName, String type, TextureRegion texture) {
        this.textureName = textureName;
        Type = type;
        this.texture = texture;
    }

    public String getTextureName() { return textureName; }
    public String getType() { return Type; }


    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x * TILE_SIZE, y * TILE_SIZE);
    }

    @Override
    public void dispose() { texture.getTexture().dispose(); }

    public TextureRegion getTexture() { return texture; }
}
