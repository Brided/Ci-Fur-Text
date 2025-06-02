package brided.fr.furrygame.gameLogic.rendering;

import brided.fr.furrygame.design.assetry.Tile;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileRendered extends Tile {
    private TextureRegion texture;

    public TileRendered(String textureName, String type) {
        super(textureName, type);
    }

    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.getTexture().dispose();
    }

    public TextureRegion getTexture() {
        return texture;
    }
}
