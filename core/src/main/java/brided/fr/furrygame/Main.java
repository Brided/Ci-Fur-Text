package brided.fr.furrygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private FitViewport viewport;

    private Texture gecko;
    private Texture desertBack;
    private BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(800, 500);

        gecko = new Texture("gecko.png");
        desertBack = new Texture("ernestDrawnMore6.png");
        font = new BitmapFont();

        font.setColor(Color.BLACK);
    }

    @Override
    public void render() {
        draw();
    }

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        batch.draw(desertBack, 0, 0, worldWidth, worldHeight);

        batch.draw(gecko, 140, 210);
        batch.draw(gecko, 170, 100);

        font.draw(batch, "I'm horny", 100, 100);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        gecko.dispose();
        desertBack.dispose();
        font.dispose();
    }
}
