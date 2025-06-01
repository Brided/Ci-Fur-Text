package brided.fr.furrygame;

import brided.fr.furrygame.gameLogic.characters.Furacter;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch spriteBatch;
    private Viewport viewport;
    private Camera camera;

    private Furacter bull;

    private float delta;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);

        bull = new Furacter(
            "Teagan", 0,0,
            new Texture("characterSprites/rpgBullFacing_Left.png"),
            new Texture("characterSprites/rpgBullFacing_Right.png"),
            new Texture("characterSprites/rpgBullFacing_Up.png"),
            new Texture("characterSprites/rpgBullFacing_Down.png")
        );
    }

    @Override
    public void render() {
        delta = Gdx.graphics.getDeltaTime();

        input();
        draw();
    }

    private void input() {
        float speed = 200f;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            bull.move(Furacter.Direction.LEFT, delta * speed);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            bull.move(Furacter.Direction.RIGHT, delta * speed);
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            bull.move(Furacter.Direction.UP, delta * speed);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            bull.move(Furacter.Direction.DOWN, delta * speed);
        }
    }

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.3f, 1f);
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        camera.update();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        bull.render(spriteBatch);

        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        bull.dispose();
    }
}
