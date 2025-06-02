package brided.fr.furrygame.design.assetry;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Tile implements Disposable {
    public static final int TILE_SIZE = 64;

    private TextureRegion texture;
    private String Type;

    public Tile(TextureRegion texture, String type) {
        this.texture = texture;
        Type = type;
    }

    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x, y);
    }

    @Override
    public void dispose() {
        texture.getTexture().dispose();
    }

    public String getType() {
        return Type;
    }

    public TextureRegion getTexture() {
        return texture;
    }
}
